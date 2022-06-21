package com.api.collector.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

public class InitChromeDriver {
	
	private ChromeDriver driver = null;
	
	/**
	 * <pre>
	 * 설치 항목
	 *  1. chromedriver.exe (window)
	 *     - 인터넷 검색 
	 *  2. selenium-java
	 *     - dependency 추가
	 *     
	 *  #dependency#
	 *    groupId   : org.seleniumhq.selenium
	 *    artifactId: selenium-java
	 *    version   : 3.141.59
	 * </pre>
	 * */
	public InitChromeDriver() {
		// 1. WebDriver 경로 설정
        // Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
        Path path = Paths.get("C:/Users/ChangMin/Git/Git_crawling_java/DataCollector/", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", path.toString());
        
        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");          // 최대크기로
        options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        
        // 3. WebDriver 객체 생성
        this.driver = new ChromeDriver( options );
	} // [end] initChrome
	
	
	public void setURL(String URL) { this.setURL(URL, 1000); }
	public void setURL(String URL, Integer delay) {
		if(URL == null || URL.equals("") || URL.equals(" ")) { 
			System.out.println("method setURL: null param");
			return ;
		}
		this.driver.get(URL);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {}
	} // [end] searchHTML
	
	
	public List<WebElement> getElements(String cssSelector) {
		if (this.driver == null || cssSelector.equals("") || cssSelector.equals(" ")) { 
			System.out.println("method getElements: null param");
			return Collections.emptyList();
		}
		return this.driver.findElements(By.cssSelector(cssSelector));
	}
	// driver.close(); close를 통해 모든 탭이 종료되었을 때, WebDriver도 닫히게 되는데 표면적으로는 종료된 것처럼 보이지만 Process가 살아있어서 자원의 낭비를 유발함.
	// driver.quit(); close로 모든 탭을 종료시켰을 때와는 다르게 Process 자체를 종료함.
	public void close() {
		this.driver.close();
		this.driver.quit();
	}
} // class
