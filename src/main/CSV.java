package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class to open and read csv data and save it to a 2D String array
 */

public class CSV {
    protected String filename;
    protected String[][] csvData = new String[100][100]; //creating and initializing 2D array of 100x100

    /**
     * Reads the csv file and formats the data into a 2D string array with the outermost array being the columns,
     * and the innermost array being the rows values.
     * @param file -- String of filename
     * @return csvData -- 2D String array representation of csv file
     * @throws FileNotFoundException
     */
    public String [][] readCSV(String file) throws FileNotFoundException{
        String row[]; // creating row array
        this.filename = file;
        int row_count = 0; // starting at the first row
        Scanner sc = new Scanner(new File("data/"+this.filename));
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            row = s.split(","); // creating row array
            int c = 0; // for column
            for (String element : row) {
                this.csvData[c++][row_count] = element;
            }
            row_count++;
        }
        sc.close();
        return csvData;
    }
}
