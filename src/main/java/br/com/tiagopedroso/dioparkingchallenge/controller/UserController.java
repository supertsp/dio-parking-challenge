package br.com.tiagopedroso.dioparkingchallenge.controller;


import br.com.tiagopedroso.dioparkingchallenge.controller.dto.UserDto;
import br.com.tiagopedroso.dioparkingchallenge.model.User;
import br.com.tiagopedroso.dioparkingchallenge.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.tiagopedroso.dioparkingchallenge.config.ApiUrl.BASE_URI;
import static br.com.tiagopedroso.dioparkingchallenge.controller.mapper.UserMapper.toDto;
import static br.com.tiagopedroso.dioparkingchallenge.controller.mapper.UserMapper.toModel;

@RestController
@AllArgsConstructor
@RequestMapping(BASE_URI + "/users")
@Tag(name = "User", description = "Operations to handle the users who will sign in to the API")
public class UserController {

    final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        User model = service.findById(id);

        return ResponseEntity.ok(
                toDto(model)
        );
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        User model = service.findByUsername(username);

        return ResponseEntity.ok(
                toDto(model)
        );
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        final var model = service.create(toModel(dto));
        // TODO: model.setRoles(null);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        toDto(model)
                );
    }

}
