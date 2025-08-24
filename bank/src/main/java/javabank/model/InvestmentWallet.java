package javabank.model;

import static javabank.model.BankService.INVESTIMENT;

import java.util.UUID;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class InvestmentWallet extends Wallet{
    private final Investment investment;
    private final AccountWallate account;

    public InvestmentWallet(final Investment investment,final AccountWallate account,final long amount){
        super(INVESTIMENT);
        this.investment = investment;
        this.account =account;
        addMoney(account.reduceMoney(amount), getService(),"Investimento");
    }

    public void updateAmount(final long Percent){
        var amount =getFunds() *Percent /100;
        var history = new MoneyAudit(UUID.randomUUID(), getService(), "Rendimento", null);
        var money = Stream.generate(() -> new Money(history)).limit(amount).toList();
        this.money.addAll(money);
    }
    
}
