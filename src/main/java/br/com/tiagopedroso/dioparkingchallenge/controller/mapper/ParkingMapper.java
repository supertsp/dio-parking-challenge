package br.com.tiagopedroso.dioparkingchallenge.controller.mapper;

import br.com.tiagopedroso.dioparkingchallenge.controller.dto.ParkingCreateDto;
import br.com.tiagopedroso.dioparkingchallenge.controller.dto.ParkingDto;
import br.com.tiagopedroso.dioparkingchallenge.model.Parking;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingMapper {

    private ParkingMapper() {}

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static ParkingDto toDto(Parking model) {
        return MODEL_MAPPER.map(model, ParkingDto.class);
    }

    public static List<ParkingDto> toDtoList(List<Parking> modelList) {
        return modelList.stream()
                .map(ParkingMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Parking toModel(ParkingDto dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }

    public static Parking toModel(ParkingCreateDto dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }
}
