package com.dk.pet.personal_finance_manager.mapper;

import com.dk.pet.personal_finance_manager.dto.transaction.CreateTransactionRequest;
import com.dk.pet.personal_finance_manager.dto.transaction.TransactionResponse;
import com.dk.pet.personal_finance_manager.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "user", ignore = true)
    Transaction requestToEntity(CreateTransactionRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    TransactionResponse entityToResponse(Transaction transaction);
}
