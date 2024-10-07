package at.kaindorf.jpintro.repos;

import at.kaindorf.jpintro.pojos.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 07.10.2024
 * Time: 10:29
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
