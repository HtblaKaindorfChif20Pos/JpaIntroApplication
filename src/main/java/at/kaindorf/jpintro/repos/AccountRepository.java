package at.kaindorf.jpintro.repos;

import at.kaindorf.jpintro.pojos.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 12.09.2024
 * Time: 08:40
 */
public interface AccountRepository extends JpaRepository<Account, String> {
}
