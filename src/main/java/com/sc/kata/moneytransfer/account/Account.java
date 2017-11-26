package com.sc.kata.moneytransfer.account;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sc.kata.moneytransfer.audit.ITransactionHistory;
import com.sc.kata.moneytransfer.audit.TransactionHistoryLog;
import com.sc.kata.moneytransfer.audit.TransactionHistoryStore;
import com.sc.kata.moneytransfer.exception.InsufficientBalanceException;

public class Account implements IAccount {

	private BigDecimal accountBalance = new BigDecimal(0);
	private long accountNumber;
	private String accountHolder;
	private TransactionHistoryStore transactionHistory;
	private ITransactionHistory txHistoryLog;
	
	public Account() {
		this.transactionHistory = new TransactionHistoryStore();
	}
	
	public Account (BigDecimal initialDeposit) {
		this.transactionHistory = new TransactionHistoryStore();
		accountBalance = accountBalance.add(initialDeposit); 
	}

	@Override
	public void deposit(BigDecimal depositAmount) {
		accountBalance = accountBalance.add(depositAmount);
	}

	@Override
	public void withdraw(BigDecimal withdrawlAmount) throws InsufficientBalanceException {

		if (withdrawlAmount.doubleValue() > getAccountBalance().doubleValue()) {
			throw new InsufficientBalanceException("There is not enough Balance available for this transaction");
		}
		accountBalance = accountBalance.subtract(withdrawlAmount);
	}

	@Override
	public void transfer(BigDecimal transferAmount, IAccount payeeAccount) throws InsufficientBalanceException {		
		this.withdraw(transferAmount);
		payeeAccount.deposit(transferAmount);

		createAuditLog(transferAmount, payeeAccount);
	}

	public void setTransactionHistoryLog(ITransactionHistory txHistory) {
		this.txHistoryLog = txHistory;

	}
	/**
	 * @param transferAmount
	 * @param payeeAccount
	 */
	private void createAuditLog(BigDecimal transferAmount, IAccount payeeAccount) {
		// set this transaction in Txhistory
		
		this.transactionHistory.setTimestamp(Calendar.getInstance().getTimeInMillis());
		this.transactionHistory.setTransactionAmount(transferAmount);
		this.transactionHistory.setTransactionFromAccount(this.getAccountNumber());
		this.transactionHistory.setTransactionToAccount(payeeAccount.getAccountNumber());
		this.transactionHistory.setTransactionReference("Money for Dinner");
				
		txHistoryLog.addTransactionLog(this.getAccountNumber(),this.transactionHistory.generatePayerTransactionHistoryLog());
		txHistoryLog.addTransactionLog(payeeAccount.getAccountNumber(),this.transactionHistory.generatePayeeTransactionHistoryLog());
		
	}


	// getters and setters

	@Override
	public BigDecimal getAccountBalance() {
		return accountBalance;	
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}


}
