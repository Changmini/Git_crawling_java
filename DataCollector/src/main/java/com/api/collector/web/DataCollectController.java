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

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.api.collector.service.DCollector;



@Controller
public class DataCollectController {
	
	@Autowired
	private DCollector dc;
	
	@RequestMapping(value = "/test.do")
	public String callCollectors() {
		String URL = "https://www.airbnb.co.kr/s/homes?refinement_paths%5B%5D=%2Fhomes&date_picker_type=flexible_dates&search_mode=flex_destinations_search&search_type=AUTOSUGGEST";
		List<WebElement> contents = dc.collect(URL, "div.c8ohbfz.dir.dir-ltr");
		
		int cSize = contents.size();
		String[] hrefArr = new String[cSize];
		int idx = 0;
		// 5. 데이터 분석
		if( cSize > 0 ) {
		    // 에어비앤비 숙박 리스트 가져오기
		    for(WebElement content : contents ) {
		        try {
		        	// 숙박 URL 정보
		            hrefArr[idx++] = content.findElement(By.cssSelector("a")).getAttribute("href");
		            
		        } catch ( NoSuchElementException e ) {
		            // pass
		        }
		    }
		}
		
		System.out.println(Arrays.toString(hrefArr));
		
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
		
		
		System.out.println("success");
		return "test";
	}
	
	
	
	
	
	
	
	
	
	
	

} // class


















