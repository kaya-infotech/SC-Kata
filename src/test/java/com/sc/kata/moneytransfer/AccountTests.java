/**
 * 
 */
package com.sc.kata.moneytransfer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

/**
 * @author anil
 *
 */
public class AccountTests {

	private IAccount payerAccount;
	
	private IAccount payeeAccount;
	
	private ITransactionHistory txHistory;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Runs before each test
		payerAccount = new Account(new BigDecimal(100.00));
		payeeAccount = new Account(new BigDecimal(400.00));
		payerAccount.setAccountNumber(123L);
		payeeAccount.setAccountNumber(456L);
		
		// set up txHistory
		txHistory = new TransactionHistoryLog();
		payerAccount.setTransactionHistoryLog(txHistory);
		payeeAccount.setTransactionHistoryLog(txHistory);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		payerAccount = null;
		payeeAccount = null;
		txHistory = null;
	}

	@Test
	public void testDepositMoneyIntoAccount() {
		payerAccount.deposit(new BigDecimal(100));
		assertThat(payerAccount.getAccountBalance(), is(new BigDecimal(200.00)));
	}
	
	@Test 
	public void testWithdrawlFromAccountWhenThereIsEnoughBalance() throws InsufficientBalanceException {
		payerAccount.withdraw(new BigDecimal(50));
		assertThat(payerAccount.getAccountBalance(), is(new BigDecimal(50.00)));
	}

	@Test (expected = InsufficientBalanceException.class)
	public void testWithdrawlFromAccountWhenThereIsNotEnoughBalance() throws InsufficientBalanceException {
		payerAccount.withdraw(new BigDecimal(200));
	}
	
	@Test
	public void testAccountBalance() {
		assertThat(payerAccount.getAccountBalance(), is(new BigDecimal(100.00)));
		assertThat(payeeAccount.getAccountBalance(), is(new BigDecimal(400.00)));
	}

	@Test
	public void testAccountTransferWhenPayerHasSufficientBalance() throws InsufficientBalanceException {
		payerAccount.transfer(new BigDecimal(50.00), payeeAccount);
		assertThat(payerAccount.getAccountBalance(), is(new BigDecimal(50.00)));
		assertThat(payeeAccount.getAccountBalance(), is(new BigDecimal(450.00)));
		
	}

	@Test (expected = InsufficientBalanceException.class)
	public void testAccountTransferWhenPayerDoesNotHaveSufficientBalance() throws InsufficientBalanceException {
		payerAccount.transfer(new BigDecimal(150.00), payeeAccount);
		assertThat(payerAccount.getAccountBalance(), is(new BigDecimal(100.00)));
		assertThat(payeeAccount.getAccountBalance(), is(new BigDecimal(400.00)));
		
	}
	
}
