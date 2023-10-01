
public abstract class Investment{

    private String type;
    private String name;
    private double investmentValue;

    public Investment() {
        super();
        type = "none";
        name = "none";
    }

    public Investment(String type, String name) {
        super();
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(double investmentValue) {
        this.investmentValue = investmentValue;
    }

    @Override
    public String toString(){
        return String.format("Investment: %s Name: %s", type, name);
    }

}