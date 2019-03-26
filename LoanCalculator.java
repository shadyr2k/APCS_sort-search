public class LoanCalculator {
	public static void main(String[] args) {
		System.out.println(createAmortizationTable(100000, 0, 0.12));
	}
	public static String createAmortizationTable(double principal, double mPayment, double interest) {
		if(mPayment < 150) return "Not possible to pay off the loan.";
		String s = String.format("%-10s%-16s%-16s%-9s%n", "Month", "Interest", "Payment", "Remaining");
		interest /= 12;
		int month = 1;
		while(principal > 0) {
			double interestAmt = Math.round(principal*interest*100.0)/100.0; double prev = principal;
			principal = (principal - mPayment + interestAmt) > 0 ? principal - mPayment + interestAmt : 0;
			double pay = mPayment < prev ? mPayment : Math.round(prev*100.0)/100.0 + interestAmt;
			s += String.format("%-10d$%-15.2f$%-15.2f$%.2f%n", month, interestAmt, pay, principal);
			month++;
		}
		return s;
	}
}

