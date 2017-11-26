/**
 * 
 */
package com.sc.kata.moneytransfer.exception;

/**
 * @author anil
 *
 */
public class InsufficientBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10234589898999L;
	
	public InsufficientBalanceException (String message) {
		super(message);
	}
	
}
