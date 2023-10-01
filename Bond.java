
public class Bond extends Investment{

    private double pricePerBond;
    private double annualReturnPercentage;
    private double rate;
    private double cashEarnedToDate;
    private int numBondsOwned;

    public Bond() {
        super();
    }

    public Bond(String name, double pricePerBond, int numBondsOwned, double annualReturnPercentage) {
        super("Bond", name);
        this.pricePerBond = pricePerBond;
        this.numBondsOwned = numBondsOwned;
        this.annualReturnPercentage = annualReturnPercentage;
        super.setInvestmentValue(pricePerBond*numBondsOwned);
    }

    public double getPricePerBond() {
        return pricePerBond;
    }

    public void setPricePerBond(double pricePerBond) {
        this.pricePerBond = pricePerBond;
    }

    public double getAnnualReturnPercentage() {
        return annualReturnPercentage;
    }

    public void setAnnualReturnPercentage(double annualReturnPercentage) {
        this.annualReturnPercentage = annualReturnPercentage;
        rate = (annualReturnPercentage/12)/100;
    }

    public int getNumBondsOwned() {
        return numBondsOwned;
    }

    public void setNumBondsOwned(int numBondsOwned) {
        this.numBondsOwned = numBondsOwned;
    }

    public void calcBondValues(){
        cashEarnedToDate += pricePerBond*rate;
        super.setInvestmentValue(pricePerBond*numBondsOwned+cashEarnedToDate);
    }

    @Override
    public String toString() {
        return String.format("\n%sPrice Per Bond: $%.2f Number Of Bonds: $%.2f\nCurrent Value: $%.2f Investment Earnings to date: $%.2f", super.toString(), pricePerBond, numBondsOwned, super.getInvestmentValue(), cashEarnedToDate);
    }

    

}