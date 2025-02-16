package com.ha.csv_massive.application.impl;

import com.ha.csv_massive.application.AccountService;
import com.ha.csv_massive.domain.model.Account;
import com.ha.csv_massive.domain.ports.AccountPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	private AccountPort accountPort;

	@Autowired
	public void setAccountPort(AccountPort accountPort) {
		this.accountPort = accountPort;
	}

	@Override
	public List<Account> saveAll(List<Account> accountList) {
		log.debug("Start -> Save Batch Account List: {}", accountList.size());
		List<Account> accounts = accountPort.saveAll(accountList);
		log.debug("End -> Save Batch Account List: {}", accountList.size());
		return accounts.isEmpty() ? null : accounts;
	}
}
