package main;

import com.sun.tools.javac.Main;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Class used to specify csv file that data will be extracted from and converted into a 2D String array.
 */
public class MinerCSV extends CSV{

    private String[][] miner;
    private String file = "MiningSetup.csv";

    /**
     *
     * @return miner -- 2D String array representation of the MiningSetup.csv
     */
    public String[][] getMiners() {
        try{
             miner = readCSV(this.file);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        return miner;
    }
}
