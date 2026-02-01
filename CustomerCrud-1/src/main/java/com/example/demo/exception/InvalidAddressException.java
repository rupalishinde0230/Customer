package com.example.demo.exception;

public class InvalidAddressException extends RuntimeException {
	public  InvalidAddressException (String message)
	{
		super(message);
	}

}
