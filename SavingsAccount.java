

public class SavingsAccount extends BankAccount{

    private double annualInterestRatePercent;
    private double rate;
    private double totalInterestEarned;

    public SavingsAccount() {
        super();
    }
    public SavingsAccount(String name, String accountNumber, double initialDeposit, double annualInterestRatePercent) {
        super("Savings Account", name, accountNumber);
        setInvestmentValue(initialDeposit);
        setAnnualInterestRatePercent(annualInterestRatePercent);
    }
    public double getAnnualInterestRatePercent() {
        return annualInterestRatePercent;
    }
    public void setAnnualInterestRatePercent(double annualInterestRatePercent) {
        this.annualInterestRatePercent = annualInterestRatePercent;
        rate = (annualInterestRatePercent/100);
    }
    public double getTotalInterestEarned() {
        return totalInterestEarned;
    }
    public void setTotalInterestEarned(double totalInterestEarned) {
        this.totalInterestEarned = totalInterestEarned;
    }

    public void makeDeposit(double deposit){
        super.setInvestmentValue(deposit + getInvestmentValue());
    }

    public boolean makeWithdrawal(double withdrawal){
        if(withdrawal > getInvestmentValue()){
            //System.out.println("There are insufficient funds");
            return false;
        }else{
            super.setInvestmentValue(getInvestmentValue() - withdrawal);
            return true;
        }
    }

    public void calcValue(){
        totalInterestEarned += (getInvestmentValue() * rate/12);
        setInvestmentValue((getInvestmentValue()*rate/12) + getInvestmentValue());
    }
    @Override
    public String toString() {
        return String.format("%s\nCurrent value: $%,.2f Interest Earned: $%,.2f\n", super.toString(), getInvestmentValue(), totalInterestEarned);
    }

    

}