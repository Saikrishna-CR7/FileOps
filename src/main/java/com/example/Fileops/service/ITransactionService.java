package com.example.Fileops.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ITransactionService {

	long processTransaction(Map<String, List<Date>> timeMap);

}
