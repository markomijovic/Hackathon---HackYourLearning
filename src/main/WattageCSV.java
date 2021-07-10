package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class used to specify csv file that data will be extracted from and converted into a 2D String array.
 */
public class WattageCSV extends CSV {
    private String[][] watt;
    private String file = "EnergyRates.csv";

    /**
     *
     * @return watt -- 2D String array representation of the EnergyRates.csv
     */
    public String[][] getWattage() {
        try {
            watt = readCSV(this.file);
        }
        catch(FileNotFoundException e){
            System.exit(-1);
        }
        return watt;
    }
}