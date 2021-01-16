package creditcardfx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tree
 */
public class ListToCSVWriter {

    //European countries use ";" as 
    //CSV separator because "," is their digit separator
    private static final String CSV_SEPARATOR = "#";

    static void writeToCSV(List<CreditCard> temp) throws IOException {

        File newFile = new File(Utils.locationOfTemporaryFile);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"))) {
            for (CreditCard cards : temp) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(cards.getSerial());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getCcv());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getSurname());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getIssueDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getExpiryDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getTier());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getMoneyBoundary());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(cards.getState());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            
            
        }

    }
}
