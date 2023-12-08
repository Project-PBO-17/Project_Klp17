package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {
    private final ArrayList<String> csvDataList = new ArrayList<>();

    public ReadCSV() {
//        String csvUri = "assets/dataset.csv";
        String csvUri = "E:\\IT\\Code\\Java swing GUI\\sini\\New folder\\Modul4\\src\\assets\\dataset.csv";

        readCSV(csvUri);
    }

    private void readCSV(String pathCSV) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathCSV));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                csvDataList.add(values[0]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCsvDataList() {
        return csvDataList;
    }

    public static void main(String[] args) {
        ReadCSV readCSV = new ReadCSV();
        readCSV.getCsvDataList();
    }
}

