import java.io.*;
import java.util.*;

public class TestPortfolioManager{
    public static void main(String[] args) throws IOException{
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Please enter the name of the portfolio: ");
        String portName = keyboard.nextLine();
        System.out.println("Please enter a seed to use to create a Random object in the portfolio: ");
        int newSeed = keyboard.nextInt();
        Portfolio newPortfolio = new Portfolio(portName, newSeed);
        newPortfolio.initializePortfolio();
        System.out.println("Please enter the duration in months to model this portfolio: ");
        int monthValue = keyboard.nextInt();
        newPortfolio.modelPortfolio(monthValue);
        newPortfolio.generatePortfolioReport(monthValue);
        keyboard.close();
    }

}