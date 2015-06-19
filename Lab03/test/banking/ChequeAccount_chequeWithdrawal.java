package banking;

import static org.junit.Assert.*;
import org.junit.Test;
import banking.ChequeAccount;

public class ChequeAccount_chequeWithdrawal {

	@Test
	public void chequeWithdraw_normal_balancedecrease() {
		ChequeAccount testChequeAccount = new ChequeAccount(900,0);
		try {
			testChequeAccount.chequeWithdraw(10);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(890, testChequeAccount.getBalance());
		assertEquals(1, testChequeAccount.getMonthlyCheques());
	}

	@Test
	public void chequeWithdraw_nofunds_exception() {
		ChequeAccount testChequeAccount = new ChequeAccount(0,0);
		try {
			testChequeAccount.chequeWithdraw(100);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(0, testChequeAccount.getBalance());
		assertEquals(0, testChequeAccount.getMonthlyCheques());
	}

	@Test
	public void chequeWithdraw_insufficientfunds_exception() {
		ChequeAccount testChequeAccount = new ChequeAccount(50,0);
		try {
			testChequeAccount.chequeWithdraw(100);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(50, testChequeAccount.getBalance());
		assertEquals(0, testChequeAccount.getMonthlyCheques());
	}
	
	@Test
	public void chequeWithdraw_negativeval_exception() {
		ChequeAccount testChequeAccount = new ChequeAccount(100,0);
		try {
			testChequeAccount.chequeWithdraw(-100);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(100, testChequeAccount.getBalance());
		assertEquals(0, testChequeAccount.getMonthlyCheques());
	}
	
	@Test
	public void chequeWithdraw_exceedmonthlylimit_exception() {
		ChequeAccount testChequeAccount = new ChequeAccount(500,5);
		try {
			testChequeAccount.chequeWithdraw(100);
		} catch (Exception e) {
			// Success!
		}
		assertEquals(500, testChequeAccount.getBalance());
		assertEquals(5, testChequeAccount.getMonthlyCheques());
	}
}
