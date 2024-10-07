package at.kaindorf.jpintro.pojos;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: jpintro
 * Created by: SF
 * Date: 16.09.2024
 * Time: 11:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQueries({
    @NamedQuery(name = "Customer.getCustomerByLastname", query = "Select c FROM Customer c WHERE c.lastname LIKE ?1")
})
public class Customer {
  @Id
  @GeneratedValue
  @JsonIgnore
  private Long customerId;
  @Column(length = 100, nullable = false)
  private String firstname;
  @Column(length = 100, nullable = false)
  private String lastname;
  private Long customerNumber;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "address_id")
  @JsonManagedReference
  private Address address;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(joinColumns = { @JoinColumn(name = "customer_id")},
      inverseJoinColumns = { @JoinColumn(name = "account_id")})
  @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
  @JsonSubTypes({
      @JsonSubTypes.Type(GiroAccount.class),
      @JsonSubTypes.Type(SavingsAccount.class)
  })
  private List<Account> accounts = new ArrayList<>();
}
