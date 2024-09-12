package at.kaindorf.jpintro;

import at.kaindorf.jpintro.pojos.Account;
import at.kaindorf.jpintro.pojos.AccountPK;
import at.kaindorf.jpintro.repos.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 12.09.2024
 * Time: 08:44
 */
@RequiredArgsConstructor
@Component
public class AccountTest {
  private final AccountRepository accountRepo;

  @PostConstruct
  public void initAccounts() {
    Account account1 = Account.builder()
        .accountPK(new AccountPK("AT44-1234-5678-0000-0815", 2L))
        .balance(12.45)
        .build();
//    account1.setId(1L);
//    account1.setIban("AT44-1234-5678-0000-0815");
//    account1.setAccountPK(new AccountPK("AT44-1234-5678-0000-0815", 2L));
    account1.setBalance(123456.789212113131312);

    accountRepo.save(account1);
  }
}
