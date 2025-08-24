package javabank.model;

import java.util.List;

import lombok.Getter;

import static javabank.model.BankService.ACCOUNT;

@Getter
public class AccountWallate extends Wallet{
    private final Object pix;
    
    public AccountWallate(final List<String> pix) {
        super(ACCOUNT); 
        this.pix = pix;
    }
    public AccountWallate(String pix,long initialFunds) {
        super(ACCOUNT); 
        this.pix = pix;
    }
    public AccountWallate(final List<String> pix, final long initialFunds) {
        super(ACCOUNT); 
        this.pix = pix;
        addMoney(String.valueOf(initialFunds), "Valor De Criação Da Conta");
    }

    public void addMoney(final String pix2,final String description){
        var money = generateMoney(Long.parseLong(pix2), description);
        this.money.addAll(money);
        }
}
