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
  @DisplayName("Test to get all customers called ...mann")
  public void testGetCustomerByLastname() {
//    List<Customer> customerList = customerRepo.getCustomerByLastname("%mann");
//    List<Customer> customerList = customerRepo.findByLastnameEndsWith("mann");
    List<Customer> customerList = customerRepo.findFirst10ByLastnameEndsWith("mann");
    int expected = 10;
    int actual = customerList.size();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test to get all customers with firstname ...mann")
  public void testGetCustomerByFirstname() {
    List<Customer> customerList = customerRepo.getCustomerByFirstname("El%");
    int expected = 48;
    int actual = customerList.size();
    assertEquals(expected, actual);
  }


}