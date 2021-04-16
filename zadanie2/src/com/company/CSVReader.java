package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class CSVReader {

    private final String[] headerRow;
    private final int columns;
    private final BufferedReader reader;

    public CSVReader(String filepath) throws IOException {
        reader = new BufferedReader(new FileReader(filepath));
        headerRow = reader.readLine().split(",");
        columns = headerRow.length;
    }

    public String[] getHeaderRow() {
        return headerRow;
    }

    public int getColumns() {
        return columns;
    }

    public Stream<String[]> getStreamOfLineValues() throws IOException {
        Stream.Builder<String[]> builder = Stream.builder();
        String line = reader.readLine();
        while (!line.equals(null)) {
            builder.add(line.split(","));
            line = reader.readLine();
        }
        return builder.build();
    }

}
