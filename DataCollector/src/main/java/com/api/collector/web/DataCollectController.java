/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.api.collector.web;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.collector.service.DCollector;



@Controller
public class DataCollectController {
	
	@Autowired
	private DCollector dc;
	
	@RequestMapping(value = "/test.do")
	public String callCollectors() {
		String URL = "set url";
		List<WebElement> contents = dc.collect(URL, "div.cy5jw6o.dir.dir-ltr"); 
		
		int cSize = contents.size();
		String[] hrefArr = new String[cSize];
		String[] DesArr = new String[cSize]; 
		int idx = 0;
		// 5. 데이터 분석
		if( cSize > 0 ) {
		    // 에어비앤비 숙박 리스트 가져오기
		    for(WebElement content : contents ) {
		        try {
		        	// 숙박 URL 정보 
		        	// content의 HTML을 확인할 때, content.getAttribute("innerHTML"));
		            hrefArr[idx] = content.findElement(By.cssSelector("a")).getAttribute("href");
		            DesArr[idx++] = content.findElement(By.cssSelector("div:nth-child(2)")).getAttribute("innerHTML");
		        } catch ( NoSuchElementException e ) {
		        	System.out.println("/test.do crawling error");
		        	e.printStackTrace();
		        }
		    }
		}
		System.out.println(Arrays.toString(DesArr));
		/*
		for (int i = 0; i < hrefArr.length; i++) {
			List<WebElement> conts = dc.collect(hrefArr[i], "data url");
			
			int cs = conts.size();
			if (cs > 0) {
				for(WebElement cont : conts) {
					try {
						String here = cont.findElement(By.cssSelector("a")).getAttribute("text");
					} catch (NoSuchElementException e) {
						// pass
					}
				}
			}
		}
		*/
		
		dc.close();
		System.out.println("success");
		return "test";
	}
	
	
	
	
	
	
	
	
	
	
	

} // class


















