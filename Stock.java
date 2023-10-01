
public class Stock extends Investment{

    private double pricePerShare;
    private double numOfSharesOwned;
    private double dividendsEarnedToDate;

    public Stock() {
        super();
    }
    public Stock(String name, double pricePerShare, double numOfSharesOwned) {
        super("Stock", name);
        this.pricePerShare = pricePerShare;
        this.numOfSharesOwned = numOfSharesOwned;
        super.setInvestmentValue(pricePerShare*numOfSharesOwned);
    }
    public double getPricePerShare() {
        return pricePerShare;
    }
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
    public double getNumOfSharesOwned() {
        return numOfSharesOwned;
    }
    public void setNumOfSharesOwned(double numOfSharesOwned) {
        this.numOfSharesOwned = numOfSharesOwned;
    }

    public void calcStockValues(double priceChange, double dividendPercent){
        double currentDividend;
        pricePerShare += priceChange;
        currentDividend = pricePerShare * dividendPercent;
        dividendsEarnedToDate += currentDividend * numOfSharesOwned;
        if(currentDividend > 0){
            numOfSharesOwned += (dividendsEarnedToDate/pricePerShare);
        }
        super.setInvestmentValue(pricePerShare*numOfSharesOwned);
    }
    @Override
    public String toString() {
        return String.format("\n%sPrice Per Share: $%.2f Number Of Shares: $%.2f\nCurrent Value: $%.2f Investment Earnings to date: $%.2f", super.toString(), pricePerShare, numOfSharesOwned, super.getInvestmentValue(), dividendsEarnedToDate*pricePerShare);
    }

}