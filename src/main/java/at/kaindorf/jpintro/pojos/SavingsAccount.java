package at.kaindorf.jpintro.pojos;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 19.09.2024
 * Time: 08:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
//@DiscriminatorValue("SAVINGS")
@Entity
public class SavingsAccount extends Account {
  private Float interest;
}
