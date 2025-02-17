package br.com.tiagopedroso.dioparkingchallenge.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {

    private String username;
    private String token;

}
