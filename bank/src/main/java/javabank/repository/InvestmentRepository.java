package javabank.repository;
import javabank.*;
import javabank.exception.InvestmentNotFoundException;
import javabank.exception.WalletNotFoundException;
import javabank.model.AccountWallate;
import javabank.model.Investment;
import javabank.model.InvestmentWallet;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InvestmentRepository {
    private long nextid = 1;
    private final List<Investment> Investment = new ArrayList<>();
    private final List<InvestmentWallet> wallets= new ArrayList<>();

    public Investment create(final long tax, final long initialFunds,final long daysToRescue){
        this.nextid++;
        var investment = new Investment(this.nextid, tax, initialFunds);
        Investment.add(investment);
        return investment;
    }

    public InvestmentWallet iniInvestmentWallet(final AccountWallate accountWallate, final long id){
        var investment = findById(id);
        var wallet = new InvestmentWallet(investment, accountWallate, investment.initialFunds());
        wallets.add(wallet);
        return wallet;
    }
    public InvestmentWallet deposit(final String pix, final long amount){
        var wallet = fInvestmentWallet(pix);
        wallet.addMoney(wallet.getAccount().reduceMoney(amount), wallet.getService(), "ADD to:"+ pix);
        return wallet;
    }

    public long getBalance(final String pix){
        var wallet = fInvestmentWallet(pix);
        return wallet.getFunds();
    }

    public InvestmentWallet withDraw(final String pix, final long amount, final String description){
        var wallet = fInvestmentWallet(pix);
        wallet.reduceMoney(amount);
        return wallet;
    }

    public void updateAmount(final long percent){
        wallets.forEach(w -> w.updateAmount(w.getInvestment().tax()));
    }
    public Investment findById(final long id){
        return Investment.stream()
                .filter(i -> i.id() == id)
                .findFirst()
                .orElseThrow(
                        () -> new InvestmentNotFoundException("O investimento não foi encontrado")
                );
    }

    public InvestmentWallet fInvestmentWallet(final String pix){
        return wallets.stream()
                    .filter(w -> ((List<Investment>) w.getAccount().getPix()).contains(pix))
                    .findFirst()
                    .orElseThrow(
                        () -> new WalletNotFoundException("A carteira não foi encontrada")
                    );
    }

    public List<InvestmentWallet> listWallets(){
        return this.wallets;
    }

    public  List<Investment> list(){
        return this.Investment;
    }
}
