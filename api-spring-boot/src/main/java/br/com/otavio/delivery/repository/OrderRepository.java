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

import br.com.otavio.delivery.model.Order;

@Repository
public class OrderRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TOR_ID_ORDER as id, TOR_TCA_ID_CART as idCart, TOR_TPD_ID_PRODUCT as idProduct "
			+ "FROM DELIVERY_SERVICE.TAB_ORDER";

	private final BeanPropertyRowMapper<Order> orderRowMapper = BeanPropertyRowMapper
		.newInstance(Order.class);

	public OrderRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Order save(Order order) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_ORDER(TOR_ID_ORDER, TOR_TCA_ID_CART, TOR_TPD_ID_PRODUCT) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_ORDER.NEXTVAL, :idCart, :idProduct)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idCart", order.getIdCart());
		parameters.addValue("idProduct", order.getIdProduct());
		String[] idField = {
			"TOR_ID_ORDER"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		order.setId(holder.getKey().longValue());
		return order;
	}

	public List<Order> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, orderRowMapper);
	}

	public Optional<Order> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TOR_ID_ORDER = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			Order findObj = jdbcTemplate.queryForObject(selectSql, parameters, orderRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	public Optional<Order> findByIdCart(Order order) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE lower(TOR_TCA_ID_CART) like :idCart";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idCart", order.getIdCart());
		try {
			Order findObj = jdbcTemplate.queryForObject(selectSql, parameters, orderRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
}
