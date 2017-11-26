/**
 * 
 */
package com.sc.kata.moneytransfer.audit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anil
 *
 */
public class TransactionHistoryLog implements ITransactionHistory {

	private Map<Long, List<String>> txHistoryStore = new HashMap<Long, List<String>>();
	
	/**
	 * 
	 */
	public TransactionHistoryLog() {
		
	}

	/* (non-Javadoc)
	 * @see com.sc.kata.moneytransfer.audit.ITransactionHistory#getTransactionHistory(long)
	 */
	@Override
	public Collection<String> getTransactionHistory(long accountNumber) {
		return this.txHistoryStore.get(accountNumber);
	}

	@Override
	public void addTransactionLog(long accountNumber, String txLog) {
		
		List<String> txLogHistory = this.txHistoryStore.get(accountNumber);
		
		if(txLogHistory != null ) {
			txLogHistory.add(txLog);
		} else {
			txLogHistory = new ArrayList<String>();
			txLogHistory.add(txLog);
		}
		
		this.txHistoryStore.put(accountNumber, txLogHistory);
	}
	
	

}
