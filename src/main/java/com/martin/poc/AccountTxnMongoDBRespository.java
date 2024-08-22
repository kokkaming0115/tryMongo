package com.martin.poc;

import org.springframework.data.repository.CrudRepository;

public interface AccountTxnMongoDBRespository extends CrudRepository<AccountTxn, String>{

}
