package banking;
import java.util.Calendar;

public class BankAccount {
	
	private static final int DAILY_WITHDRAWAL_LIMIT = 800;
	private int balance;
	private int dailyWithdrawals;
	private Calendar lastWithdrawalDate;
	
	/**
	 * Creates a new BankAccount given a balance.
	 * @param balance
	 * @return BankAccount
	 */
	public BankAccount (int balance) {
		super();
		this.balance = balance;
		this.dailyWithdrawals = 0;
		Calendar now = Calendar.getInstance();
		this.lastWithdrawalDate = now;
	}
	
	/**
	 * Gets the date of the last withdrawal from the BankAccount.
	 * @return last withdrawal date of the BankAccount as a Calendar
	 */
	public Calendar getLastWithdrawalDate () {
		return lastWithdrawalDate;
	}

	/**
	 * Gets the current balance of the BankAccount.
	 * @return current balance as an integer
	 */
	public int getBalance () {
		return balance;
	}

	/**
	 * Gets the total withdrawals from the BankAccount today.
	 * @return total withdrawals as an integer
	 */
	public int getDailyWithdrawals () {
		return dailyWithdrawals;
	}
	
	/**
	 * Deposits money into the BankAccount.
	 * @precondition depositValue is positive.
	 * @postcondition balance increases.
	 * This post-condition must be satisfied because the only operation in this method is addition.
	 * @param depositValue The value to deposit in the BankAccount
	 */
	public void deposit (int depositValue) throws Exception {
		if (depositValue < 0) {
			throw new Exception("Deposit cancelled: Value to deposit must be positive.");
		}
		this.balance = this.balance + depositValue;
	}

	/**
	 * Withdraws money from the BankAccount.
	 * @precondition withdrawalValue is positive, balance is positive, DAILY_WITHDRAWAL_LIMIT not reached.
	 * @postcondition balance decreases.
	 * @param withdrawalValue Amount to withdraw from the BankAccount
	 * @throws Exception due to either insufficient funds or exceeding daily withdrawal limit
	 */
	public void withdraw (int withdrawalValue) throws Exception {
		/*
		 * How are limits enforced?
		 * Last withdrawal date is used to determine when dailyWithdrawals is reset.
		 * There are checks for whether there are sufficient funds to withdraw, whether the value to withdraw is positive and
		 * whether the daily limit has been exceeded. These will throw exceptions.
		 */
		// Check when last withdrawal occurred. If on a previous day, reset dailyWithdrawals to 0.
		Calendar today = Calendar.getInstance();
		if ((getLastWithdrawalDate().get(Calendar.DATE)) < today.get(Calendar.DATE) ||
				(getLastWithdrawalDate().get(Calendar.MONTH)) < today.get(Calendar.MONTH) ||
				(getLastWithdrawalDate().get(Calendar.YEAR)) < today.get(Calendar.YEAR)) {
			this.dailyWithdrawals = 0;
		}
		
		// Check withdrawalValue is positive.
		if (withdrawalValue < 0) {
			throw new Exception("Withdrawal cancelled: Value to withdraw must be positive");
		// Check balance has sufficient funds for withdrawal.
		} if ((balance - withdrawalValue) < 0) {
			throw new Exception("Withdrawal cancelled: Insufficient funds");
		// Check that daily withdrawal limit is not exceeded.
		} else if ((withdrawalValue + this.dailyWithdrawals) > DAILY_WITHDRAWAL_LIMIT) {
			throw new Exception("Withdrawal cancelled: exceeded daily withdrawal limit ($" + DAILY_WITHDRAWAL_LIMIT +")");
		} else {
			this.balance = this.balance - withdrawalValue;
			this.lastWithdrawalDate = today;
			dailyWithdrawals += withdrawalValue;
		}
	}
}
