package com.johnkuper.epam.daointerface;

import java.util.List;

import com.johnkuper.epam.domain.CarDomain;
import com.johnkuper.epam.entity.Car;

public interface CarDAO extends GenericDAO<Car, CarDomain, Integer> {

	List<CarDomain> findByName(String name);

	List<CarDomain> findByMotorPower(int minPower, int maxPower);

}
