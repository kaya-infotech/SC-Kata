package com.sc.kata.moneytransfer.audit;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionHistoryStore {

	private long timestamp;
	
	private long transactionFromAccount;
	
	private long transactionToAccount;
	
	private BigDecimal transactionAmount;
	
	private String transactionReference;
	
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy") ;
	
	public TransactionHistoryStore() {
	
	}

	
	public String generatePayerTransactionHistoryLog() {
		StringBuffer txHistorylog = new StringBuffer();
		
		LocalDate dateNow = LocalDate.now();
		
		txHistorylog.append("Date " + dateNow.format(dateFormat) );
		txHistorylog.append(" Amount Sent to Account no. " + this.getTransactionToAccount());
		txHistorylog.append(" Amount Transferred " + this.getTransactionAmount().toString());
		txHistorylog.append(" Transaction Reference " + this.getTransactionReference());
		
		return txHistorylog.toString();
	}

	public String generatePayeeTransactionHistoryLog() {
		StringBuffer txHistorylog = new StringBuffer();
		
		LocalDate dateNow = LocalDate.now();
		
		txHistorylog.append("Date " + dateNow.format(dateFormat) );
		txHistorylog.append(" Amount received from Account no. " + this.getTransactionFromAccount());
		txHistorylog.append(" Amount received " + this.getTransactionAmount().toString());
		txHistorylog.append(" Transaction Reference " + this.getTransactionReference());
		
		return txHistorylog.toString();
	}
	
	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	public long getTransactionFromAccount() {
		return transactionFromAccount;
	}


	public void setTransactionFromAccount(long transactionFromAccount) {
		this.transactionFromAccount = transactionFromAccount;
	}


	public long getTransactionToAccount() {
		return transactionToAccount;
	}


	public void setTransactionToAccount(long transactionToAccount) {
		this.transactionToAccount = transactionToAccount;
	}


	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}


	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	public String getTransactionReference() {
		return transactionReference;
	}


	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	
	
	
}
