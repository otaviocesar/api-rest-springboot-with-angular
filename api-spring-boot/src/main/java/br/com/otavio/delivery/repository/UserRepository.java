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

import br.com.otavio.delivery.model.User;

@Repository
public class UserRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TUS_ID_USER as id, TUS_NAME as name, TUS_MAIL_ADDRESS as mail, TUS_ADDRESS as address "
			+ "FROM DELIVERY_SERVICE.TAB_USER";
	
	private final BeanPropertyRowMapper<User> userRowMapper = BeanPropertyRowMapper
		.newInstance(User.class);
	
	public UserRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public User save(User user) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_USER(TUS_ID_USER, TUS_NAME, TUS_MAIL_ADDRESS, TUS_ADDRESS) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_USER.NEXTVAL, :name, :mail, :address)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", user.getName());
		parameters.addValue("mail", user.getMail());
		parameters.addValue("address", user.getAddress());
		String[] idField = {
			"TUS_ID_USER"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		user.setId(holder.getKey().longValue());
		return user;
	}
	
	public List<User> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, userRowMapper);
	}
	
	public Optional<User> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TUS_ID_USER = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			User findObj = jdbcTemplate.queryForObject(selectSql, parameters, userRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}

	public Optional<User> findByName(User user) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE lower(TUS_NAME) like :name";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", user.getName());
		try {
			User findObj = jdbcTemplate.queryForObject(selectSql, parameters, userRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
}
