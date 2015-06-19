package banking;

import static org.junit.Assert.*;
import org.junit.*;
import banking.BankAccount;
import java.util.Calendar;

public class BankAccount_general {

	@Test
	public void testGetLastWithdrawalDate() {
		BankAccount testBankAccount = new BankAccount(500);
		try {
			testBankAccount.withdraw(300);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		Calendar now = Calendar.getInstance();
		if (now.get(Calendar.HOUR_OF_DAY) != 23 && now.get(Calendar.MINUTE) != 59) {
			assertEquals(now.get(Calendar.DATE),
				testBankAccount.getLastWithdrawalDate().get(Calendar.DATE));
		} else {
			fail("Do not run testGetLastWithdrawalDate between days.");
		}
		
		try {
			testBankAccount.withdraw(200);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		Calendar now2 = Calendar.getInstance();
		if (now.get(Calendar.HOUR_OF_DAY) != 23 && now.get(Calendar.MINUTE) != 59) {
			assertEquals(now2.get(Calendar.DATE),
				testBankAccount.getLastWithdrawalDate().get(Calendar.DATE));
		} else {
			fail("Do not run testGetLastWithdrawalDate between days.");
		}
	}

	@Test
	public void testGetBalance() {
		BankAccount testBankAccount = new BankAccount(500);
		assertEquals(500, testBankAccount.getBalance());
		
		try {
			testBankAccount.withdraw(300);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(200, testBankAccount.getBalance());
		
		try {
			testBankAccount.withdraw(200);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(0, testBankAccount.getBalance());
	}

	@Test
	public void testGetDailyWithdrawals() {
		BankAccount testBankAccount = new BankAccount(500);
		try {
			testBankAccount.withdraw(300);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(300, testBankAccount.getBalance());
	}

}
