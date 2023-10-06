

public class CheckingAccount extends BankAccount{

    private double annualInterestRatePercent;
    private double rate;
    private double totalInterestEarned;
    private double minimumCheckFreeBalance;
    private double checkCharge;
    private double totalCheckCharges;


    public CheckingAccount() {
        super();
    }

    public CheckingAccount(String name, String accountNumber, double initialDeposit, double annualInterestRatePercentage, double minimumCheckFreeBalance, double checkCharge) {
        super("Checking Account", name, accountNumber);
        setInvestmentValue(initialDeposit);
        setMinimumCheckFreeBalance(minimumCheckFreeBalance);
        setCheckCharge(checkCharge);
        setAnnualInterestRatePercent(annualInterestRatePercentage);
    }

    public double getAnnualInterestRatePercent() {
        return annualInterestRatePercent;
    }

    public void setAnnualInterestRatePercent(double annualInterestRatePercent) {
        this.annualInterestRatePercent = annualInterestRatePercent;
        rate = annualInterestRatePercent/100;
    }

    public double getTotalInterestEarned() {
        return totalInterestEarned;
    }

    public void setTotalInterestEarned(double totalInterestEarned) {
        this.totalInterestEarned = totalInterestEarned;
    }

    public double getMinimumCheckFreeBalance() {
        return minimumCheckFreeBalance;
    }

    public void setMinimumCheckFreeBalance(double minimumCheckFreeBalance) {
        this.minimumCheckFreeBalance = minimumCheckFreeBalance;
    }

    public double getCheckCharge() {
        return checkCharge;
    }

    public void setCheckCharge(double checkCharge) {
        this.checkCharge = checkCharge;
    }

    public double getTotalCheckCharges() {
        return totalCheckCharges;
    }

    public void setTotalCheckCharges(double totalCheckCharges) {
        this.totalCheckCharges = totalCheckCharges;
    }

    public void makeDeposit(double deposit){
        setInvestmentValue(deposit + getInvestmentValue());
    }

    public void writeCheck(double checkValue){
        if(checkValue > getInvestmentValue()){
            //System.out.println("There are insufficient funds");
        }else if(getInvestmentValue() >= minimumCheckFreeBalance){
            setInvestmentValue(getInvestmentValue() - checkValue);
        }else{
            setInvestmentValue(getInvestmentValue()-checkValue-checkCharge);
            setTotalCheckCharges(totalCheckCharges + checkCharge);
        }
    }

    public void calcValue(){
        if(getInvestmentValue() >= minimumCheckFreeBalance){
            double interest = (getInvestmentValue() *(rate/12));
            totalInterestEarned += interest;
            setInvestmentValue(getInvestmentValue() + interest);
            
        }
    }

    @Override
    public String toString() {
        return String.format("%s\nMinimum For Free Checking: $%,.2f Check Charge: $%,.2f\nCurrent value: $%,.2f Interest Earned: $%,.2f Check Charges: $%,.2f\n", super.toString(), getMinimumCheckFreeBalance(), getCheckCharge(), getInvestmentValue(), totalInterestEarned, totalCheckCharges);
    }

}