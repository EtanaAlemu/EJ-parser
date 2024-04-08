package org.example;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class MyTrx {
    public static void main(String[] args) {
        // Example transaction lines
        String[] transactionLines = {
                "------------03/04/24 11:19:18-------------",
                "============TRANSACTION START=============",
                "11:19:18 CARD:;458300XXXXXX9694?",
                "11:19:18 CAMERA  1 P20240304111918298.JPG",
                "11:19:22 ATR RECEIVED T=0",
                "11:19:23 AID=A0000000031010",
                "11:19:24 CARD:;458300XXXXXX9694?",
                "11:19:29 PIN ENTERED",
                "11:19:31 AMOUNT/INFORMATION FDK I PRESSED",
                "11:19:34 1st GenAC:P1 = 80[ARQC]",
                "11:19:35 TNX REQUEST, OPCODE: AAAF   A",
                "11:19:36 TNX REPLY, NEXT: 077, FUNCID: A",
                "11:19:37 2nd GenAC:P1 = 40[TC]",
                "11:19:40 CARD TAKEN BY CUSTOMER",
                "11:19:41 CAMERA  2 P20240304111940715.JPG",
                "------------03/04/24 11:19:46-------------",
                "24/03/04 24/03/04 11:19 0",
                "113 6016 011000",
                "0004583********9694",
                "100.00  230",
                "SURCHARGE:  0.00",
                "000",
                "11:19:46 DISPENSE",
                "ETB100   -001",
                "11:19:47 NOTES PRESENT AT CASH SLOT",
                "11:19:47 CAMERA  3 P20240304111947262.JPG",
                "11:19:47 CASH SHUTTER OPENED",
                "11:19:52 NOTES TAKEN BY CUSTOMER",
                "11:19:52 CAMERA  3 P20240304111952649.JPG",
                "------------03/04/24 11:19:56-------------",
                "11:19:56 CASH SHUTTER CLOSED",
                "11:19:57 CAMERA2 31 E20240304111947629.MPG",
                "------------03/04/24 11:20:00-------------"
        };

        // Parse transaction lines into a list of transactions
        List<Transaction> transactions = parseTransactions(transactionLines);

        // Convert transactions to JSON
        String json = convertToJson(transactions);

        // Print JSON string
        System.out.println(json);
    }

    // Method to parse transaction lines into a list of transactions
    public static List<Transaction> parseTransactions(String[] transactionLines) {
        List<Transaction> transactions = new ArrayList<>();
        Transaction currentTransaction = null;
        for (String line : transactionLines) {
            if (line.startsWith("------------")) {
                // Start of a new transaction
                currentTransaction = new Transaction();
                transactions.add(currentTransaction);
                currentTransaction.setTransactionStart(line.substring(13));
            } else if (currentTransaction != null) {
                // Add transaction details
                if (line.startsWith("============TRANSACTION START=============")) {
                    currentTransaction.setTransactionStart(line);
                } else if (line.matches("\\d{3}\\s\\d{4}\\s\\d{6}")) {
                    currentTransaction.setBranchCode(line.substring(0, 3));
                    currentTransaction.setAuthorizationReference(line.substring(4, 8));
                    currentTransaction.setUnknownData(line.substring(9));
                } else if (line.matches("\\d{16}")) {
                    currentTransaction.setPAN(line);
                } else if (line.matches("\\d+\\.\\d+\\s+\\d+")) {
                    String[] parts = line.split("\\s+");
                    currentTransaction.setWithdrawalAmount(parts[0]);
                    currentTransaction.setCurrencyCode(parts[1]);
                } else if (line.startsWith("SURCHARGE:")) {
                    currentTransaction.setSurcharge(line.substring(10));
                } else if (line.matches("\\d+")) {
                    currentTransaction.setChargedFee(line);
                } else if (line.startsWith("DISPENSE")) {
                    currentTransaction.setDispenseEvent(line);
                } else if (line.matches("\\D+\\d+\\s+-\\d+")) {
                    currentTransaction.setDispensedAmount(line);
                } else {
                    // Add event to current transaction
                    currentTransaction.addEvent(parseEvent(line));
                }
            }
        }
        return transactions;
    }

    // Method to parse a single event line
    public static Event parseEvent(String line) {
        String[] parts = line.split("\\s+", 3);
        if (parts.length >= 3) {
            String timestamp = parts[0];
            String eventType = parts[1];
            String eventData = parts[2];
            return new Event(timestamp, eventType, eventData);
        }
        return null;
    }

    // Method to convert transactions to JSON
    public static String convertToJson(List<Transaction> transactions) {
        Gson gson = new Gson();
        return gson.toJson(transactions);
    }
}

