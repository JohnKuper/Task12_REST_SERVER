package com.johnkuper.epam.jpa.daointerface;

import java.util.List;

import com.johnkuper.epam.jpa.domain.CarDomain;
import com.johnkuper.epam.jpa.entity.Car;

public interface CarDAO extends GenericDAO<Car, CarDomain, Integer> {

	List<CarDomain> findByName(String name);

	List<CarDomain> findByMotorPower(int minPower, int maxPower);

}
