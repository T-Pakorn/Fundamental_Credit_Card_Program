/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Tree
 */
public class DeleteRow {

    static void deleteAndWriteBackToCSV(int row) throws IOException {
        List<CreditCard> tempDeleteRow = CSVReaderInJava.readCardsFromCSV(Utils.fileLocation);
        System.out.println("---------------------------------BEFORE DELETE----------------------------------");
        for (CreditCard c : tempDeleteRow) {
            System.out.println(c);
        }
        System.out.println("---------------------------------AFTER DELETE----------------------------------");
        int index = row - 1;
if(tempDeleteRow.size()>=1){
        tempDeleteRow.remove(index);
}
         for (CreditCard d : tempDeleteRow) {
            System.out.println(d);
        }

        ListToCSVWriter.writeToCSV(tempDeleteRow);

    }
}
