package com.sc.kata.moneytransfer;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sc.kata.moneytransfer.account.Account;
import com.sc.kata.moneytransfer.account.IAccount;
import com.sc.kata.moneytransfer.audit.ITransactionHistory;
import com.sc.kata.moneytransfer.audit.TransactionHistoryLog;
import com.sc.kata.moneytransfer.exception.InsufficientBalanceException;

public class TransactionHistoryTests {

	private IAccount payerAccount;
	
	private IAccount payeeAccount;
	
	private ITransactionHistory txHistory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		payerAccount = new Account(new BigDecimal(100.00));
		payerAccount.setAccountNumber(123L);
		
		payeeAccount = new Account(new BigDecimal(400.00));
		payeeAccount.setAccountNumber(456L);
	
		// set up txHistory
		txHistory = new TransactionHistoryLog();
		payerAccount.setTransactionHistoryLog(txHistory);
		payeeAccount.setTransactionHistoryLog(txHistory);		
	}

	@After
	public void tearDown() throws Exception {
		payerAccount = null;
		payeeAccount = null;	
		txHistory = null;		
	}

	@Test
	public void testThatTransactionIsLoggedInHistory() throws InsufficientBalanceException {
		payerAccount.transfer(new BigDecimal(50.00), payeeAccount);
		assertThat(txHistory.getTransactionHistory(payerAccount.getAccountNumber()).size(), is(1) );
		assertThat(txHistory.getTransactionHistory(payeeAccount.getAccountNumber()).size(), is(1) );
	}

	@Test
	public void testThatMultipleTransactionsAreLoggedInHistory() throws InsufficientBalanceException {
		payerAccount.transfer(new BigDecimal(10.00), payeeAccount);
		payerAccount.transfer(new BigDecimal(20.00), payeeAccount);

		assertThat(txHistory.getTransactionHistory(payerAccount.getAccountNumber()).size(), is(2) );
		assertThat(txHistory.getTransactionHistory(payeeAccount.getAccountNumber()).size(), is(2) );
	}
	
}
