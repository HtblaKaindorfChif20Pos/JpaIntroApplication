package at.kaindorf.jpintro.database;

import at.kaindorf.jpintro.pojos.Account;
import at.kaindorf.jpintro.pojos.Customer;
import at.kaindorf.jpintro.repos.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 16.09.2024
 * Time: 10:52
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class InitDatabase {

  private final AccountRepository accountRepo;
  private final CustomerRepository customerRepo;

  @PostConstruct
  public void loadCustumerFromJson() {
    InputStream jsonInputStream = getClass().getResourceAsStream("/bankAccout_customer.json");
    ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());
    try {
      List<Customer> customerList = objectMapper.readerForListOf(Customer.class).readValue(jsonInputStream);
      log.info("Json-file read successfully");
      // Create Set<Account> that contains only different accounts
      Set<Account> allDifferentAccounts = customerList.stream()
          .flatMap(c -> c.getAccounts().stream())
          .collect(Collectors.toSet());
//      customerList.forEach(cust -> { cust.getAccounts().forEach(acc -> acc.addCustomer(cust));});
      // (1) Search and replace duplicate account-instances from accountList of all customers
      // (2) Add back-reference in customerList of account
      for (Customer customer : customerList) {
        for (int i = 0; i < customer.getAccounts().size(); i++) {
          Long accountNumber = customer.getAccounts().get(i).getAccountNumber();
          Account accountFromSet = allDifferentAccounts.stream()
              .filter(a -> a.getAccountNumber().equals(accountNumber))
              .findFirst()
              .get();
          accountFromSet.addCustomer(customer);
          customer.getAccounts().set(i, accountFromSet);
        }
      }
      log.info("Data-structure update finished");
      customerRepo.saveAll(customerList);
      log.info("all saved to database");
    } catch (IOException e) {
      log.error(e.toString());
      log.error("Reading Json-file failed");
    }
  }

}
