package at.kaindorf.jpintro.repos;

import at.kaindorf.jpintro.pojos.Customer;
import at.kaindorf.jpintro.pojos.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 07.10.2024
 * Time: 10:29
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  List<Customer> getCustomerByLastname(String lastname);

  @Query("SELECT c FROM Customer c where c.firstname LIKE ?1")
  List<Customer> getCustomerByFirstname(String firstname, Sort sort);

  Page<Customer> findByLastnameEndsWith(String lastname, Pageable page);

  List<Customer> findFirst10ByLastnameEndsWith(String lastname);

  List<Customer> findByLastnameEndsWithAndFirstnameStartsWith(String lastname, String firstname);

  List<Customer> findByLastnameEndsWithOrderByBirthdateAscLastnameDesc(String lastname);

  @Query("SELECT c FROM Customer c WHERE c.address.city LIKE ?1")
  List<Customer> findCustomerFromCity(String cityname);

  List<Customer> findByAddress_CityLike(String cityname);

  // Query-1: Get all customers who have at least one account
  //          where the balance is between two given values, e.g.
  //          between 1000.- and 2000.-
  @Query("SELECT c FROM Customer c JOIN c.accounts a WHERE a.balance between ?1 AND ?2")
  List<Customer> getAccountsByBalanceBetween();

  List<Customer> findByAccounts_BalanceBetween(Double value1, Double value2);

  // Query-2: Get all male customer that are older than a give Date.
  //          Sort by birthdate, lastname and firstname. Paginate for
  //          5 customer
  @Query("SELECT c FROM Customer c WHERE c.gender = ?1 AND c.birthdate >= ?2")
  Page<Customer> getMaleCustomerFilteredandSorted(Gender gender, LocalDate birthdate, Pageable page);

  Page<Customer> findByGenderEqualsAndBirthdateGreaterThanEqual(Gender gender, LocalDate birthdate, Pageable page);

  // Query-3: Get all female customer who have exactly one saving
  //          or one giro account. Sort by lastname descending
  @Query("SELECT c FROM Customer c WHERE c.gender = ?1 AND size(c.accounts) = 1")
  List<Customer> getFemaleCustomerWithExactlyOneAccountSorted(Gender gender, Sort sort);

  // Query-4: Get the number of male customer that have a balance
  //          less than a give value.
  @Query("SELECT count(c) FROM Customer c JOIN c.accounts a WHERE c.gender = ?1 AND a.balance < ?2")
  Long countMaleCustomerWithBalance(Gender gender, Double balance);

  Long countByGenderEqualsAndAccounts_BalanceLessThan(Gender gender, Double balance);

  // Query-5: Get the number of female customers that have a giro
  //         account with an overdraft higher than a given value.
  @Query("SELECT count(c) FROM Customer c JOIN c.accounts a WHERE c.gender = ?1 AND treat(a as GiroAccount).overdraft = ?2")
  Long countFemaleCustomerWithOverdraft(Gender gender, Double overdraft);

  // Query-6: Get the total number of accounts of all customers living
  //          in a given city
  @Query("SELECT count(a) FROM Account a JOIN Customer c WHERE c.address.city = ?1")
  Long countAccountFromCustomerInCity(String city);

  // Query-7: Get the customer with the highest balance of a given city
  @Query("SELECT DISTINCT c FROM Customer c JOIN c.accounts a WHERE a.balance = (SELECT max(a2.balance) FROM Account a2) AND c.address.city = ?1")
  List<Customer> getCustomerWithHighestBalanceInCity(String city);

  // Query-8: Get a list of all different cities sorted by their name
  @Query("SELECT DISTINCT c.address.city FROM Customer c ORDER BY c.address.city")
  List<String> getDifferentCitiesSorted();

  // Query-9: Get the city names of the three oldest customers
  @Query("SELECT DISTINCT c.address.city FROM Customer c ORDER BY c.birthdate DESC")
  List<String> getCitiesOfOldestCustomers();

  // Query-10: Get a list of all customers with the largest difference
  //           between credit- and debit-interests

}
