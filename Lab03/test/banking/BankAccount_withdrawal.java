package banking;

import static org.junit.Assert.*;
import org.junit.*;
import banking.BankAccount;

public class BankAccount_withdrawal {
	
	@Test
	public void withdraw_normal_balancedecrease() {
		BankAccount testBankAccount = new BankAccount(100);
		try {
			testBankAccount.withdraw(100);
		} catch (Exception e) {
			fail();
		}
		assertEquals(0, testBankAccount.getBalance());
	}

	@Test
	public void withdraw_nofunds_exception() {
		BankAccount testBankAccount = new BankAccount(0);
		try {
			testBankAccount.withdraw(100);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(0, testBankAccount.getBalance());
	}

	@Test
	public void withdraw_insufficientfunds_exception() {
		BankAccount testBankAccount = new BankAccount(50);
		try {
			testBankAccount.withdraw(100);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(50, testBankAccount.getBalance());
	}
	
	@Test
	public void withdraw_negativeval_exception() {
		BankAccount testBankAccount = new BankAccount(600);
		try {
			testBankAccount.withdraw(-100);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(600, testBankAccount.getBalance());
	}
	
	@Test
	public void withdraw_exceeddailylimit_exception() {
		BankAccount testBankAccount = new BankAccount(1000);
		try {
			testBankAccount.withdraw(900);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(1000, testBankAccount.getBalance());
	}
}
