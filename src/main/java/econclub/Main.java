package econclub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "memberdata.csv";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Map<String, Integer> monthYearCountsadds = new HashMap<>();
        Map<String, Integer> monthYearCountsdrops = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Skip the first line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                try {
                    Date date = dateFormat.parse(values[7]);
                    SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM/yyyy");
                    String monthYear = monthYearFormat.format(date);

                    if (monthYearCountsadds.containsKey(monthYear)) {
                        int count = monthYearCountsadds.get(monthYear);
                        monthYearCountsadds.put(monthYear, count + 1);
                    } else {
                        monthYearCountsadds.put(monthYear, 1);
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date in line: " + line);
                }
                catch (IndexOutOfBoundsException e){
                }
                try {
                    Date date = dateFormat.parse(values[8]);
                    SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM/yyyy");
                    String monthYear = monthYearFormat.format(date);

                    if (monthYearCountsdrops.containsKey(monthYear)) {
                        int count = monthYearCountsdrops.get(monthYear);
                        monthYearCountsdrops.put(monthYear, count + 1);
                    } else {
                        monthYearCountsdrops.put(monthYear, 1);
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date in line: " + line);
                }
                catch (IndexOutOfBoundsException e){
                }

            }
            TreeMap<String, Integer> sorted = new TreeMap<>(monthYearCountsadds);
            TreeMap<String, Integer> sorted2 = new TreeMap<>(monthYearCountsdrops);


        CSVToExcel.writeExcelFile(sorted, "adds 2013-2022");
        CSVToExcel.writeExcelFile(sorted2, "drops 2013-2022");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
