package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.Utils.*;

public class Main {
    public static void main(String[] args) {
        String filename = "ej_file.txt";
        List<Transaction> transactions = parseEJFile(filename);
        String json = convertToJson(transactions);
        System.out.println(json);

//        String line = "11:57:15 CARD:;458300XXXXXX9694?";
//        String[] parts = splitCardLine(line);
//        String time = parts[0];
//        String key = parts[1];
//        String value = parts[2];
//        System.out.println("Time: " + time);
//        System.out.println("Key: " + key);
//        System.out.println("Value: " + value);

//        String[] lines = {
//                "11:19:18 CAMERA  1 P20240304111918298.JPG",
//                "11:19:41 CAMERA  2 P20240304111940715.JPG",
//                "11:19:47 CAMERA  3 P20240304111947262.JPG",
//                "11:19:52 CAMERA  3 P20240304111952649.JPG",
//                "11:19:57 CAMERA2 31 E20240304111947629.MPG"
//        };
//
//        for (String line : lines) {
//            String[] parts = splitCameraLine(line);
//            String time = parts[0];
//            String key = parts[1];
//            String value = parts[2];
//            System.out.println("Time: " + time);
//            System.out.println("Key: " + key);
//            System.out.println("Value: " + value);
//            System.out.println();
//        }


//        String line = "11:19:22 ATR RECEIVED T=0                 ";
//        String[] parts = splitATRLine(line);
//        String time = parts[0];
//        String key = parts[1];
//        String value = parts[2];
//        System.out.println("Time: " + time);
//        System.out.println("Key: " + key);
//        System.out.println("Value: " + value);


//        String line = "11:19:23 AID=A0000000031010";
//        String[] parts = splitAIDLine(line);
//        String time = parts[0];
//        String key = parts[1];
//        String value = parts[2];
//        System.out.println("Time: " + time);
//        System.out.println("Key: " + key);
//        System.out.println("Value: " + value);
    }

    public static List<Transaction> parseEJFile(String filename) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Transaction currentTransaction = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("============TRANSACTION START=============")) {
                    if (currentTransaction != null) {
                        transactions.add(currentTransaction);
                    }
                    currentTransaction = new Transaction();
                } else {
                    if (line.contains("CARD:")) {
                        String[] cardEvent = splitCardLine(line);
                        Event event = new Event(cardEvent[0], cardEvent[1], cardEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("CAMERA")) {
                        String[] cameraEvent = splitCameraLine(line);
                        Event event = new Event(cameraEvent[0], cameraEvent[1], cameraEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("ATR")) {
                        String[] atrEvent = splitATRLine(line);
                        Event event = new Event(atrEvent[0], atrEvent[1], atrEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("AID")) {
                        String[] aidEvent = splitAIDLine(line);
                        Event event = new Event(aidEvent[0], aidEvent[1], aidEvent[2]);
                        currentTransaction.addEvent(event);
                    } else {
                        // Ignore unknown lines
                    }

                }
            }

            // Add the last transaction to the list
            if (currentTransaction != null) {
                transactions.add(currentTransaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }


    public static String convertToJson(List<Transaction> transactions) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(transactions);
    }
}