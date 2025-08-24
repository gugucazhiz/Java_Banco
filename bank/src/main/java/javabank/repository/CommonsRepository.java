package javabank.repository;

import lombok.NoArgsConstructor;

import static javabank.model.BankService.ACCOUNT;
import static lombok.AccessLevel.PRIVATE;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javabank.exception.NoFundsEnoughException;
import javabank.model.AccountWallate;
import javabank.model.Money;
import javabank.model.MoneyAudit;

@NoArgsConstructor(access = PRIVATE)
public final class CommonsRepository {
    

    public static void checkFundsForTransaction(final AccountWallate source, final long amount){
        if(source.getFunds() < amount){
            throw new NoFundsEnoughException("Dinheiro Não suficiente, para esse Trasação");
        }
    }

    public static List<Money> generateMoney(final UUID transactionid, final long funds,final String Description){
        var history =  new MoneyAudit(transactionid, ACCOUNT, Description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history)).limit(funds).toList();
    }
}
