package at.kaindorf.jpintro.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 26.09.2024
 * Time: 08:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
  @Id
  @GeneratedValue
  @JsonIgnore
  private Long addressId;

  @Column(length = 100)
  private String streetname;
  @Column(length = 10)
  private String streetNumber;

  private Integer zipCode;

  @Column(length = 100)
  private String city;

  @OneToOne(mappedBy = "address")
  @ToString.Exclude
  @JsonBackReference
  private Customer customer;
}
