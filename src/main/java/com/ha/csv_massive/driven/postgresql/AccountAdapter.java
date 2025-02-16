package com.ha.csv_massive.driven.postgresql;

import com.ha.csv_massive.domain.model.Account;
import com.ha.csv_massive.domain.ports.AccountPort;
import com.ha.csv_massive.driven.postgresql.entity.AccountEntity;
import com.ha.csv_massive.driven.postgresql.mapper.AccountMapper;
import com.ha.csv_massive.driven.postgresql.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountAdapter implements AccountPort {

	private AccountRepository accountRepository;
	private AccountMapper accountMapper;

	@Autowired
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Autowired
	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Override
	public List<Account> saveAll(List<Account> accountList) {
		List<AccountEntity> accountEntities = accountMapper.toEntity(accountList);
		List<Account> accountListSaved = accountMapper.toDto(
				accountRepository.saveAll(accountEntities)
		);
		return accountListSaved.isEmpty() ? null : accountListSaved;
	}
}
