package at.kaindorf.jpintro.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 12.09.2024
 * Time: 08:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@IdClass(AccountPK.class)
public class Account {

//  @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myAccoutSeq")
//  @SequenceGenerator(name = "myAccoutSeq", sequenceName = "my_account_seq", initialValue = 500)
//  private Long accountId;

//  @Id
//  @Column(length = 30)
//  private String iban;
//  @Id
//  private Long id;

  @EmbeddedId
  private AccountPK accountPK;

  @Column(precision = 2)
  private Double balance;
}
