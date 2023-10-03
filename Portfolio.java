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
        randy.setSeed(seed);
    }

    public void initializePortfolio()throws IOException{
        Scanner openFile = new Scanner(fileChecker());
        while(openFile.hasNext()){
            Investment tempInvestment;
            String[] strArray = openFile.next().split(" ");
            if(strArray[0].equals("Stock")){
                tempInvestment = new Stock(strArray[1], Double.parseDouble(strArray[2]),Double.parseDouble(strArray[3]));
                portfolioInvestments.add(tempInvestment);
            }else if(strArray[0].equals("Bond")){
                tempInvestment = new Bond(strArray[1], Double.parseDouble(strArray[2]), Integer.parseInt(strArray[3]), Double.parseDouble(strArray[4]));
                portfolioInvestments.add(tempInvestment);
            }else if(strArray[0].equals("SavingsAccount")){
                tempInvestment = new SavingsAccount(strArray[1], strArray[2], Double.parseDouble(strArray[3]), Double.parseDouble(strArray[4]));
                portfolioInvestments.add(tempInvestment);
            }else{
                tempInvestment = new CheckingAccount(strArray[1], strArray[2], Double.parseDouble(strArray[3]), Double.parseDouble(strArray[4]), Double.parseDouble(strArray[5]), Double.parseDouble(strArray[6]));
                portfolioInvestments.add(tempInvestment);
            }
        }
        openFile.close();
    }

    public void modelPortfolio(int months){
        for(Investment invested: portfolioInvestments){
            if(invested instanceof Stock){
                for (int i = 0; i < months; i+=3) {
                    Stock tempStock = (Stock) invested;
                    double priceChange = randy.nextInt(-11,201)/10;
                    double dividendPercent = randy.nextInt(0,51)/10;
                    tempStock.calcStockValues(priceChange, dividendPercent);
                }
            }else if(invested instanceof Bond){
                Bond tempBond = (Bond) invested;
                tempBond.calcBondValues();
            }else if(invested instanceof SavingsAccount){
                SavingsAccount tempSavings = (SavingsAccount) invested;
                for (int i = 0; i < months; i++) {
                    for (int j = 0; j < 3; j++) {
                        double depWith = randy.nextInt(-60000, 1000001)/100;
                        if(depWith > 0){
                            tempSavings.makeDeposit(depWith);
                        }else if(depWith < 0){
                            tempSavings.makeWithdrawal(depWith);
                        }else continue;
                    }
                }
                tempSavings.calcValue();
            }else if(invested instanceof CheckingAccount){
                CheckingAccount tempChecking = (CheckingAccount) invested;
                double ranDeposit = randy.nextInt(5000,15000)/10;
                tempChecking.makeDeposit(ranDeposit);
                for (int i = 0; i < months; i++) {
                    for (int j = 0; j < 4; j++) {
                        double writeCheck = randy.nextInt(100, 30001)/100;
                        tempChecking.writeCheck(writeCheck);
                    }
                    tempChecking.calcValue();
                }
            }
        }
    }

    public void generatePortfolioReport(int months){

    }

    public File fileChecker(){
        Scanner newKey = new Scanner(System.in);
        File newUserFile = new File("");
        do{
            System.out.println(String.format("Please enter the name of the input file: "));
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