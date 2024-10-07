package at.kaindorf.jpintro.repos;

import at.kaindorf.jpintro.pojos.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepo;

  @Test
  @DisplayName("Finding top three lastnames matching %au%")
  public void testTopThree() {
    List<Customer> topThree = customerRepo.findFirst3ByLastnameLikeOrderByLastnameAscFirstnameDesc("%ay%");
//    List<Customer> topThree = customerRepo.findAll();
    System.out.println(topThree);
    assertEquals(topThree.size(), 3);

  }

  @Test
  @DisplayName("Finding top three lastnames matching %au%")
  public void test2() {
    Long res = customerRepo.countByLastnameContaining("ay");
    assertEquals(8, res);
  }

  @Test
  @DisplayName("Finding top three lastnames matching %au%")
  public void test3() {
    List<Customer> list = customerRepo.findByAccounts_BalanceLessThan(0.0);
    System.out.println("Hello");
    assertEquals(303, list.size());
  }
}