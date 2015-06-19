package banking;
import java.util.Calendar;

public class ChequeAccount extends BankAccount {
	private static final int MONTHLY_CHEQUE_LIMIT = 5;
	private int monthlyCheques;
	private Calendar lastChequeDate;

	/**
	 * Creates a new ChequeAccount given a balance and monthly cheques used.
	 * @param balance balance of the ChequeAccount
	 * @param monthlyCheques number of cheques used so far this month
	 */
	public ChequeAccount (int balance, int monthlyCheques) {
		super(balance);
		this.monthlyCheques = monthlyCheques;
		Calendar now = Calendar.getInstance();
		this.lastChequeDate = now;
	}

	/**
	 * Returns the date when the last cheque was issued.
	 * @return date when the last cheque was issued as a Calendar
	 */
	public Calendar getLastChequeDate () {
		return lastChequeDate;
	}

	/**
	 * Returns the number of cheques used this month.
	 * @return the number of cheques used this month
	 */
	public int getMonthlyCheques () {
		return monthlyCheques;
	}
	
	/**
	 * Uses a cheque to withdraw from ChequeAccount.
	 * @precondition withdrawalValue is positive, MONTHLY_CHEQUE_LIMIT not reached, balance has sufficient funds.
	 * @postcondition balance decreases.
	 * @param withdrawalValue value to withdraw using a cheque
	 * @throws Exception if insufficient funds or exceeded daily withdrawal limit or if exceeded monthly cheque limit
	 */
	public void chequeWithdraw (int withdrawalValue) throws Exception {
		// Reset monthlyCheques if previous cheque was issued last month. 
		Calendar today = Calendar.getInstance();
		if ((getLastChequeDate().get(Calendar.MONTH)) < today.get(Calendar.MONTH) || 
				(getLastChequeDate().get(Calendar.YEAR)) < today.get(Calendar.YEAR)) {
			this.monthlyCheques = 0;
		}
		
		// Check if withdrawalValue is positive.
		if (withdrawalValue < 0) {
			throw new Exception("Withdrawal cancelled: Value to withdraw must be positive");
		// Check if MONTHLY_CHEQUE_LIMIT has been reached.
		} else if ((1 + this.monthlyCheques) > MONTHLY_CHEQUE_LIMIT) {
			throw new Exception("Withdrawal cancelled: exceeded monthly cheque limit (" + MONTHLY_CHEQUE_LIMIT + ")");
		} else {
			withdraw(withdrawalValue);
			this.lastChequeDate = today;
			monthlyCheques += 1;
		}
	}
}
