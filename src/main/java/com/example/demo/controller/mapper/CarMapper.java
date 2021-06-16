package com.example.demo.controller.mapper;

import com.example.demo.controller.dto.CarDto;
import com.example.demo.domain.Car;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface CarMapper extends EntityMapper<CarDto, Car> {

    @Mapping(source = "seatCount", target = "numberOfSeats")
    @Mapping(source = "carId", target = "id", ignore = true)
    Car toEntity(CarDto carDto);

    @Mapping(source = "id", target = "carId")
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto toDto(Car car);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "carId", target = "id", ignore = true)
    @Mapping(source = "seatCount", target = "numberOfSeats")
    void partialUpdate(@MappingTarget Car entity, CarDto dto);

    default Car fromId(Long id) {
        if (id == null) {
            return null;
        }
        Car car = new Car();
        car.setId(id);
        return car;
    }
}
