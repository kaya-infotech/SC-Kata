/**
 * 
 */
package com.sc.kata.moneytransfer.audit;

import java.util.Collection;

/**
 * @author anil
 *
 */
public interface ITransactionHistory {

	public Collection<String> getTransactionHistory(long accountNummber);
	
	public void addTransactionLog(long accountNumber, String txLog);
}
