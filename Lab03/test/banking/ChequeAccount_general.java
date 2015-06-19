package banking;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import banking.ChequeAccount;

public class ChequeAccount_general {

	@Test
	public void testGetLastChequeDate() {
		ChequeAccount testChequeAccount = new ChequeAccount(30, 0);
		Calendar now = Calendar.getInstance();
		
		try {
			testChequeAccount.chequeWithdraw(30);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		if (now.get(Calendar.HOUR_OF_DAY) != 23 && now.get(Calendar.MINUTE) != 59) {
			assertEquals(now.get(Calendar.DATE),
				testChequeAccount.getLastChequeDate().get(Calendar.DATE));
		} else {
			fail("Do not run testGetLastChequeDate between days.");
		}
		
		try {
			testChequeAccount.withdraw(200);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		Calendar now2 = Calendar.getInstance();
		if (now.get(Calendar.HOUR_OF_DAY) != 23 && now.get(Calendar.MINUTE) != 59) {
			assertEquals(now2.get(Calendar.DATE),
				testChequeAccount.getLastChequeDate().get(Calendar.DATE));
		} else {
			fail("Do not run testGetLastChequeDate between days.");
		}
	}

	@Test
	public void testGetMonthlyCheques() {
		ChequeAccount testChequeAccount = new ChequeAccount(100, 0);
		
		try {
			testChequeAccount.chequeWithdraw(30);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(1, testChequeAccount.getMonthlyCheques());
		
		try {
			testChequeAccount.chequeWithdraw(30);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(2, testChequeAccount.getMonthlyCheques());
		
		try {
			testChequeAccount.chequeWithdraw(30);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(3, testChequeAccount.getMonthlyCheques());

		
		try {
			testChequeAccount.chequeWithdraw(30);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(3, testChequeAccount.getMonthlyCheques());
	}

}