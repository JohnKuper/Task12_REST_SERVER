package com.johnkuper.epam.service;

import java.math.BigDecimal;
import java.util.List;

import com.johnkuper.epam.servicemodel.CarWeb;
import com.johnkuper.epam.servicemodel.CustomerWeb;
import com.johnkuper.epam.servicemodel.MerchantWeb;
import com.johnkuper.epam.servicemodel.StoreWeb;

public interface JPAService {

	// Car methods
	public List<CarWeb> findCarByName(String name);

	public CarWeb findCar(int id);

	public String createCar(CarWeb carWeb);

	public String updateCar(CarWeb carWeb);

	public List<CarWeb> findCarsByMotorPower(int minPower, int maxPower);

	public List<StoreWeb> getAllCarsFromStore();

	// Customer methods
	public String createCustomer(CustomerWeb customer);

	public CustomerWeb findCustomer(int id);

	// Merchant methods
	public MerchantWeb findMerchant(int id);

	public String createMerchant(MerchantWeb merchant);

	// Sales method
	public String buyCar(CarWeb car, CustomerWeb customer,
			MerchantWeb merchant, BigDecimal price);

	// Store method
	public List<StoreWeb> findCarsBetweenPrices(BigDecimal minPrice,
			BigDecimal maxPrice);

}
