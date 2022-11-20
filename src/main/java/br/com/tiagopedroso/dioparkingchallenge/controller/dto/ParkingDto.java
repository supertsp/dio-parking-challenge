package br.com.tiagopedroso.dioparkingchallenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDto {

    private String id;
    private String license;
    private String state;
    private String model;
    private String color;

    @EqualsAndHashCode.Exclude
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime entryDate;

    @EqualsAndHashCode.Exclude
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime exitDate;

    @EqualsAndHashCode.Exclude
    private Double bill;

}
