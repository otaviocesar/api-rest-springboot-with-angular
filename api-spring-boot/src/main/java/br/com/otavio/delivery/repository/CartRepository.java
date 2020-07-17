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

import br.com.otavio.delivery.model.Cart;

@Repository
public class CartRepository {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TCA_ID_CART as id, TCA_TUS_ID_USER as idUser "
			+ "FROM DELIVERY_SERVICE.TAB_CART";
	
	private final BeanPropertyRowMapper<Cart> cartRowMapper = BeanPropertyRowMapper
		.newInstance(Cart.class);
	
	public CartRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public Cart save(Cart cart) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_CART(TCA_ID_CART, TCA_TUS_ID_USER) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_CART.NEXTVAL, :idUser)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idUser", cart.getIdUser());
		String[] idField = {
			"TCA_ID_CART"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		cart.setId(holder.getKey().longValue());
		return cart;
	}
	
	public List<Cart> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, cartRowMapper);
	}
	
	public Optional<Cart> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TCA_ID_CART = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			Cart findObj = jdbcTemplate.queryForObject(selectSql, parameters, cartRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}

	public Optional<Cart> findByIdUser(Cart cart) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE lower(TCA_TUS_ID_USER) like :idUser";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idUser", cart.getIdUser());
		try {
			Cart findObj = jdbcTemplate.queryForObject(selectSql, parameters, cartRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
}
