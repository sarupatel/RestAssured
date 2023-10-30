package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum=xl.getRowCount("Sheet1");
		int colcount=xl.getCellCount("Sheet1", 1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name="UserName")
	public String[] getUSerName() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum=xl.getRowCount("Sheet1");
		int colcount=xl.getCellCount("Sheet1", 1);
		
		for(int i=0; i<colcount; i++)
		{
			if(xl.getCellData("Sheet1", 0, i).equalsIgnoreCase("username"))
			{
				colcount=i;
				break;
			}
		}
		
		String apidata[]=new String[rownum];
		
		for(int i=1; i<=rownum; i++)
		{
			apidata[i-1]=xl.getCellData("Sheet1", i, colcount);
		}
		return apidata;
	}
}
