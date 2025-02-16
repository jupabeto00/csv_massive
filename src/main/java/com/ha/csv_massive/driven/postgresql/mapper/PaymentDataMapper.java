package com.ha.csv_massive.driven.postgresql.mapper;

import com.ha.csv_massive.domain.model.PaymentData;
import com.ha.csv_massive.driven.postgresql.entity.PaymentDataEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentDataMapper {
	PaymentDataEntity toEntity(PaymentData paymentData);
	List<PaymentDataEntity> toEntity(List<PaymentData> paymentData);

	PaymentData toDto(PaymentDataEntity paymentDataEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PaymentDataEntity partialUpdate(PaymentData paymentData, @MappingTarget PaymentDataEntity paymentDataEntity);
}