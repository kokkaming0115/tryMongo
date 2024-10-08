package com.martin.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martin.poc.mongo.AccountTxnMongoDBRespository;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.martin.poc", "com.martin.poc.*"})
public class AccountWallet {

	@Autowired
	private ObjectMapper om;
	@Autowired
	private AccountTxnMongoDBRespository accountTxnMongoDBRespository;

	public static void main(String[] args) {
		SpringApplication.run(AccountWallet.class, args);
	}
/* 
	@Bean
	public Function<String, String> uppercase(){
		return value -> value.toUpperCase();
	}
*/

	@SuppressWarnings("null")
	@Bean
    public Consumer<String> sink(){
        return txtMsg -> {
			System.out.println(txtMsg);
			AccountTxn accountTxn=null;
			//JSON to Object
			try {
				accountTxn = om.readValue(txtMsg, AccountTxn.class);
				//accountTxn.setWaterMarkDT(LocalDateTime.now());
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}
			System.out.println("Saved accountTxn=>"+accountTxn);
			//Save to Ledge
			accountTxnMongoDBRespository.save(accountTxn);

			//Update Cache
			//throw new RuntimeException("test error!");
		};
    }
/* 
	@Bean
	public Function<String, List<AccountTxn>> getAccountTxnAfterDT(){
		

		return accountTxnInput -> {
			System.out.println(accountTxnInput);

			List<AccountTxn> accountTxnList = new ArrayList<>();

			System.out.println(accountTxnInput);

			return accountTxnList;
		}
	}
*/
}
