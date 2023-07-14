package qtriptest;

import qtriptest.utils.ExcelUtillity;
import org.testng.annotations.DataProvider;

public class DP {
    @DataProvider(name ="QTripData")

    public Object[][] QTripData(){
        return ExcelUtillity.getDataFromExcel("TestCase01");
    }
}
