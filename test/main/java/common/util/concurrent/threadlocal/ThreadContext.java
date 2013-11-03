package common.util.concurrent.threadlocal;

public class ThreadContext {
	private String userId;

	private Long transactionId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "userId:" + userId + ",transactionId:" + transactionId;
	}
}
