package com.api.collector.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class DCollector {
	
	private InitChromeDriver myDriver = null;
	
	public DCollector() { 
		// 1. 크롬 드라이버 연결
		this.myDriver = new InitChromeDriver();
	}
	
	/**
	 * <pre>
	 * 	URL          = HTML을 분석할 웹 사이트 링크
	 * 	cssSelectors = 수집할 HTML 요소(태그)의 선택자
	 * </pre>
	 * */
	public List<WebElement> collect(String URL, String cssSelector) {
		
		// 2. 웹 페이지 요청
		myDriver.setURL(URL);
		// 3. 초기 HTML 수집 
		List<WebElement> elements = myDriver.getElements(cssSelector);
		// 4. 데이터 수 확인
		System.out.println( "조회된 콘텐츠 수 : "+elements.size() );
		// 5. 반환
		return elements != null ? elements : Collections.emptyList();
	} // collect
	
	public void close() {
		this.myDriver.close();
	}
	
} // class
