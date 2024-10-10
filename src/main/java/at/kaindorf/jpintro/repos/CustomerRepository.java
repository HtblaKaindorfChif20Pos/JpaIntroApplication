package at.kaindorf.jpintro.repos;

import at.kaindorf.jpintro.pojos.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
