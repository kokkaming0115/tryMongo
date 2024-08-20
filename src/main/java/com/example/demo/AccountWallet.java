package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.function.Consumer;

@SpringBootApplication
public class AccountWallet {

	public static void main(String[] args) {
		SpringApplication.run(AccountWallet.class, args);
	}
/* 
	@Bean
	public Function<String, String> uppercase(){
		return value -> value.toUpperCase();
	}
*/

	@Bean
    public Consumer<String> sink(){
        return txtMsg -> {
			System.out.println(txtMsg);
			//JSON to Object

			//Save to Ledge

			//Update Cache
		};
    }

}
