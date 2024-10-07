package at.kaindorf.jpintro.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 12.09.2024
 * Time: 08:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // only one table in DB
//@Inheritance(strategy = InheritanceType.JOINED)         // for each class a table, data are spread over tables
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // for each concrete class a table that contains all data
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//@IdClass(AccountPK.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Account {

//  @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myAccoutSeq")
//  @SequenceGenerator(name = "myAccoutSeq", sequenceName = "my_account_seq", initialValue = 500)
//  private Long accountId;

//  @Id
//  @Column(length = 30)
//  private String iban;
//  @Id
//  private Long id;
//  @EmbeddedId
//  private AccountPK accountPK;

  @Id
  @GeneratedValue
  @JsonIgnore
  private Long accountId;
  @Column(nullable = false)
  @EqualsAndHashCode.Include
  private Long accountNumber;
  @Column(precision = 2)
  private Double balance;

  @ToString.Exclude
  @ManyToMany(mappedBy = "accounts")
  private List<Customer> customerList = new ArrayList<>();

  @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
  private List<Transaction> transactionList = new ArrayList<>();

  public void addCustomer(Customer customer) {
    if (!customerList.contains(customer)) {
      customerList.add(customer);
    }
  }

  public void addTransaction(Transaction transaction) {
    transactionList.add(transaction);
    transaction.setAccount(this);
  }


}
