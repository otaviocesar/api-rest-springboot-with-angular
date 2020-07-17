
package br.com.otavio.delivery.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "status", "job-key", "send-date" })
public class DeliveryJobResponse implements Serializable {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("status")
	private Object status;
	@JsonProperty("job-key")
	private Long jobKey;
	@JsonProperty("send-date")
	private String sendDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 620117719986607038L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public DeliveryJobResponse() {
	}

	/**
	 *
	 * @param sendDate
	 * @param jobKey
	 * @param id
	 * @param status
	 */
	public DeliveryJobResponse(Long id, Object status, Long jobKey, String sendDate) {
		super();
		this.id = id;
		this.status = status;
		this.jobKey = jobKey;
		this.sendDate = sendDate;
	}

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Long id) {
		this.id = id;
	}


	@JsonProperty("status")
	public Object getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Object status) {
		this.status = status;
	}

	@JsonProperty("job-key")
	public Long getJobKey() {
		return jobKey;
	}

	@JsonProperty("job-key")
	public void setJobKey(Long jobKey) {
		this.jobKey = jobKey;
	}


	@JsonProperty("send-date")
	public String getSendDate() {
		return sendDate;
	}

	@JsonProperty("send-date")
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

}