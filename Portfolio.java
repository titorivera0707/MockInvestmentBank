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