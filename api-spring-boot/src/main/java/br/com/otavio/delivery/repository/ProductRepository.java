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

import br.com.otavio.delivery.model.Product;

@Repository
public class ProductRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TPD_ID_PRODUCT as id, TPD_NAME as name, TPD_DETAILS as details, TPD_PRICE as price "
			+ "FROM DELIVERY_SERVICE.TAB_PRODUCT";

	private final BeanPropertyRowMapper<Product> productRowMapper = BeanPropertyRowMapper
		.newInstance(Product.class);

	public ProductRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Product save(Product product) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_PRODUCT(TPD_ID_PRODUCT, TPD_NAME, TPD_DETAILS, TPD_PRICE) "
				+ "VALUES (DELIVERY_SERVICE.SEQ_PRODUCT.NEXTVAL, :name, :details, :price)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", product.getName());
		parameters.addValue("details", product.getDetails());
		parameters.addValue("price", product.getPrice());
		String[] idField = {
			"TPD_ID_PRODUCT"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		product.setId(holder.getKey().longValue());
		return product;
	}

	public List<Product> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, productRowMapper);
	}

	public Optional<Product> findById(Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TPD_ID_PRODUCT = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		try {
			Product findObj = jdbcTemplate.queryForObject(selectSql, parameters, productRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	public Optional<Product> findByName(Product product) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE lower(TPD_NAME) like :name";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", product.getName());
		try {
			Product findObj = jdbcTemplate.queryForObject(selectSql, parameters, productRowMapper);
			return Optional.of(findObj);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
}
