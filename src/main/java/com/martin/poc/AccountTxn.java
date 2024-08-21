package com.martin.poc;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AccountTxn{
    
    @Id
    private String id;
    private String accountNo;
    private long accountBalance;
    private long waterMark;
    private LocalDateTime waterMarkDT;
    
    public AccountTxn(){}

    public AccountTxn(String accountNo, long accountBalance, long waterMark, LocalDateTime waterMarkDT) {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
        this.waterMark = waterMark;
        this.waterMarkDT = waterMarkDT;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public long getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(long balance) {
        this.accountBalance = balance;
    }
    public long getWaterMark() {
        return waterMark;
    }
    public void setWaterMark(long waterMark) {
        this.waterMark = waterMark;
    }

    public LocalDateTime getWaterMarkDT() {
        return waterMarkDT;
    }

    public void setWaterMarkDT(LocalDateTime waterMarkDT) {
        this.waterMarkDT = waterMarkDT;
    }
    

    @Override
    public String toString() {
        return "AccountTxn [accountNo=" + accountNo + ", accountBalance=" + accountBalance + ", waterMark=" + waterMark
                + ", waterMarkDT=" + waterMarkDT + "]";
    }

 


    

    
}