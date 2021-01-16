/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Tree
 */
public class DeleteRow {

    static void deleteAndWriteBackToCSV(int row) throws IOException {
        List<CreditCard> tempDeleteRow = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);
        System.out.println("---------------------------------BEFORE DELETE----------------------------------");
        tempDeleteRow.forEach((c) -> {
            System.out.println(c);
        });
        System.out.println("---------------------------------AFTER DELETE----------------------------------");
        int index = row - 1;
        if (tempDeleteRow.size() >= 1) {
            tempDeleteRow.remove(index);
        }
        tempDeleteRow.forEach((d) -> {
            System.out.println(d);
        });
        


        ListToCSVWriter.writeToCSV(tempDeleteRow);

    }
}
