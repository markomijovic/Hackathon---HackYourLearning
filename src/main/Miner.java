package main;

public class Miner {

    private double btc_amount; // total amount of btc
    private int maximum_miners;
    private MinerCSV miner_csv; // rate btc is mined
    private WattageCSV wattage_csv;

    /**
     *
     * @param btc_amount
     * @param maximum_miners
     */
    public Miner(double btc_amount, int maximum_miners) {
        this.btc_amount = btc_amount;
        this.maximum_miners = maximum_miners;
    }

    /**
     *
     * @param btc_amount
     */
    public Miner(double btc_amount) {
        // If user does not want to specify maximum_miners (aka use all the available miners)
        // then just pass -1 as the value to represent that
        this(btc_amount, -1);
    }

    /**
     *
     * @return the fastest time it would take to mine specified btc amount
     */
    public double fastestMiningTime() {
        double total_mine_rate = 0;
        for (int i=0; i< this.miner_csv.btc_rate.length; i++){
            total_mine_rate += miner_csv.btc_rate[i];
        }

        double total_time = this.btc_amount / //rate btc is mined from csv;
    }

    /**
     *
     * @return the time it would take to make the most money mining the specified btc amount
     */
    public double efficientMiningTime() {

        return 0.0;
    }

}

//1 function that just outputs how long it would take irrelevent of everything else (still outputs profit)
//        - initalizes the MinerCSV and reads data
//        - method to calcualte the most efficent (profit) way using wattage and miner csv to producte the btc amount
