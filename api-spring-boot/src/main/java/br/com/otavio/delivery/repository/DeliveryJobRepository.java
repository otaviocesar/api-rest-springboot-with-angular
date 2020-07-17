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

import br.com.otavio.delivery.model.DeliveryJob;
import br.com.otavio.delivery.model.DeliveryJobStatus;

@Repository
public class DeliveryJobRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TDJ_ID_DELIVERY_JOB as id, TDJ_STATUS as status, TDJ_SEND_DATE as sendDate "
			+ "FROM DELIVERY_SERVICE.TAB_DELIVERY_JOB";

	private final BeanPropertyRowMapper<DeliveryJob> deliveryJobRowMapper = BeanPropertyRowMapper
		.newInstance(DeliveryJob.class);

	public DeliveryJobRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public DeliveryJob save(DeliveryJob deliveryJob) {
		KeyHolder holder = new GeneratedKeyHolder();
		Timestamp sendDate = Timestamp.valueOf(deliveryJob.getSendDate());

		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_DELIVERY_JOB(TDJ_ID_DELIVERY_JOB, TDJ_STATUS, TDJ_SEND_DATE) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_MESSAGE.NEXTVAL, :status, :sendDate)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("status", DeliveryJobStatus.NEW.getCode());
		parameters.addValue("sendDate", sendDate);
		String[] idField = {
			"TDJ_ID_DELIVERY_JOB"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		deliveryJob.setId(holder.getKey().longValue());
		return deliveryJob;
	}
	
	public List<DeliveryJob> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, deliveryJobRowMapper);
	}
	
	public Optional<DeliveryJob> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TDJ_ID_DELIVERY_JOB = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			DeliveryJob findObj = jdbcTemplate.queryForObject(selectSql, parameters, deliveryJobRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}

	public Optional<DeliveryJob> updateStatus(Long deliveryJobId, DeliveryJobStatus status) {
		String updateSql = "UPDATE DELIVERY_SERVICE.TAB_DELIVERY_JOB  SET TDJ_STATUS = :status WHERE TDJ_ID_DELIVERY_JOB = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", deliveryJobId);
		parameters.addValue("status", status.getCode());
		jdbcTemplate.update(updateSql, parameters);
		return this.findById(deliveryJobId);
	}
}
