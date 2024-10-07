package at.kaindorf.jpintro.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 19.09.2024
 * Time: 08:32
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
//@DiscriminatorValue("GIRO")
@Entity
public class GiroAccount extends Account {

  @Builder
  public GiroAccount(Long accountId, Long accountNumber, Double balance, List<Customer> customerList, List<Transaction> transactionList, Double overdraft, Float debitInterest, Float creditInterest) {
    super(accountId, accountNumber, balance, customerList, transactionList);
    this.overdraft = overdraft;
    this.debitInterest = debitInterest;
    this.creditInterest = creditInterest;
  }

  private Double overdraft;
  private Float debitInterest;
  private Float creditInterest;
}
