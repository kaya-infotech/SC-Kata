/**
 * 
 */
package com.sc.kata.moneytransfer.app;

import java.math.BigDecimal;
import java.util.Collection;

import com.sc.kata.moneytransfer.account.Account;
import com.sc.kata.moneytransfer.account.IAccount;
import com.sc.kata.moneytransfer.audit.ITransactionHistory;
import com.sc.kata.moneytransfer.audit.TransactionHistoryLog;
import com.sc.kata.moneytransfer.exception.InsufficientBalanceException;

/**
 * @author anil
 *
 */
public class ClientApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IAccount payerAccount = new Account(new BigDecimal(140));
		payerAccount.setAccountNumber(123L);
		
		ITransactionHistory txHistory = new TransactionHistoryLog();
		
		payerAccount.setTransactionHistoryLog(txHistory);
		
		IAccount payeeAccount = new Account(new BigDecimal(230));
		payeeAccount.setAccountNumber(456L);
	
		System.out.println("************* Single Transaction *******************");		
		singleTransaction(payerAccount, txHistory, payeeAccount);
		System.out.println("************* Multiple Transactions *******************");		
		multipleTransactions(payerAccount, txHistory, payeeAccount);
		System.out.println("************* This should show Exception *******************");	
		singleTransactionWithException(payerAccount, txHistory, payeeAccount);
	}

	/**
	 * @param payerAccount
	 * @param txHistory
	 * @param payeeAccount
	 */
	private static void singleTransaction(IAccount payerAccount, ITransactionHistory txHistory, IAccount payeeAccount) {
		try {
			long payeeAccountNumber = payeeAccount.getAccountNumber();
			long payerAccountNumber = payerAccount.getAccountNumber();
			
			System.out.println("Payer Account # " + payerAccountNumber + " with balance " + payerAccount.getAccountBalance());
			System.out.println("Payee Account # " + payeeAccountNumber + " with balance " + payeeAccount.getAccountBalance());
			
			System.out.println("Transferring 40 from Payer Account # :" + payerAccountNumber);
			
			payerAccount.transfer(new BigDecimal(40), payeeAccount);

			// query the transaction log for these accounts
			Collection<String> payerTxLogs = txHistory.getTransactionHistory(payerAccountNumber);
			Collection<String> payeeTxLogs = txHistory.getTransactionHistory(payeeAccountNumber);

			System.out.println("*************");
			System.out.println("Transaction History for account number " + payerAccountNumber);
			
			for(String logStatement : payerTxLogs) {
				System.out.println(logStatement);
			}

			System.out.println("*************");
			System.out.println("Transaction History for account number " + payeeAccountNumber);
			
			for(String logStatement : payeeTxLogs) {
				System.out.println(logStatement);
			}
			System.out.println("*************");

			System.out.println("Payer Account Balance now " + payerAccount.getAccountBalance().toString());
			System.out.println("Payee Account Balance now " + payeeAccount.getAccountBalance().toString());
			
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @param payerAccount
	 * @param txHistory
	 * @param payeeAccount
	 */
	private static void multipleTransactions(IAccount payerAccount, ITransactionHistory txHistory, IAccount payeeAccount) {
		try {
			long payeeAccountNumber = payeeAccount.getAccountNumber();
			long payerAccountNumber = payerAccount.getAccountNumber();
			
			System.out.println("Payer Account # " + payerAccountNumber + " with balance " + payerAccount.getAccountBalance());
			System.out.println("Payee Account # " + payeeAccountNumber + " with balance " + payeeAccount.getAccountBalance());
			
			System.out.println("Transferring 40 from Payer Account # :" + payerAccountNumber);
			
			payerAccount.transfer(new BigDecimal(40), payeeAccount);
			
			System.out.println("Transferring another 30 from Payer Account # :" + payerAccountNumber);
			
			payerAccount.transfer(new BigDecimal(30), payeeAccount);
			
			// query the transaction log for these accounts
			Collection<String> payerTxLogs = txHistory.getTransactionHistory(payerAccountNumber);
			Collection<String> payeeTxLogs = txHistory.getTransactionHistory(payeeAccountNumber);

			System.out.println("*************");
			System.out.println("Transaction History for account number " + payerAccountNumber);
			
			for(String logStatement : payerTxLogs) {
				System.out.println(logStatement);
			}

			System.out.println("*************");
			System.out.println("Transaction History for account number " + payeeAccountNumber);
			
			for(String logStatement : payeeTxLogs) {
				System.out.println(logStatement);
			}
			System.out.println("*************");

			System.out.println("Payer Account Balance now " + payerAccount.getAccountBalance().toString());
			System.out.println("Payee Account Balance now " + payeeAccount.getAccountBalance().toString());
			
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * @param payerAccount
	 * @param txHistory
	 * @param payeeAccount
	 */
	private static void singleTransactionWithException(IAccount payerAccount, ITransactionHistory txHistory, IAccount payeeAccount) {
		try {
			long payeeAccountNumber = payeeAccount.getAccountNumber();
			long payerAccountNumber = payerAccount.getAccountNumber();
			
			System.out.println("Payer Account # " + payerAccountNumber + " with balance " + payerAccount.getAccountBalance());
			System.out.println("Payee Account # " + payeeAccountNumber + " with balance " + payeeAccount.getAccountBalance());
			
			System.out.println("Transferring 40 from Payer Account # :" + payerAccountNumber);
			
			payerAccount.transfer(new BigDecimal(40), payeeAccount);

			// query the transaction log for these accounts
			Collection<String> payerTxLogs = txHistory.getTransactionHistory(payerAccountNumber);
			Collection<String> payeeTxLogs = txHistory.getTransactionHistory(payeeAccountNumber);

			System.out.println("*************");
			System.out.println("Transaction History for account number " + payerAccountNumber);
			
			for(String logStatement : payerTxLogs) {
				System.out.println(logStatement);
			}

			System.out.println("*************");
			System.out.println("Transaction History for account number " + payeeAccountNumber);
			
			for(String logStatement : payeeTxLogs) {
				System.out.println(logStatement);
			}
			System.out.println("*************");

			System.out.println("Payer Account Balance now " + payerAccount.getAccountBalance().toString());
			System.out.println("Payee Account Balance now " + payeeAccount.getAccountBalance().toString());
			
		} catch (InsufficientBalanceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
