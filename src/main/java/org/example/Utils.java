package org.example;

public class Utils {

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

    public static String[] splitPINLine(String line) {
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

    public static String[] splitAmountInformationFDKLine(String line) {
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
    public static String[] splitGenACLine(String line) {
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
    public static String[] splitTNXRequestLine(String line) {
        // Find the index of the first occurrence of ' ' (whitespace) to split time and key-value pair
        int spaceIndex = line.indexOf(' ');
        // Extract time
        String time = line.substring(0, spaceIndex).trim();
        // Extract key-value pair
        String keyValue = line.substring(spaceIndex).trim();
        // Find the index of the first occurrence of ',' within the key-value pair to split key and value
        int commaIndex = keyValue.indexOf(',');
        // Extract key
        String key;
        // Extract value
        String value;
        if (commaIndex != -1) {
            // If the line contains ',', split it into key and value
            key = keyValue.substring(0, commaIndex).trim();
            value = keyValue.substring(commaIndex + 1).trim();
        } else {
            // If there's no ',', consider the entire line as key and set an empty string as value
            key = keyValue.trim();
            value = ""; // No value present
        }
        return new String[]{time, key, value};
    }
    public static String[] splitTNXReplyLine(String line) {
        // Find the index of the first occurrence of ' ' (whitespace) to split time and key-value pair
        int spaceIndex = line.indexOf(' ');
        // Extract time
        String time = line.substring(0, spaceIndex).trim();
        // Extract key-value pair
        String keyValue = line.substring(spaceIndex).trim();
        // Find the index of the first occurrence of ',' within the key-value pair to split key and value
        int commaIndex = keyValue.indexOf(',');
        // Extract key
        String key;
        // Extract value
        String value;
        if (commaIndex != -1) {
            // If the line contains ',', split it into key and value
            key = keyValue.substring(0, commaIndex).trim();
            value = keyValue.substring(commaIndex + 1).trim();
        } else {
            // If there's no ',', consider the entire line as key and set an empty string as value
            key = keyValue.trim();
            value = ""; // No value present
        }
        return new String[]{time, key, value};
    }
    public static String[] splitGenAC2Line(String line) {
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
    public static String[] splitCardTakenLine(String line) {
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
    public static String[] splitNotesPresentLine(String line) {
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
    public static String[] splitCashShutterOpenedLine(String line) {
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
    public static String[] splitNotesTakenLine(String line) {
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
    public static String[] splitCashShutterClosedLine(String line) {
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

}
