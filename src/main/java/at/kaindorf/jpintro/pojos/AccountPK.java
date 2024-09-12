package at.kaindorf.jpintro.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 12.09.2024
 * Time: 08:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AccountPK implements Serializable {
  @Column(length = 30)
  private String iban;
  private Long id;
}
