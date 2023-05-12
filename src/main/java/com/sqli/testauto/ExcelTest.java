package com.sqli.testauto;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class ExcelTest {
    WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/webDriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        //driver.get("https://wordpress.com/start/about?ref=create-blog-lp");
        driver.manage().window().maximize();

    }
    @Test
    public void Treatment() throws FilloException {
        String title = driver.getTitle();
        assertEquals("Web form", title);
        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(".//Data/dataa.xlsx");
        ///////////////////insert data into excel file
        //String strInsertQuerry = "INSERT INTO DATA1 (SiteTitle,SiteTopic) Values('sqli','selenium')";
        //connection.executeUpdate (strInsertQuerry);
        /////////////update data into excel file if I dont write where they update all rows of column
        //String strUpdateQuerry = "Update Data1 Set SiteTitle = 'sqli.com' WHERE SiteTitle='sqlioujda'";
        //connection.executeUpdate (strUpdateQuerry);
        //////////////delete data from excel file table
        //String strDeleteQuerry = "DELETE FROM Data1  WHERE SiteTitle='sqli.com'";
        //connection.executeUpdate (strDeleteQuerry);
        //select data from excel file
        //Execute the Select query and store the result in a Recordset class


        Recordset recordset =null;
        String strSelectQuerry = "Select * from  Data1";
        recordset = connection.executeQuery(strSelectQuerry);

        //use while loop to iterate through all columns and rows available in

        while(recordset.next()){
            System.out.println("Column 1 = " +recordset.getField("SiteTitle"));
            System.out.println("Column 2 = " +recordset.getField("SiteTopic"));
            connection.close();
        }

    }
    @After
    public void closeSession(){
        driver.quit();
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }


}