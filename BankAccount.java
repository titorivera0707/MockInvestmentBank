public abstract class BankAccount extends Investment{

    private String accountNumber;

    public BankAccount() {
        super();
        accountNumber = "none";
    }
    public BankAccount(String type, String name, String accountNumber) {
        super(type, name);
        setAccountNumber(accountNumber);
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    @Override
    public String toString() {
        return String.format("%s Account Number: %s", super.toString(), accountNumber);
    }

}