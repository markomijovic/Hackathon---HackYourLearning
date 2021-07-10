package main;
import java.util.Scanner;

// FROM PROBLEM SET:
    //our program should be able to do the following:
        //Get the current price of Bitcoin from Coindesk
        //Ask the user how much Bitcoin they would like to mine
        //Return to the user how long it will take to produce the specified amount of bitcoin


public class CLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double btc_amount = 0;
        boolean invalidInput = true;
        // Validate user input
        while (invalidInput) {
            System.out.print("Enter a quantity of Bitcoin: ");
            String input = scanner.nextLine();

                try {
                    btc_amount = Double.parseDouble(input);
                    if (btc_amount <= 0) {
                        System.out.println("Bitcoin quantity can't be equal to or less than 0.");
                    }
                }
                catch (NumberFormatException n) {
                    System.out.println("Invalid Input. Only positive numerical entries are allowed.");
            }

            if (btc_amount > 0){
                break;
            }
        }
        scanner.close();
        Miner cryptoMiner = new Miner(btc_amount);
        double ratio = cryptoMiner.btc_can_price / cryptoMiner.btc_price;
        double fastestTime = cryptoMiner.fastestMiningTime();
        System.out.println(String.format("The fastest time to mine %.2f BTC is %.2f days.", btc_amount, fastestTime));
        ProfitableMining t2 = cryptoMiner.efficientMiningTime();
        System.out.println(String.format("The most profitable time to mine %.2f BTC is %.2f days. This results in profit of $%.2f USD or $%.2f CAD", btc_amount, t2.getTime(), t2.getProfit()*t2.getTime(), ratio*t2.getProfit()*t2.getTime()));
        cryptoMiner.efficientMiningTime();
        
    }
}
