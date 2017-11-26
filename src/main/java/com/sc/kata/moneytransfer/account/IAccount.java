package com.sc.kata.moneytransfer.account;

import java.math.BigDecimal;

import com.sc.kata.moneytransfer.audit.ITransactionHistory;
import com.sc.kata.moneytransfer.exception.InsufficientBalanceException;

public interface IAccount {

	public BigDecimal getAccountBalance();
	
	public long getAccountNumber();
	
	public void setAccountNumber(long accountNumber);
	
	public void setTransactionHistoryLog(ITransactionHistory txHistory);

	public void deposit(BigDecimal bigDecimal);

	public void withdraw(BigDecimal bigDecimal) throws InsufficientBalanceException;

	public void transfer(BigDecimal bigDecimal, IAccount payeeAccount) throws InsufficientBalanceException;
}
