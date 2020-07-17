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

import br.com.otavio.delivery.model.Sender;

@Repository
public class SenderRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TSD_ID_SENDER as id, TSD_NAME as name "
			+ "FROM DELIVERY_SERVICE.TAB_SENDER";

	private final BeanPropertyRowMapper<Sender> senderRowMapper = BeanPropertyRowMapper
		.newInstance(Sender.class);

	public SenderRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Sender save(Sender sender) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_SENDER(TSD_ID_SENDER, TSD_NAME) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_SENDER.NEXTVAL, :name)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", sender.getName());
		String[] idField = {
			"TSD_ID_SENDER"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		sender.setId(holder.getKey().longValue());
		return sender;
	}

	public List<Sender> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, senderRowMapper);
	}

	public Optional<Sender> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TSD_ID_SENDER = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			Sender findObj = jdbcTemplate.queryForObject(selectSql, parameters, senderRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	public Optional<Sender> findByName(Sender sender) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE lower(TSD_NAME) like :name";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", sender.getName());
		try {
			Sender findObj = jdbcTemplate.queryForObject(selectSql, parameters, senderRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
}
