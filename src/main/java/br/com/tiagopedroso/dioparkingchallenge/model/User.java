package br.com.tiagopedroso.dioparkingchallenge.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    Integer id;

    @Column(length = 50, nullable = false)
    String name;

    @Column(length = 20, nullable = false)
    String username;

    @Column(length = 100, nullable = false)
    String password;

    @EqualsAndHashCode.Exclude
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    List<String> roles = new ArrayList<>();

}