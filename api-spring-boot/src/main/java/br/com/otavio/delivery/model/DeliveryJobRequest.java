package br.com.otavio.delivery.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "jobKey", "sendDate" })
public class DeliveryJobRequest implements Serializable {

	@JsonProperty("jobKey")
	private Long jobKey;
	@JsonProperty("sendDate")
	private String sendDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 930976601967670538L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public DeliveryJobRequest() {
	}

	/**
	 *
	 * @param sendDate
	 * @param jobKey
	 */
	public DeliveryJobRequest(Long jobKey, String sendDate) {
		super();
		this.jobKey = jobKey;
		this.sendDate = sendDate;
	}

	@JsonProperty("jobKey")
	public Long getJobKey() {
		return jobKey;
	}

	@JsonProperty("jobKey")
	public void setJobKey(Long jobKey) {
		this.jobKey = jobKey;
	}

	@JsonProperty("sendDate")
	public String getSendDate() {
		return sendDate;
	}

	@JsonProperty("sendDate")
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
}