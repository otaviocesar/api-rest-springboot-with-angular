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

import br.com.otavio.delivery.model.DeliveryJobLog;

@Repository
public class DeliveryJobLogRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TDL_ID as id, TDL_TDJ_ID_DELIVERY_JOB as idDeliveryJob, TDL_DATE as sendDate, TDL_ERROR as error "
			+ "FROM DELIVERY_SERVICE.TAB_DELIVERY_JOB_LOG";
	
	private final BeanPropertyRowMapper<DeliveryJobLog> deliveryJobLogRowMapper = BeanPropertyRowMapper
		.newInstance(DeliveryJobLog.class);
	
	public DeliveryJobLogRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public DeliveryJobLog save(DeliveryJobLog deliveryJobLog, Long deliveryJobId) {
		KeyHolder holder = new GeneratedKeyHolder();
		
		Timestamp sendDate = Timestamp.valueOf(deliveryJobLog.getSendDate());
		
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_DELIVERY_JOB_LOG(TDL_ID, TDL_TDJ_ID_DELIVERY_JOB, TDL_DATE, TDL_ERROR) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_DELIVERY_JOB_LOG.NEXTVAL, :idDeliveryJob, :sendDate, :error)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idDeliveryJob", deliveryJobId);
		parameters.addValue("sendDate", sendDate);
		parameters.addValue("error", deliveryJobLog.getError());
		String[] idField = {
			"TDL_ID"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		deliveryJobLog.setId(holder.getKey().longValue());
		return deliveryJobLog;
	}

	public List<DeliveryJobLog> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, deliveryJobLogRowMapper);
	}

	public Optional<DeliveryJobLog> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TDL_ID = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			DeliveryJobLog findObj = jdbcTemplate.queryForObject(selectSql, parameters, deliveryJobLogRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
}
