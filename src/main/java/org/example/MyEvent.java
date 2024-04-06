package org.example;

import com.google.gson.Gson;
        import java.util.ArrayList;
        import java.util.List;

public class MyEvent {
    public static void main(String[] args) {
        // Example event lines
        String[] eventLines = {
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
                "11:19:41 CAMERA  2 P20240304111940715.JPG"
        };

        // Parse event lines into a list of events
        List<Event> events = parseEvents(eventLines);

        // Convert events to JSON
        String json = convertToJson(events);

        // Print JSON string
        System.out.println(json);
    }

    // Method to parse event lines into a list of events
    public static List<Event> parseEvents(String[] eventLines) {
        List<Event> events = new ArrayList<>();
        for (String line : eventLines) {
            Event event = parseEvent(line);
            if (event != null) {
                events.add(event);
            }
        }
        return events;
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

    // Method to convert events to JSON
    public static String convertToJson(List<Event> events) {
        Gson gson = new Gson();
        return gson.toJson(events);
    }
}

