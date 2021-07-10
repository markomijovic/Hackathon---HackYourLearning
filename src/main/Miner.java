package main;

import java.util.HashMap;

public class Miner {

    private double btc_amount; // total amount of btc we want to mine
    private int maximum_miners; // total number of miners if user has a limit
    private double btc_price; // price of the btc in USD
    private double btc_can_price; // price of the btc in CAN
    private MinerCSV miner_csv; // miner information from the csv file
    private WattageCSV wattage_csv; // wattage cost information from the csv file

    /**
     * Constructor initializes amount of btc want mined, and the maximum number of available miners if provided
     * @param btc_amount -- amount of btc want to mine
     * @param maximum_miners -- maximum number of available miners
     */
    public Miner(double btc_amount, int maximum_miners) {
        this.btc_amount = btc_amount;
        this.maximum_miners = maximum_miners;
        miner_csv = new MinerCSV();
        wattage_csv = new WattageCSV();
        btc_price = BTCPrice.getUSDPrice();
        btc_can_price = BTCPrice.getConvertedPrice();
    }

    /**
     * Constructor initializes amount of btc want mined
     * @param btc_amount -- amount of btc want to mine
     */
    public Miner(double btc_amount) {
        // If user does not specify maximum_miners (aka use all the available miners)
        // then just pass -1 as the value to represent that
        this(btc_amount, -1);
    }

    /**
     * Calculate the number of days it takes to mine specified quantity of btc
     * @return the number of days it would take to mine specified btc amount the fastest
     */
    public double fastestMiningTime() {
        String[][] miner = this.miner_csv.getMiners();
        double total_mined_per_hour = 0;
        double ans = 0;
        for (int i = 0; i < miner[0].length; i++) { // for each miner
            String miner_name = miner[0][i];
            if (i == 0) {
                miner_name = miner_name.substring(1);
            }
            // Because we initialized the array to size 100 stop when null. Should have used ArrayList instead.
            if (miner_name == null) { break; }
            double btc_mined_hour = Double.parseDouble(miner[1][i]);
            total_mined_per_hour += btc_mined_hour;
            // Because we initialized the array to size 100 stop when null. Should have used ArrayList instead.
        }
        try {
            ans = this.btc_amount / total_mined_per_hour;
        }
        catch (Error e) {
            System.out.println("0 BTC mined per hour");
            System.exit(-1);
        }
        return ans / 24; // days
    }

    /**
     * Efficient mining is defined as running miners only when they make money per hour given the btc price
     * and the cost of electricity
     * @return ProfitableMining class that stores how much profit per day and how many days it takes to mine the BTC
     */
    public ProfitableMining efficientMiningTime() {
        String[][] miner = this.miner_csv.getMiners();
        HashMap<String, Double[]> hm = this.minerProfit();
        double total_profit_per_day = 0; // profit of all miners working
        double total_btc_mined_per_day = 0.00000000000000000000001; // total time all miners mined
        for (int i = 0; i < miner[0].length; i++) { // for each miner
            // Miner information
            String miner_name = miner[0][i];
            if (i == 0) { // reading csv file has first bit reserved for encoding type so we take the substring from 1
                miner_name = miner_name.substring(1);
            }
            // Because we initialized the array to size 100 stop when null. Should have used ArrayList instead.
            if (miner_name == null) { break; }
            //

            Double[] miner_profit = hm.get(miner_name);
            double btc_mined_hour = Double.parseDouble(miner[1][i]);
            double miner_profit_per_day = 0;
            double btc_mined_day = 0;
            for (double profit_per_hour : miner_profit) {
                if (profit_per_hour >= 0) {
                    miner_profit_per_day += profit_per_hour;
                    btc_mined_day += btc_mined_hour; // btc/hour * number of hours its profitable
                }
            }
            total_profit_per_day += miner_profit_per_day;
            total_btc_mined_per_day += btc_mined_day;
        }
        return new ProfitableMining(total_profit_per_day, this.btc_amount / total_btc_mined_per_day);
    }

    /**
     * Calculates for each hour of the day the amount of money all miners make for a given btc price and electricity cost
     * @return hashmap whose key is the miner name/code and the value is the array of doubles that represent profit for each hour in a day
     */
    private HashMap<String, Double[]> minerProfit () {
        HashMap<String, Double[]> miner_profit = new HashMap<>(); // hashmap to store {mine_name: [profit_hour0, proft_hour1, ...]}
        String [][] miner = this.miner_csv.getMiners();
        String [][] wattage = this.wattage_csv.getWattage();
        double total_time = 0;
        for (int i = 0; i < miner[0].length; i++) { // for each miner
            // Miner information
            String miner_name = miner[0][i];
            if (i == 0) { // reading csv file has first bit reserved for encoding type so we take the substring from 1
                miner_name = miner_name.substring(1);
            }
            // Because we initialized the array to size 100 stop when null. Should have used ArrayList instead.
            if (miner_name == null) { break; }

            // Wattage information
            double miner_wattage = Integer.parseInt(miner[2][i]);
            double btc_mined_hour = Double.parseDouble(miner[1][i]);
            // The array we store profit each miner makes per hour for each hour and store in the hashmap for the miner
            Double[] profit_per_hour = new Double[24];
            for (int j = 0; j < 24; j++) { // for each hour
                // money made from mining btc
                double btc_profit_per_hour = btc_mined_hour * this.btc_price; // profit from mining btc
                double wattage_cost = Double.parseDouble(wattage[1][j]); // cost of electricity
                // Total profit = btc value - electricity cost (all per hour)
                profit_per_hour[j] = (btc_profit_per_hour) - (miner_wattage * wattage_cost / 10000.0);
            }
            // Insert profit per hour to the hashmap
            miner_profit.put(miner_name, profit_per_hour);
        }
        return miner_profit;
    }

    /**
     * Tests
     */
    public static void main(String[] args) {
        double btc_amount = 1.5;
        Miner test = new Miner(btc_amount);
        double t = test.fastestMiningTime();
        System.out.println(String.format("It takes %.2f days to mine %.2f BTC.", t, btc_amount));
        HashMap<String, Double[]> hm = test.minerProfit();
        ProfitableMining t2 = test.efficientMiningTime();
        System.out.println(String.format("It takes %.2f days to mine %.2f BTC. This results in profit of $%.3f USD", t2.getTime(), btc_amount, t2.getProfit()*t2.getTime()));
    }
}
