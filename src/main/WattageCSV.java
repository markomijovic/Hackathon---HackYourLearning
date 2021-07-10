package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class WattageCSV extends CSV {
    private String[][] watt;
    private String file = "EnergyRates.csv";


    public String[][] getWattage() {
        try {
            watt = readCSV(this.file);
        }
        catch(FileNotFoundException e){
            System.exit(-1);
        }
        return watt;
    }

    public static void main(String[] args) {
        WattageCSV test = new WattageCSV();
        String[][] btc_rate_test = test.getWattage();
        System.out.println(btc_rate_test[1][2]);
    }
}