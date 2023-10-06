
public class Stock extends Investment{

    private double pricePerShare;
    private double numOfSharesOwned;
    private double dividendsEarnedToDate;
    

    public Stock() {
        super();
    }
    public Stock(String name, double pricePerShare, double numOfSharesOwned) {
        super("Stock", name);
        setPricePerShare(pricePerShare);;
        setNumOfSharesOwned(numOfSharesOwned);;
        setInvestmentValue(pricePerShare*numOfSharesOwned);
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
        pricePerShare += priceChange;
        double currentDividend = ((pricePerShare * dividendPercent)/100);
        dividendsEarnedToDate += (currentDividend * numOfSharesOwned);
        if(pricePerShare > 0){
            numOfSharesOwned += (currentDividend*numOfSharesOwned/pricePerShare);
        }
        setInvestmentValue(numOfSharesOwned * pricePerShare);
    }
    @Override
    public String toString() {
        return String.format("%s\nPrice Per Share: $%,.2f Number Of Shares: %,.2f\nCurrent Value: $%,.2f Investment Earnings to date: $%,.2f\n", super.toString(), pricePerShare, numOfSharesOwned, getInvestmentValue(), dividendsEarnedToDate);
    }

}