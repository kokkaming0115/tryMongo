package com.martin.poc.mongo;

import org.springframework.data.repository.CrudRepository;

import com.martin.poc.AccountTxn;

public interface AccountTxnMongoDBRespository extends CrudRepository<AccountTxn, String>{

}
