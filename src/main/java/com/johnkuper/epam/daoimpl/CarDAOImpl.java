package com.johnkuper.epam.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.daointerface.CarDAO;
import com.johnkuper.epam.domain.CarDomain;
import com.johnkuper.epam.entity.Car;
import com.johnkuper.epam.entity.Store;

public class CarDAOImpl extends GenericDAOImpl<Car, CarDomain, Integer>
		implements CarDAO {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@Override
	public List<CarDomain> findByName(String name) {

		logger.debug("--- Start 'findByName' method for Car entity --- ");

		TypedQuery<Car> query = entityManager.createQuery(
				"SELECT c FROM Car c WHERE c.name = :name", Car.class);
		query.setParameter("name", name);
		List<Car> cars = query.getResultList();
		List<CarDomain> carDomains = new ArrayList<CarDomain>();
		if (cars.size() != 0) {
			for (Car car : cars) {
				carDomains.add(mapper.map(car, CarDomain.class));
				logger.debug("Found car with name {}: {}", name, car);
			}
		}
		return carDomains;
	}

	TypedQuery<Store> query = entityManager
			.createQuery(
					"SELECT s FROM Store s WHERE s.price BETWEEN :minprice AND :maxprice",
					Store.class);

	@Override
	public List<CarDomain> findByMotorPower(int minPower, int maxPower) {

		logger.debug("--- Start 'findByMotorPower' method for Car entity --- ");

		TypedQuery<Car> query = entityManager
				.createQuery(
						"SELECT c FROM Car c WHERE c.modification BETWEEN :minPower AND :maxPower",
						Car.class);
		query.setParameter("minPower", minPower);
		query.setParameter("maxPower", maxPower);
		List<Car> cars = query.getResultList();
		List<CarDomain> carDomains = new ArrayList<>();
		if (cars.size() != 0) {
			for (Car car : cars) {
				carDomains.add(mapper.map(car, CarDomain.class));
				logger.debug("Found car between {} and {} motor power: {}",
						minPower, maxPower, car);
			}
		}

		return carDomains;
	}

}
