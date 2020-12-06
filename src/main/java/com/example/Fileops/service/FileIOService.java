package com.example.Fileops.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileIOService implements IFileIOService {

	private static final Logger LOGGER = LogManager.getLogger(FileIOService.class);
	
	Map<String, List<Date>> timeMap = new HashMap<>();

	private static String PATTERN = "yyyy-MM-dd hh:mm aa";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
	private static String FILE_NAME = "Sample.txt";
	
	@Autowired
	ITransactionService transactionService;

	@Override
	public void readFile() {

		try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
			
			stream.forEach(s -> mapData(s));
			long average = transactionService.processTransaction(timeMap);
			System.out.println("Average time for each transaction is : " + average + " min/mins");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void mapData(String s) {
		List<String> list = Arrays.asList(s.split(","));
		List<Date> dateList = new ArrayList<>();
		Date date = new Date();
		if (Objects.nonNull(timeMap.get(list.get(0)))) {
			dateList = timeMap.get(list.get(0));
		}
		try {
			date = simpleDateFormat.parse(list.get(1) + " " + list.get(2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateList.add(date);
		timeMap.put(list.get(0), dateList);
	}

}
