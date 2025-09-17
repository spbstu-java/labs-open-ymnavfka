package Translator;

import java.io.*;
import java.util.*;

public class Translator {
    private final Map<String, String> dictionary = new HashMap<>();

    public Translator(String file) throws InvalidFileFormatException, FileReadException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 2)
                    throw new InvalidFileFormatException("Invalid line: " + line);
                dictionary.put(parts[0].trim().toLowerCase(), parts[1].trim());
            }
        } catch (IOException e) {
            throw new FileReadException("File error: " + e.getMessage());
        }
    }

    public String translate(String text) {
        String result = text.toLowerCase();
        List<String> keys = new ArrayList<>(dictionary.keySet());
        keys.sort((a, b) -> b.length() - a.length()); // длинные сначала
        for (String k : keys) {
            result = result.replaceAll("\\b" + k + "\\b", dictionary.get(k));
        }
        return result;
    }
}
