package assignmentsOOP;

public class PeriodicPayment implements IBankAccount{
	
	private double networth;
	@Override
	public boolean withdraw(double amount) {
		if (amount < 0 || amount > networth) {
			return false;
		}
		networth -= amount;
		return true;
	}

	@Override
	public boolean deposit(double amount) {
		if (amount < 0) {
			return false;
		}
		networth += amount;
		return true;
	}
	
	public boolean makePayment (double amount){
		if (amount < 0 || amount > networth) {
			return false;
		}
		networth -= amount;
		return true;
	}
}
