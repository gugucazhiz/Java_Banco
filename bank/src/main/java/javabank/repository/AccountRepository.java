package javabank.repository;

import java.util.ArrayList;
import java.util.List;

import javabank.*;

import javabank.exception.AccountNotFoundException;

import javabank.model.AccountWallate;

public class AccountRepository {
    
    private static List<AccountWallate> accounts = new ArrayList<>();
    
    public static AccountWallate create(final String pix, final long initialFunds){
        var newAccount = new AccountWallate(pix, initialFunds);
        accounts.add(newAccount);
        return newAccount;
    }

    public void deposit(final String pix, final long fundsAmount){
        var target = findByPix(pix);
        target.addMoney(String.valueOf(fundsAmount), "Deposito");
    }

    public long withDraw(final String pix, final long amount){
        var source = findByPix(pix);
        source.reduceMoney(amount);
        return amount;

    }

    public void transferMoney(final String sourcepix, final String targetpix, final long fundsAmount){
        var source = findByPix(sourcepix);
        var target = findByPix(targetpix);
        var message = "Pix enviado De: '"+sourcepix+ "' Para: '"+targetpix+"'";
        target.addMoney(source.reduceMoney(fundsAmount),source.getService(),message);
    }

    public AccountWallate findByPix(String pix) {
        return accounts.stream()
        .filter(a -> a.getPix().equals(pix))
        .findFirst()
        .orElseThrow(() -> new AccountNotFoundException("Conta Não Encontrada"));
    }

    public long getBalance(final String pix){
        AccountWallate acc = findByPix(pix);
        return acc.getFunds();
    }
    public List<AccountWallate> list(){
        return this.accounts;
    }

}
