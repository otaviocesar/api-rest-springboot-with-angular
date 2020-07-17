package br.com.otavio.delivery.repository;

import java.io.ByteArrayInputStream;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;

import br.com.otavio.delivery.model.Attachment;

@Repository
public class AttachmentRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String ID_MESSAGE = "messageId";
	
	private static final String SELECT_QUERY_ALL_COLUMNS = "SELECT TAT_ID_ATTACHMENT as id,"
			+ " TAT_TMS_ID_MESSAGE as messageId, "
			+ "TAT_CONTENT as content, "
			+ "TAT_FILE_NAME as fileName, "
			+ "TAT_MIME_TYPE as typeFile "
			+ "FROM DELIVERY_SERVICE.TAB_ATTACHMENT";

	private final BeanPropertyRowMapper<Attachment> attachmentRowMapper = BeanPropertyRowMapper
		.newInstance(Attachment.class);

	public AttachmentRepository(@Autowired DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Attachment save(Attachment attachment, Long messageId) {
		KeyHolder holder = new GeneratedKeyHolder();

		String insertSql = "INSERT INTO DELIVERY_SERVICE.TAB_ATTACHMENT("
				+ "TAT_ID_ATTACHMENT, "
				+ "TAT_TMS_ID_MESSAGE, "
				+ "TAT_CONTENT,"
				+ "TAT_FILE_NAME,"
				+ "TAT_MIME_TYPE) "
				+ "VALUES ("
				+ "DELIVERY_SERVICE.SEQ_ATTACHMENT.NEXTVAL, "
				+ ":messageId, "
				+ ":content,"
				+ ":fileName,"
				+ ":typeFile)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(ID_MESSAGE, messageId);
		parameters.addValue(
			"content", new SqlLobValue(
				new ByteArrayInputStream(attachment.getContent()),
				attachment.getContent().length,
				new DefaultLobHandler()
			),
			Types.BLOB
		);
		parameters.addValue("fileName", attachment.getFileName());
		parameters.addValue("typeFile", attachment.getTypeFile());
		String[] idField = {
			"TAT_ID_ATTACHMENT"
		};
		jdbcTemplate.update(insertSql, parameters, holder, idField);
		attachment.setId(holder.getKey().longValue());
		return attachment;
	}
	
	public Optional<Attachment> findById(Long messageId, Long id) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " "
				+ " WHERE TAT_TMS_ID_MESSAGE = :messageId"
				+ " AND TAT_ID_ATTACHMENT = :id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(ID_MESSAGE, messageId);
		parameters.addValue("id", id);
		try {
			Attachment attachment = jdbcTemplate.queryForObject(
				selectSql,
				parameters,
				attachmentRowMapper
			);
			return Optional.of(attachment);
		} catch (EmptyResultDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	public List<Attachment> findByMessage(Long idMessage) {
		String selectSql = SELECT_QUERY_ALL_COLUMNS + " WHERE TAT_TMS_ID_MESSAGE = :messageId";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(ID_MESSAGE, idMessage);
		return jdbcTemplate.query(selectSql, parameters, attachmentRowMapper);
	}
	
	public List<Attachment> findAll() {
		String selectSql = SELECT_QUERY_ALL_COLUMNS;
		return jdbcTemplate.query(selectSql, attachmentRowMapper);
	}
}
