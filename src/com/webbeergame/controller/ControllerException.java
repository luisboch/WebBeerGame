package com.webbeergame.controller;

/**
 * 
 * @author luis
 * Usado pelo GameController
 * 
 */
public class ControllerException extends Exception {

	private static final long serialVersionUID = 3034604531499802625L;

	public ControllerException() {
	}

	public ControllerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

}
