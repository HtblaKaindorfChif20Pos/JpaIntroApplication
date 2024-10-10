package at.kaindorf.jpintro.repos;

import at.kaindorf.jpintro.pojos.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@DataJpaTest(showSql = false)
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

//    customerRepo.findAll(sort);
    int expected = 10;
    int actual = customerList.size();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test to get all customers with firstname ...mann")
  public void testGetCustomerByFirstname() {
    Sort sort = Sort.by(Sort.Direction.DESC, "lastname", "firstname");
//    sort = Sort.by(Sort.Order.asc("lastname"), Sort.Order.desc("firstname"));
//    sort = Sort.by(Sort.Direction.DESC, "lastname").and(Sort.by("firstname"));
    List<Customer> customerList = customerRepo.getCustomerByFirstname("El%", sort);
    int expected = 48;
    int actual = customerList.size();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test derived query method findByLastnameEndsWith")
  public void testFindByLastnameEndsWith()
  {
    Pageable page = PageRequest.of(0,5);
    Page<Customer> customerPage = customerRepo.findByLastnameEndsWith("mann", page);
    int expected = 5;
    int actual = customerPage.getNumberOfElements();
    assertEquals(expected, actual);
  }


}