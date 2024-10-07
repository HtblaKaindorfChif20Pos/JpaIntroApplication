package at.kaindorf.jpintro.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 26.09.2024
 * Time: 08:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
  @Id
  @GeneratedValue
  private Long transactionId;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false)
  private LocalDateTime timestamp;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;
}
