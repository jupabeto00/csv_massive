package com.ha.csv_massive.driven.postgresql.mapper;

import com.ha.csv_massive.domain.model.Account;
import com.ha.csv_massive.driven.postgresql.entity.AccountEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
	AccountEntity toEntity(Account account);
	List<AccountEntity> toEntity(List<Account> account);

	Account toDto(AccountEntity accountEntity);
	List<Account> toDto(List<AccountEntity> accountEntity);
}