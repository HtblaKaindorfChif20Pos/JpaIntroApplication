package at.kaindorf.jpintro.pojos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountTest {

  @Autowired
  private EntityManager em;

  @Test
  @Rollback(false)
  public void testInsertOfGiroccount() {
    Account giro1 = GiroAccount.builder()
        .accountNumber(123456L)
        .balance(0.0d)
        .creditInterest(6.0f)
        .debitInterest(7.0f)
        .overdraft(1000.0d)
        .build();
    em.persist(giro1);
  }

  @Test
  @DisplayName("Balance Test")
  public void testAccountBalance() {
    double expected = 12.45;
    double actual = em.find(Account.class, new AccountPK("AT44-1234-5678-0000-0815", 2L))
                      .getBalance();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Account number Test")
  public void testNofAccounts() {
    TypedQuery<Long> numOfAccounts = em.createQuery("SELECT count(a) FROM Account a",Long.class);
    long actual = numOfAccounts.getSingleResult();
    long expected = 1l;
    assertEquals(expected, actual);
  }


  
}