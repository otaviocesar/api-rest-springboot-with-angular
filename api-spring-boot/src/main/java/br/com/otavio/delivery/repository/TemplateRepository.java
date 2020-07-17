package br.com.otavio.delivery.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.otavio.delivery.model.Template;
import br.com.otavio.delivery.model.TemplateStatus;

@Repository
public class TemplateRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String STATUS = "status";
	
	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TTP_ID_TEMPLATE as id, TTP_CONTENT as content, TTP_TYPE as type, TTP_NAME as name, TTP_DESCRIPTION as description, TTP_STATUS as status "
			+ "FROM DELIVERY_SERVICE.TAB_TEMPLATE";
	
	private final BeanPropertyRowMapper<Template> templateRowMapper = BeanPropertyRowMapper
		.newInstance(Template.class);
	
	public TemplateRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public Template save(Template template) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_TEMPLATE(TTP_ID_TEMPLATE, TTP_CONTENT, TTP_TYPE, TTP_NAME, TTP_DESCRIPTION, TTP_STATUS) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_TEMPLATE.NEXTVAL, :content, :type, :name, :description, :status)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("content", template.getContent());
		parameters.addValue("type", template.getType().getCode());
		parameters.addValue("name", template.getName());
		parameters.addValue("description", template.getDescription());
		parameters.addValue(STATUS, template.getStatus().getCode());
		String[] idField = {
			"TTP_ID_TEMPLATE"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		template.setId(holder.getKey().longValue());
		return template;
	}
	
	public List<Template> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, templateRowMapper);
	}
	
	public Optional<Template> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TTP_ID_TEMPLATE = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			Template findObj = jdbcTemplate.queryForObject(selectSql, parameters, templateRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	public Optional<Template> update(Long templateId, Template template) {
		String updateSql = "UPDATE DELIVERY_SERVICE.TAB_TEMPLATE SET TTP_CONTENT = :content, TTP_TYPE = :type, TTP_NAME = :name, TTP_DESCRIPTION = :description, TTP_STATUS = :status WHERE TTP_ID_TEMPLATE = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", templateId);
		parameters.addValue("content", template.getContent());
		parameters.addValue("type", template.getType().getCode());
		parameters.addValue("name", template.getName());
		parameters.addValue("description", template.getDescription());
		parameters.addValue(STATUS, template.getStatus().getCode());
		jdbcTemplate.update(updateSql, parameters);
		return this.findById(templateId);
	}

	public void updateStatus(Long id, TemplateStatus templateStatus) {
		String updateSql = "UPDATE DELIVERY_SERVICE.TAB_TEMPLATE SET TTP_STATUS = :status"
				+ " WHERE TTP_ID_TEMPLATE = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		parameters.addValue(STATUS, templateStatus.getCode());
		jdbcTemplate.update(updateSql, parameters);
	}
}
