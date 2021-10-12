/**
 * TestRail API binding for Java (API v2, available since TestRail 3.0)
 *
 * Learn more:
 *
 * http://docs.gurock.com/testrail-api2/start
 * http://docs.gurock.com/testrail-api2/accessing
 *
 * Copyright Gurock Software GmbH. See license.md for details.
 */

package qa.tools.testraill.core;

import com.google.common.base.Preconditions;

public class APIException extends RuntimeException {
	private static final long serialVersionUID = -2131644110724458502L;
	private final int responseCode;

	/**
	 * @param responseCode the HTTP response code from the TestRail server
	 * @param error        the error message from TestRail service
	 */
	APIException(int responseCode, String error) {
		super(responseCode + " - " + error);
		this.responseCode = responseCode;
	}

	static class Builder {

		private int responseCode;
		private String error;

		public APIException build() {
			Preconditions.checkNotNull(responseCode);
			Preconditions.checkNotNull(error);
			return new APIException(responseCode, error);
		}

		@SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public Builder setResponseCode(final int responseCode) {
			this.responseCode = responseCode;
			return this;
		}

		@SuppressWarnings("all")
		@javax.annotation.Generated("lombok")
		public Builder setError(final String error) {
			this.error = error;
			return this;
		}
	}

	@SuppressWarnings("all")
	@javax.annotation.Generated("lombok")
	public int getResponseCode() {
		return this.responseCode;
	}
}
