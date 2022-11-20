package br.com.tiagopedroso.dioparkingchallenge.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parking {

    @Id
    String id;

    String license;

    String state;

    String model;

    String color;

    @EqualsAndHashCode.Exclude
    LocalDateTime entryDate;

    @EqualsAndHashCode.Exclude
    LocalDateTime exitDate;

    @EqualsAndHashCode.Exclude
    Double bill;

    public Parking(String id, String license, String state, String model, String color) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }

}
