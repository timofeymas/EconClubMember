package econclub;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CSVToExcel {

    public static void writeExcelFile(Map<String, Integer> data, String fileName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Adds");

        int rowNum = 1;
        int colNum = 0;
        Row random = sheet.createRow(0);
        for (int month = 1; month <= 12; month++) {
            Cell cell = random.createCell(month);
            switch (month) {
                case 1:
                    cell.setCellValue("Jan");
                    break;
                case 2:
                    cell.setCellValue("Feb");
                    break;
                case 3:
                    cell.setCellValue("Mar");
                    break;
                case 4:
                    cell.setCellValue("April");
                    break;
                case 5:
                    cell.setCellValue("May");
                    break;
                case 6:
                    cell.setCellValue("June");
                    break;
                case 7:
                    cell.setCellValue("July");
                    break;
                case 8:
                    cell.setCellValue("Aug");
                    break;
                case 9:
                    cell.setCellValue("Sep");
                    break;
                case 10:
                    cell.setCellValue("Oct");
                    break;
                case 11:
                    cell.setCellValue("Nov");
                    break;
                case 12:
                    cell.setCellValue("Dec");
                    break;

            }
        }
        for (int year = 2023; year >= 2013; year--) {
            Row row = sheet.createRow(rowNum++);
            colNum = 0;

            Cell yearCell = row.createCell(colNum++);
            yearCell.setCellValue(year);

            for (int month = 1; month <= 12; month++) {

                String monthYear = String.format("%02d", month) + "/" + year;
                Integer value = data.get(monthYear);

                Cell monthCell = row.createCell(colNum++);
                monthCell.setCellValue(value != null ? value : 0);
            }
        }


        try {
            FileOutputStream outputStream = new FileOutputStream(fileName + ".xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
