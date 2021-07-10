package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class CSV {
    protected String filename;
    protected String[][] csvData;

    CSV(String file) throws FileNotFoundException{
        this.filename = file;
        Scanner sc = new Scanner(new File (file));
        while (sc.hasNextLine()){
            String s = sc.nextLine();

        }

        sc.close();
    }

}
