package main;

public class ProfitableMining {
    /**
     * Acts as the store of value of profit and time for an efficient calculation
     */
    double profit;
    double time;

    public ProfitableMining (double profit, double days) {
        this.profit = profit;
        this.time = days;
    }

    public double getProfit() {
        return profit;
    }

    public double getTime() {
        return time;
    }
}
