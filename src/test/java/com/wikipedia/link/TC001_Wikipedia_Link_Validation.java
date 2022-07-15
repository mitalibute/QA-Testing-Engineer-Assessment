package com.wikipedia.link;



import java.sql.Driver;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC001_Wikipedia_Link_Validation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		 // locating chrome.exe file
			System.setProperty("webdriver.chrome.driver", "/Users/mitalivishalbute/Mitali/busyQA/eclipse-workspace-1/AutomationExercise/src/test/resources/BrowserExeFile/chromedriver");
			
			// open browser
			
			WebDriver driver = new ChromeDriver();
			
			// Maximize browser
			
			driver.manage().window().maximize();
			
			
			// navigate to page https://en.wikipedia.org/wiki/Main_Page/ ( Accepts a Wikipedia link)
			
			driver.get("https://en.wikipedia.org/wiki/Main_Page");
			
			
			
		
			//  Accepts a valid integer between 1 to 20 - call it n
			
			int iterationCount = Integer.parseInt(args[0]);
			
			if(iterationCount<1 || iterationCount>20)	{
				System.out.println("Please enter a number that is between 1 and 20");
				System.exit(0);
			}else {
				System.out.println("Count entered is "+iterationCount);
			}
			
			//  Scrape the link provided in Step 1, for all wiki links embedded in the page and store them in a data structure 
	
			 	
												
             List<WebElement> linkList = driver.findElements(By.xpath("//a"));
             System.out.println("Number of links "+linkList.size());

             
     		//  Repeat Step 3 for all newly found links and store them in the same data structure
             
             for (int linkCounter=0; linkCounter< linkList.size(); linkCounter++) {
            	 System.out.println(linkList.get(linkCounter).toString() + " , " +linkList.get(linkCounter).getText()+ " and '" +linkList.get(linkCounter).getAttribute("href")+"'");
            	 
            	 if(linkList.get(linkCounter).getAttribute("href")!=null) {
            		 WebDriver childPageDriver = new ChromeDriver();
            		 childPageDriver.get(linkList.get(linkCounter).getAttribute("href"));
            	     linkList.addAll(childPageDriver.findElements(By.xpath("//a")));
            	     childPageDriver.quit();
            	     System.out.println("Interim Number of links after cycle "+ (linkCounter+1)+ " is "+linkList.size());
            	     
            	 }  
            	 if(linkCounter>=iterationCount) {
            		 break;
            	 }
             }
             System.out.println("Final Number of links "+linkList.size());
             
 
             
	              
	              

	              
              
			
		
		
	}

}
