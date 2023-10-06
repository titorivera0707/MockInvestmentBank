import java.util.*;
import java.io.*;

public class Portfolio{

    private String portfolioName;
    private ArrayList<Investment> portfolioInvestments = new ArrayList<Investment>();
    private Random randy;
    public Scanner keyboard = new Scanner(System.in);

    public Portfolio() {
        super();
        portfolioName = "none";
    }
    public Portfolio(String name, int seed) {
        super();
        portfolioName = name;
        randy = new Random(seed);
    }

    public String getPortfolioName() {
        return portfolioName;
    }
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    public void initializePortfolio()throws IOException{
        Scanner openFile = new Scanner(fileChecker("input"));
        while(openFile.hasNext()){
            Investment tempInvestment;
            ArrayList<String> temoArr = new ArrayList<String>();
            String strArray = openFile.nextLine();
            Scanner newScan = new Scanner(strArray);
            while(newScan.hasNext()){
                temoArr.add(newScan.next());
            }
            //Use openfile.nextLine then use has scanner on new String to use next
            if(temoArr.get(0).equals("Stock")){
                tempInvestment = new Stock(temoArr.get(1), Double.parseDouble(temoArr.get(2)),Double.parseDouble(temoArr.get(3)));
                portfolioInvestments.add(tempInvestment);
            }else if(temoArr.get(0).equals("Bond")){
                tempInvestment = new Bond(temoArr.get(1), Double.parseDouble(temoArr.get(2)), Integer.parseInt(temoArr.get(3)), Double.parseDouble(temoArr.get(4)));
                portfolioInvestments.add(tempInvestment);
            }else if(temoArr.get(0).equals("SavingsAccount")){
                tempInvestment = new SavingsAccount(temoArr.get(1), temoArr.get(2), Double.parseDouble(temoArr.get(3)), Double.parseDouble(temoArr.get(4)));
                portfolioInvestments.add(tempInvestment);
            }else{
                tempInvestment = new CheckingAccount(temoArr.get(1), temoArr.get(2), Double.parseDouble(temoArr.get(3)), Double.parseDouble(temoArr.get(4)), Double.parseDouble(temoArr.get(5)), Double.parseDouble(temoArr.get(6)));
                portfolioInvestments.add(tempInvestment);
            }
        }
    }

    public void modelPortfolio(int months){
        for(Investment invested: portfolioInvestments){
            if(invested instanceof Stock){
                Stock tempStock = (Stock) invested;
                for (int i = 0; i < months; i+=3) {
                    double priceChange = randy.nextInt(-100,201)/10;
                    double dividendPercent = randy.nextInt(0,51)/10;
                    tempStock.calcStockValues(priceChange, dividendPercent);
                }
            }else if(invested instanceof Bond){
                for (int i = 0; i < months; i++) {
                    Bond tempBond = (Bond) invested;
                    tempBond.calcBondValues();   
                }
            }else if(invested instanceof SavingsAccount){
                SavingsAccount tempSavings = (SavingsAccount) invested;
                for (int i = 1; i < months; i++) {
                    for (int j = 0; j < 3; j++) {
                        double depWith = randy.nextInt(-60000, 100001)/100;
                        if(depWith >= 0){
                            tempSavings.makeDeposit(depWith);
                        }else if(depWith < 0){
                            tempSavings.makeWithdrawal(Math.abs(depWith));
                        }else continue;
                    }
                    tempSavings.calcValue();
                }
            }else if(invested instanceof CheckingAccount){
                CheckingAccount tempChecking = (CheckingAccount) invested;
                for (int i = 0; i < months; i++) {
                    double ranDeposit = randy.nextInt(5000,15001)/10;
                    tempChecking.makeDeposit(ranDeposit);
                    for (int j = 0; j < 4; j++) {
                        double writeCheck = randy.nextInt(1000, 30001)/100;
                        tempChecking.writeCheck(writeCheck);
                    }
                    tempChecking.calcValue();
                }
            }
        }
    }

    public void generatePortfolioReport(int months)throws IOException{
        PrintWriter newWriter = new PrintWriter(fileChecker("output"));
        newWriter.println("Results of the portfolio " + portfolioName + " over "+ months + " months");
        for(Investment tempInv: portfolioInvestments){
            newWriter.println(tempInv.toString());
        }
        newWriter.close();
    }

    public File fileChecker(String inOut){
        Scanner newKey = new Scanner(System.in);
        File newUserFile = new File("");
        do{
            System.out.println(String.format("Please enter the name of the "+ inOut +" file: "));
            String fileName = newKey.nextLine();
            File tempFile = new File(fileName);
            if(tempFile.exists()){
                newUserFile = tempFile;
                continue;
            }else{
                System.out.println("File does not exist. Please try again.");
            }
        }while(!newUserFile.exists());
        return newUserFile;
    }

}