package PageObjectModel;

import org.testng.annotations.DataProvider;

public class ParameterizingTestData {

	//writing data provider method for Apidemotest edit bax
	@DataProvider(name="InputData")
	public Object[][] getEditBoxData()
	{
		Object[][] obj=new Object[][]
						{
							{"Hello"},{"#@!&%"}
						};
		return obj;
	}
	
	
}
