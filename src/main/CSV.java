package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CSV {
    protected String filename;
    protected String[][] csvData = new String[100][100];

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
