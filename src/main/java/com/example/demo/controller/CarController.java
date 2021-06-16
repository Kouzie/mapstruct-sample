package com.example.demo.controller;

import com.example.demo.controller.dto.CarDto;
import com.example.demo.controller.mapper.CarMapper;
import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @PostMapping
    CarDto setCar(@RequestBody CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        carRepository.save(car);
        return carMapper.toDto(car);
    }

    @PatchMapping("{id}")
    CarDto patchCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        Car car = carRepository.findById(id).get();
        carMapper.partialUpdate(car, carDto);
        car = carRepository.save(car);
        return carMapper.toDto(car);
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable Long id) {
        Car car = carRepository.findById(id).get();
        return carMapper.toDto(car);
    }
}
