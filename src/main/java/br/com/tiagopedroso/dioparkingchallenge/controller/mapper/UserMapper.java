package br.com.tiagopedroso.dioparkingchallenge.controller.mapper;

import br.com.tiagopedroso.dioparkingchallenge.controller.dto.UserDto;
import br.com.tiagopedroso.dioparkingchallenge.model.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    private UserMapper() {}

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static UserDto toDto(User model) {
        return MODEL_MAPPER.map(model, UserDto.class);
    }

    public static User toModel(UserDto dto) {
        return MODEL_MAPPER.map(dto, User.class);
    }

}
