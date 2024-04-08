package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    } else if (line.contains("PIN")) {
                        String[] pinEvent = splitPINLine(line);
                        Event event = new Event(pinEvent[0], pinEvent[1], pinEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("AMOUNT/INFORMATION")) {
                        String[] amountInformationFDKEvent = splitAmountInformationFDKLine(line);
                        Event event = new Event(amountInformationFDKEvent[0], amountInformationFDKEvent[1], amountInformationFDKEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("1st GenAC:P1")) {
                        String[] genACEvent = splitGenACLine(line);
                        Event event = new Event(genACEvent[0], genACEvent[1], genACEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("TNX REQUEST")) {
                        String[] tnxRequestEvent = splitTNXRequestLine(line);
                        Event event = new Event(tnxRequestEvent[0], tnxRequestEvent[1], tnxRequestEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("TNX REPLY")) {
                        String[] tnxReplyEvent = splitTNXReplyLine(line);
                        Event event = new Event(tnxReplyEvent[0], tnxReplyEvent[1], tnxReplyEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("2nd GenAC:P1")) {
                        String[] genAC2Event = splitGenAC2Line(line);
                        Event event = new Event(genAC2Event[0], genAC2Event[1], genAC2Event[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("CARD TAKEN BY CUSTOMER")) {
                        String[] cardTakenEvent = splitCardTakenLine(line);
                        Event event = new Event(cardTakenEvent[0], cardTakenEvent[1], cardTakenEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("NOTES PRESENT AT CASH SLOT")) {
                        String[] notesPresentEvent = splitNotesPresentLine(line);
                        Event event = new Event(notesPresentEvent[0], notesPresentEvent[1], notesPresentEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("CASH SHUTTER OPENED")) {
                        String[] cashShutterOpenedEvent = splitCashShutterOpenedLine(line);
                        Event event = new Event(cashShutterOpenedEvent[0], cashShutterOpenedEvent[1], cashShutterOpenedEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("NOTES TAKEN BY CUSTOMER")) {
                        String[] notesTakenEvent = splitNotesTakenLine(line);
                        Event event = new Event(notesTakenEvent[0], notesTakenEvent[1], notesTakenEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.contains("CASH SHUTTER CLOSED")) {
                        String[] cashShutterClosedEvent = splitCashShutterClosedLine(line);
                        Event event = new Event(cashShutterClosedEvent[0], cashShutterClosedEvent[1], cashShutterClosedEvent[2]);
                        currentTransaction.addEvent(event);
                    } else if (line.matches("^\\d{3}\\s\\d{4}\\s\\d{6}\\s*$")) {
                        currentTransaction.setBranchCode(line.substring(0, 3).trim());
                        currentTransaction.setAuthorizationReference(line.substring(4, 8).trim());
                        currentTransaction.setUnknownData(line.substring(9).trim());
                    } else if (line.matches("^\\d{7}\\*{8}\\d{4}\\s*$")) {
                        currentTransaction.setPAN(line.trim());
                    } else if (line.matches("^\\s*\\d+\\.\\d+\\s+\\d+\\s*$")) {
                        String[] parts = line.split("\\s+");
                        currentTransaction.setWithdrawalAmount(parts[1]);
                        currentTransaction.setCurrencyCode(parts[2]);
                    } else if (line.startsWith("SURCHARGE:")) {
                        currentTransaction.setSurcharge(line.substring(10).trim());
                    } else if (line.matches("\\d+")) {
                        currentTransaction.setChargedFee(line);
                    } else if (line.startsWith("DISPENSE")) {
                        currentTransaction.setDispenseEvent(line);
                    } else if (line.matches("^\\s*([A-Z]+)\\s*(\\d+)\\s*(-?\\d+)\\s*$")) {
                        Pattern pattern = Pattern.compile("([A-Z]+)\\s*(\\d+)\\s*(-?\\d+)");
                        Matcher matcher = pattern.matcher(line);

                        if (matcher.find()) {
                            String currency = matcher.group(1);
                            String amount = matcher.group(2);
                            String unknownAmount = matcher.group(3);


                            currentTransaction.setDispensedAmount(amount);
                        }
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