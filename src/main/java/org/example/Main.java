package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        String filename = "ej_file.txt";
//        List<Transaction> transactions = parseEJFile(filename);
//        String json = convertToJson(transactions);
//        System.out.println(json);

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


        String line = "11:19:23 AID=A0000000031010";
        String[] parts = splitAIDLine(line);
        String time = parts[0];
        String key = parts[1];
        String value = parts[2];
        System.out.println("Time: " + time);
        System.out.println("Key: " + key);
        System.out.println("Value: " + value);


    }
//    public static List<Transaction> parseEJFile(String filename) {
//        List<Transaction> transactions = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            Transaction currentTransaction = null;
//            while ((line = br.readLine()) != null) {
//                if (line.startsWith("------------")) {
//                    if (currentTransaction != null) {
//                        transactions.add(currentTransaction);
//                    }
//                    currentTransaction = new Transaction();
//                    currentTransaction.setTimestamp(line.substring(0, 8)); // Extract time from the separator line
//                } else if (line.startsWith("============TRANSACTION START=============")) {
//                    // Skip transaction start indicator
//                } else {
//                    String[] parts = line.split("\\s+", 2);
//                    String key = parts[0];
//                    String value = parts.length > 1 ? parts[1] : "";
//                    // Set the appropriate field in the current transaction object
//                    switch (key) {
//                        case "CARD:":
//                            currentTransaction.setCardInfo(value);
//                            break;
//                        case "CAMERA":
//                            currentTransaction.setCameraInfo(value);
//                            break;
//                        case "ATR":
//                            currentTransaction.setAtrReceived(value);
//                            break;
//                        case "AID":
//                            currentTransaction.setAid(value);
//                            break;
//                        case "PIN":
//                            currentTransaction.setPinEntered(value);
//                            break;
//                        // Add more cases for other fields
//                        default:
//                            // Ignore unknown lines
//                            break;
//                    }
//                }
//            }
//            // Add the last transaction to the list
//            if (currentTransaction != null) {
//                transactions.add(currentTransaction);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return transactions;
//    }
    public static String[] splitCardLine(String line) {
        // Find the index of the first occurrence of ' ' (whitespace) to split time and key-value pair
        int spaceIndex = line.indexOf(' ');
        // Extract time
        String time = line.substring(0, spaceIndex).trim();
        // Extract key-value pair
        String keyValue = line.substring(spaceIndex).trim();
        // Find the index of the first occurrence of ':' within the key-value pair to split key and value
        int colonIndex = keyValue.indexOf(':');
        // Extract key
        String key = keyValue.substring(0, colonIndex).trim();
        // Extract value
        String value = keyValue.substring(colonIndex + 1).trim();
        // Remove semicolon if it exists at the beginning of the value
        if (value.startsWith(";")) {
            value = value.substring(1); // Remove the first character
        }
        // Remove question mark if it exists at the end of the value
        if (value.endsWith("?")) {
            value = value.substring(0, value.length() - 1); // Remove the last character
        }
        return new String[]{time, key, value};
    }

    public static String[] splitCameraLine(String line) {
        // Find the index of the first occurrence of ' ' (whitespace) to split time and key-value pair
        int spaceIndex = line.indexOf(' ');
        // Extract time
        String time = line.substring(0, spaceIndex).trim();
        // Extract key-value pair
        String keyValue = line.substring(spaceIndex).trim();
        // Find the index of the first occurrence of ' ' (whitespace) within the key-value pair to split key and value
        int secondSpaceIndex = keyValue.indexOf(' ');
        // Extract key
        String key;
        // Extract value
        String value;
        if (secondSpaceIndex != -1) {
            String[] keyParts = keyValue.substring(0, secondSpaceIndex).split("\\s+");
            if (keyParts.length >= 2 && keyParts[0].equals("CAMERA")) {
                key = keyParts[0] + " " + keyParts[1];
                value = keyValue.substring(secondSpaceIndex + 1).trim();
            } else if (keyValue.startsWith("CAMERA")) {
                String[] parts = keyValue.split("\\s+", 3); // Limit to 3 parts
                key = parts[0] + " " + parts[1];
                value = parts.length > 2 ? parts[2] : ""; // Value might be empty
            } else {
                key = keyValue.substring(0, secondSpaceIndex).trim();
                value = keyValue.substring(secondSpaceIndex + 1).trim();
            }
        } else {
            key = keyValue.trim();
            value = ""; // No value present
        }
        return new String[]{time, key, value};
    }

    public static String[] splitATRLine(String line) {
        // Find the index of the first occurrence of ' ' (whitespace) to split time and key-value pair
        int spaceIndex = line.indexOf(' ');
        // Extract time
        String time = line.substring(0, spaceIndex).trim();
        // Extract key-value pair
        String keyValue = line.substring(spaceIndex).trim();
        // Find the index of the first occurrence of ' ' (whitespace) within the key-value pair to split key and value
        int secondSpaceIndex = keyValue.indexOf(' ');
        // Extract key
        String key;
        // Extract value
        String value;
        if (secondSpaceIndex != -1) {
            // If the line contains a second space, split it into key and value
            key = keyValue.substring(0, secondSpaceIndex).trim();
            value = keyValue.substring(secondSpaceIndex + 1).trim();
        } else {
            // If there's no second space, consider the entire line as key and set an empty string as value
            key = keyValue.trim();
            value = ""; // No value present
        }
        return new String[]{time, key, value};
    }

    public static String[] splitAIDLine(String line) {
        // Find the index of the first occurrence of ' ' (whitespace) to split time and key-value pair
        int spaceIndex = line.indexOf(' ');
        // Extract time
        String time = line.substring(0, spaceIndex).trim();
        // Extract key-value pair
        String keyValue = line.substring(spaceIndex).trim();
        // Find the index of the first occurrence of '=' within the key-value pair to split key and value
        int equalsIndex = keyValue.indexOf('=');
        // Extract key
        String key;
        // Extract value
        String value;
        if (equalsIndex != -1) {
            // If the line contains '=', split it into key and value
            key = keyValue.substring(0, equalsIndex).trim();
            value = keyValue.substring(equalsIndex + 1).trim();
        } else {
            // If there's no '=', consider the entire line as key and set an empty string as value
            key = keyValue.trim();
            value = ""; // No value present
        }
        return new String[]{time, key, value};
    }




    public static String convertToJson(List<Transaction> transactions) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(transactions);
    }
}