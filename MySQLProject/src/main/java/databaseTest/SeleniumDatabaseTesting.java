package databaseTest;

import java.sql.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver; 

public class SeleniumDatabaseTesting {
	
	public static void main(String[] args) {
		
		//Testdata
		String email = "vivek@rediffmail.com";
        String zipCode = "222222";
        String firstname = "Lucy";
        String lastName = "Singh";
		
		
		//Input Data using Selenium
		WebDriver driver = new ChromeDriver();
		driver.get("https://mailing.dollartree.com/user/signup.jsp");
		driver.findElement(By.xpath("//*[@id=\"emailAddress\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"zipCode\"]")).sendKeys(zipCode);
		driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys(firstname);
		driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys(lastName);
		driver.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div[3]/form/div[2]/input[4]")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[3]/div[2]/div[3]/form/div[3]/input[2]")).click();
		driver.close();
		
		// Connection to DataBase
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost:3306/";
		String databaseName = "dollarTree";
		String username = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url + databaseName, username, password);
			
//			String sqlQuerry = " Select * FROM consumers";
			String sqlQuerry ="SELECT * from Consumers ORDER BY ID DESC  LIMIT 1";
			Statement statment = conn.createStatement();
			ResultSet result = statment.executeQuery(sqlQuerry);
			
			result.next();
			System.out.println(result.getString("emailAddress"));
			System.out.println(result.getString("zipCode"));
			System.out.println(result.getString("firstname"));
			System.out.println(result.getString("lastName"));
				
			//Database Testing
			if(!result.getString("emailAddress").equals(email))
				System.out.println("Email ID is stored wrong");
			if(!result.getString("zipCode").equals(zipCode))
				System.out.println("Zipcode is stored wrong");
			if(!result.getString("firstname").equals(firstname))
				System.out.println("Firstname is stored wrong");
			if(!result.getString("lastName").equals(lastName))
				System.out.println("Lastname is stored wrong");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(conn != null) {
				conn = null;
			}
		}
		
	}

}
