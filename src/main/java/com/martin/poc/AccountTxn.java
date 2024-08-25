package com.martin.poc;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ledger")
public class AccountTxn{
    
    @Id
    private String id;
    private String accountNo;
    private long amount;
    private long waterMark;
    private LocalDateTime waterMarkDT;
    
    public AccountTxn(){}

    public AccountTxn(String accountNo, long amount, long waterMark, LocalDateTime waterMarkDT) {
        this.accountNo = accountNo;
        this.amount = amount;
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
    public long getAmount() {
        return amount;
    }
    public void setAmount(long balance) {
        this.amount = balance;
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
        return "AccountTxn [accountNo=" + accountNo + ", amount=" + amount + ", waterMark=" + waterMark
                + ", waterMarkDT=" + waterMarkDT + "]";
    }

 


    

    
}