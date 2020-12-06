package com.example.Fileops.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {
	
	private static final Logger LOGGER = LogManager.getLogger(TransactionService.class);
	
	//number of ended transactions.
	int count = 0;
	
	//average of all transactions
	long average = 0;
	
	@Override
	public long processTransaction(Map<String, List<Date>> timeMap) {
		Set<Entry<String, List<Date>>> entrySet = timeMap.entrySet();

		entrySet.stream().forEach(entry -> calculateTime(entry));
		average = average % count;
		return average;
	}

	private void calculateTime(Entry<String, List<Date>> entry) {
		long diff = 0;
		if (entry.getValue().size() < 2) {
			LOGGER.info(entry.getKey() + " : doesnt have end time");
		} else {
			long diffInMillies = Math.abs(entry.getValue().get(0).getTime() - 
					entry.getValue().get(1).getTime());
			LOGGER.info("Difference in milliseconds for Transaction : " + entry.getKey() + " :: " + diffInMillies);
		    diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    count = count + 1;
		}
		 average = average + diff;
	}

}
