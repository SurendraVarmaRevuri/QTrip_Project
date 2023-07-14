package qtriptest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtillity {
    public static String[][] getDataFromExcel(String sheetName) {
        try {
            File excelFileData = new File("/home/crio-user/workspace/varmarevuri53-ME_QTRIP_QA_V2/app/src/test/resources/DatasetsforQTrip.xlsx");
            FileInputStream fis = new FileInputStream(excelFileData);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            String[][] data = new String[rowCount - 1][2];

            for (int row = 1; row < rowCount; row++) {
                String username = sheet.getRow(row).getCell(1).toString();
                String password = sheet.getRow(row).getCell(2).toString();
                data[row - 1][0] = username;
                data[row - 1][1] = password;
            }

            workbook.close();
            fis.close();

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String[][] data = ExcelUtillity.getDataFromExcel("TestCase01");
        if (data != null) {
            for (String[] arrValue : data) {
                System.out.println("Username: " + arrValue[0] + ", Password: " + arrValue[1]);
            }
        }
    }
}
