package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class CSVReader {

    private final String[] headerRow;
    private final int columns;
    private final BufferedReader reader;
    private final String filepath;
    private int lines;

    public CSVReader(String filepath) throws IOException {
        this.filepath = filepath;
        reader = new BufferedReader(new FileReader(filepath));
        headerRow = reader.readLine().split(",");
        columns = headerRow.length;
        countLines();
        Stream<String> stream = streamValues();
    }

    public int getColumns() {
        return columns;
    }

    private void countLines() {
        try (BufferedReader lineReader = new BufferedReader(new FileReader(filepath))) {
            lines = 0;
            while (lineReader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLines() {
        return lines;
    }

    public Stream<String> streamValues() throws FileNotFoundException {
        Stream.Builder<String> builder = Stream.builder();
        Scanner scanner = new Scanner(new File(filepath));
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            for (String string :
                    scanner.nextLine().split(",")) {
                builder.add(string);
            }
        }
        return builder.build();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Plik ");
        sb.append(filepath).append(" ma ").append(columns).append(" kolumn danych:\n");
        int i = 0;
        for (String string :
                headerRow) {
            sb.append(i).append(") ").append(string).append("\n");
            i++;
        }
        sb.append("Plik ten ma ").append(lines).append(" linijek długości.");
        return sb.toString();
    }
}