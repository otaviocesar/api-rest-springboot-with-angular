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

import br.com.otavio.delivery.model.Recipient;

@Repository
public class RecipientRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String ID_DELIVERY_JOB = "idDeliveryJob";

	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TRC_ID_RECIPIENT as id, TRC_TMS_ID_MESSAGE as idMessage, TRC_TDJ_ID_DELIVERY_JOB as idDeliveryJob, TRC_ADDRESS as address, TRC_TYPE as type "
			+ "FROM DELIVERY_SERVICE.TAB_RECIPIENT";

	private final BeanPropertyRowMapper<Recipient> recipientRowMapper = BeanPropertyRowMapper
		.newInstance(Recipient.class);

	public RecipientRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Recipient save(Recipient recipient, Long messageId) {
		KeyHolder holder = new GeneratedKeyHolder();

		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_RECIPIENT(TRC_ID_RECIPIENT, TRC_TMS_ID_MESSAGE, TRC_TDJ_ID_DELIVERY_JOB, TRC_ADDRESS, TRC_TYPE) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_RECIPIENT.NEXTVAL, :idMessage, :idDeliveryJob, :address, :type)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idMessage", messageId);
		parameters.addValue(ID_DELIVERY_JOB, recipient.getIdDeliveryJob());
		parameters.addValue("address", recipient.getAddress());
		parameters.addValue("type", recipient.getType().getCode());
		String[] idField = {
			"TRC_ID_RECIPIENT"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		recipient.setId(holder.getKey().longValue());
		return recipient;
	}
	
	public List<Recipient> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, recipientRowMapper);
	}
	
	public Optional<Recipient> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TRC_ID_RECIPIENT = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			Recipient findObj = jdbcTemplate.queryForObject(selectSql, parameters, recipientRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}

	public List<Recipient> findByDeliveryJob(Long idDeliveryJob) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TRC_TDJ_ID_DELIVERY_JOB = :idDeliveryJob";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(ID_DELIVERY_JOB, idDeliveryJob);
		return jdbcTemplate.query(selectSql, parameters, recipientRowMapper);
	}

	public Optional<Recipient> update(Long recipientId, Recipient recipient) {
		String updateSql = "UPDATE DELIVERY_SERVICE.TAB_RECIPIENT  SET TRC_TMS_ID_MESSAGE = :idMessage, TRC_TDJ_ID_DELIVERY_JOB = :idDeliveryJob, TRC_ADDRESS = :address, TRC_TYPE = :type WHERE TRC_ID_RECIPIENT = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", recipientId);
		parameters.addValue("idMessage", recipient.getIdMessage());
		parameters.addValue(ID_DELIVERY_JOB, recipient.getIdDeliveryJob());
		parameters.addValue("address", recipient.getAddress());
		parameters.addValue("type", recipient.getType().getCode());
		jdbcTemplate.update(updateSql, parameters);
		return this.findById(recipientId);
	}
}
