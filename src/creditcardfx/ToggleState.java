/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import creditcardfx.CSVReaderInJava;
import creditcardfx.CreditCard;
import creditcardfx.ListToCSVWriter;
import creditcardfx.Utils;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Tree
 */
public class ToggleState {

    public static void switchCardState(int row, String stateNow) throws IOException {
        List<CreditCard> tempSwitchStateRow = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);

        int index = row - 1;

        CreditCard wantToEdit = tempSwitchStateRow.get(index);
        System.out.println("State Now = " + stateNow);
        if ("Forfeit".equals(stateNow)) {

            wantToEdit.setState("Normal");
            System.out.println("Set To Forfeit");
        } else {
            wantToEdit.setState("Forfeit");
            System.out.println("Set To Normal");
        }

        System.out.println("---------------------------------BEFORE EDIT----------------------------------");
        tempSwitchStateRow.forEach((c) -> {
            System.out.println(c);
        });
        System.out.println("---------------------------------AFTER EDIT----------------------------------");

        tempSwitchStateRow.remove(index);
        System.out.println("Delete Target INDEX");
        tempSwitchStateRow.add(index, wantToEdit);
        System.out.println("Slip Edit Information In");

        tempSwitchStateRow.forEach((d) -> {
            System.out.println(d);
        });

        ListToCSVWriter.writeToCSV(tempSwitchStateRow);
    }
}
