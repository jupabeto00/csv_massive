package com.ha.csv_massive.application;

import com.ha.csv_massive.domain.model.Account;

import java.util.List;

public interface AccountService {
	List<Account> saveAll(List<Account> accountList);
}
