package main;

import com.sun.tools.javac.Main;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class MinerCSV extends CSV{

    private String[][] miner;
    private String file = "MiningSetup.csv";

    public String[][] getMiners() {
        try{
             miner = readCSV(this.file);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return miner;
    }

    public static void main(String[] args) {
        MinerCSV test = new MinerCSV();
        String[][] btc_rate_test = test.getMiners();
        System.out.println(btc_rate_test[2][2]);
    }
}
