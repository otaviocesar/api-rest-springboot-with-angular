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

import br.com.otavio.delivery.model.MessageParam;

@Repository
public class MessageParamRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TMP_ID as id, TMP_TMS_ID_MESSAGE as idMessage, TMP_NAME as name, TMP_VALUE as value, TMP_AUTHOR as author "
			+ "FROM DELIVERY_SERVICE.TAB_MESSAGE_PARAM";
	
	private final BeanPropertyRowMapper<MessageParam> messageParamRowMapper = BeanPropertyRowMapper
		.newInstance(MessageParam.class);
	
	public MessageParamRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public MessageParam save(MessageParam messageParam, Long messageId) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_MESSAGE_PARAM(TMP_ID, TMP_TMS_ID_MESSAGE, TMP_NAME, TMP_VALUE, TMP_AUTHOR) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_MESSAGE.NEXTVAL, :idMessage, :name, :value, :author)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idMessage", messageId);
		parameters.addValue("name", messageParam.getName());
		parameters.addValue("value", messageParam.getValue());
		parameters.addValue("author", messageParam.getAuthor());
		String[] idField = {
			"TMP_ID"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		messageParam.setId(holder.getKey().longValue());
		return messageParam;
	}

	public List<MessageParam> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, messageParamRowMapper);
	}

	public Optional<MessageParam> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TMP_ID = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			MessageParam findObj = jdbcTemplate.queryForObject(selectSql, parameters, messageParamRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}

	public Optional<MessageParam> findByMessageId(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TMP_TMS_ID_MESSAGE = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			MessageParam findObj = jdbcTemplate.queryForObject(selectSql, parameters, messageParamRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
}
