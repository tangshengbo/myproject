package com.tangshengbo.util.baofoo.http;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class SimpleHttpResponse {

	private int statusCode;
	private String entityString;
	private String errorMessage;

	/**
	 * @param statusCode
	 * @param entityString
	 */
	public SimpleHttpResponse(int statusCode, String entityString,
			String errorMessage) {
		this.statusCode = statusCode;
		this.entityString = entityString;
		this.errorMessage = errorMessage;
	}

	/**
	 * 是否成功
	 *
	 * @return
	 */
	public boolean isRequestSuccess() {
		return HttpUtil.isRequestSuccess(statusCode);
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @return the entityString
	 */
	public String getEntityString() {
		return entityString;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
