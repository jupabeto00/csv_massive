package com.ha.csv_massive.domain.ports;

import com.ha.csv_massive.domain.model.Account;

import java.util.List;

public interface AccountPort {
	List<Account> saveAll(List<Account> accountList);
}
