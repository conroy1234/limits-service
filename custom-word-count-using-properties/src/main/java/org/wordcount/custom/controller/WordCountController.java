package org.wordcount.custom.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wordcount.custom.factory.WordCountConfig;
import org.wordcount.custom.model.Word;

@RestController
public class WordCountController {
	@Autowired
	MessageSource messageSource;
	
	
	WordCountConfig wordCountConfig =  WordCountConfig.newInstance();	
	

	@GetMapping("/word/search/{search}")
	public List<Word> findWordInList(@RequestHeader(name="Accept-Language",required=false) Locale locale,@PathVariable String search) {			
		Word word = wordCountConfig.wordContainerC(search, Arrays.asList( messageSource.getMessage("word-searchdisplay.message", null,locale)) );
		System.out.println(word);	
	return Arrays.asList(word);
		
	}
	
	@GetMapping("/word/search-java8/{search}")
	public List<Word> findWordUsingJava8(@RequestHeader(name="Accept-Language",required=false) Locale locale,@PathVariable String search) {			
		String [] result =messageSource.getMessage("word-searchdisplay.message", null,locale).split(" ");	
		Word word = WordCountConfig.findword(search,Arrays.asList(result));
		System.out.println(word);	
	return Arrays.asList(word);
		
	}
	

	@GetMapping("/word/search/charactor/{c}")
	public List<Word> findCharactorsingJava8(@RequestHeader(name="Accept-Language",required=false) Locale locale,@PathVariable char c) {			
		Word word = WordCountConfig.findCharactor(c, messageSource.getMessage("word-searchdisplay.message", null,locale));
		System.out.println(word);	
	return Arrays.asList(word);
		
	}


}
