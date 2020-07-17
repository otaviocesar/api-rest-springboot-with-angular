package br.com.otavio.delivery.repository;

import java.sql.Timestamp;
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

import br.com.otavio.delivery.model.Message;
import br.com.otavio.delivery.model.MessageStatus;

@Repository
public class MessageRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String ID = "id";
	
	private static final String ID_TEMPLATE = "idTemplate";

	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TMS_ID_MESSAGE as id, TMS_TSD_ID_SENDER as idSender, TMS_TTP_ID_TEMPLATE as idTemplate, TMS_DATE as sendDate, TMS_SCHEDULE as schedule, TMS_STATUS as status "
			+ "FROM DELIVERY_SERVICE.TAB_MESSAGE";

	private final BeanPropertyRowMapper<Message> messageRowMapper = BeanPropertyRowMapper
		.newInstance(Message.class);

	public MessageRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Message save(Message message) {
		KeyHolder holder = new GeneratedKeyHolder();

		Timestamp date = Timestamp.valueOf(message.getSendDate());
		Timestamp schedule = Timestamp.valueOf(message.getSchedule());

		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_MESSAGE(TMS_ID_MESSAGE, TMS_TSD_ID_SENDER, TMS_TTP_ID_TEMPLATE, TMS_DATE, TMS_SCHEDULE, TMS_STATUS) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_MESSAGE.NEXTVAL, :idSender, :idTemplate, :sendDate, :schedule, :status)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idSender", message.getIdSender());
		parameters.addValue(ID_TEMPLATE, message.getIdTemplate());
		parameters.addValue("sendDate", date);
		parameters.addValue("schedule", schedule);
		parameters.addValue("status", MessageStatus.BUILDING.getCode());
		String[] idField = {
			"TMS_ID_MESSAGE"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		message.setId(holder.getKey().longValue());
		return message;
	}
	
	public List<Message> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, messageRowMapper);
	}
	
	public Optional<Message> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TMS_ID_MESSAGE = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(ID, id);
		try {
			Message findObj = jdbcTemplate.queryForObject(selectSql, parameters, messageRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	public Optional<Message> updateStatus(Long messageId, MessageStatus status) {
		String updateSql = "UPDATE DELIVERY_SERVICE.TAB_MESSAGE SET TMS_STATUS = :status WHERE TMS_ID_MESSAGE = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", messageId);
		parameters.addValue("status", status.getCode());
		jdbcTemplate.update(updateSql, parameters);
		return this.findById(messageId);
	}
}
