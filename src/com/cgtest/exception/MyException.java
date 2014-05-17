package com.cgtest.exception;

public class MyException extends Exception {

	private static final long serialVersionUID = 5052449475465771187L;

	public MyException() {
		super();
	}

	public MyException(String msg) {
		super(msg);

	}

	public MyException(String msg, Throwable cause) {
		super(msg, cause);

	}

	public MyException(Throwable cause) {
		super(cause);
	}
}
