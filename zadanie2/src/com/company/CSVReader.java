package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class CSVReader {

    private final String[] headerRow;
    private final int columns;
    private final BufferedReader reader;
    private final String filepath;
    private int lines;
    private final String[][] sheet;

    public CSVReader(String filepath) throws IOException {
        this.filepath = filepath;
        reader = new BufferedReader(new FileReader(filepath));
        headerRow = reader.readLine().split(",");
        columns = headerRow.length;
        countLines();
        sheet = new String[lines][columns];
        fillSheet();
    }

    public String[][] getSheet() {
        return sheet;
    }

    public String getFilepath() {
        return filepath;
    }

    private void fillSheet() throws IOException {
        for (int i = 0; i < columns; i++) {
            sheet[0][i] = headerRow[i];
        }
        for (int i = 1; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                String[] row = reader.readLine().split(",");
                sheet[i][j] = row[j];
            }
        }

    }

    private void countLines() {
        try (BufferedReader lineReader = new BufferedReader(new FileReader(filepath))) {
            lines = 0;
            while (lineReader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public int getLines() {
        return lines;
    }
}