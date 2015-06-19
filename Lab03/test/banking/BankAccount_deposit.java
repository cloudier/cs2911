package banking;

import static org.junit.Assert.*;
import org.junit.*;
import banking.BankAccount;

public class BankAccount_deposit {

	@Test
	public void deposit_positivevalue_balanceincrease() {
		BankAccount testBankAccount = new BankAccount(0);
		try {
			testBankAccount.deposit(100);
		} catch (Exception e) {
			fail("Exception thrown");
		}
		assertEquals(100, testBankAccount.getBalance());
	}
	
	@Test
	public void deposit_negativevalue_balanceincrease() {
		BankAccount testBankAccount = new BankAccount(0);
		try {
			testBankAccount.deposit(-100);
		} catch (Exception e) {
			assertEquals(0, testBankAccount.getBalance());
		}
	}
}