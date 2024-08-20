package com.example.demo;

public class AccountTxn{
    private String accountNo;
    private long accountBalance;
    private long waterMark;
    
    public AccountTxn(String accountNo, long accountBalance, long waterMark) {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
        this.waterMark = waterMark;
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

    @Override
    public String toString() {
        return "AccountTxn [accountNo=" + accountNo + ", balance=" + accountBalance + ", waterMark=" + waterMark + "]";
    }

    

    
}