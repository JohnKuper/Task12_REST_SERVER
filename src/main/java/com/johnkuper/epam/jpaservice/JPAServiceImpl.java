package com.johnkuper.epam.jpaservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.jpa.daoimpl.CarDAOImpl;
import com.johnkuper.epam.jpa.daoimpl.CustomerDAOImpl;
import com.johnkuper.epam.jpa.daoimpl.MerchantDAOImpl;
import com.johnkuper.epam.jpa.daoimpl.SaleDAOImpl;
import com.johnkuper.epam.jpa.daoimpl.StoreDAOImpl;
import com.johnkuper.epam.jpa.domain.CarDomain;
import com.johnkuper.epam.jpa.domain.CustomerDomain;
import com.johnkuper.epam.jpa.domain.MerchantDomain;
import com.johnkuper.epam.jpa.domain.SaleDomain;
import com.johnkuper.epam.jpa.domain.StoreDomain;
import com.johnkuper.epam.jpaservice.mapper.DomainServiceFieldRegistator;
import com.johnkuper.epam.jpaservice.mapper.OrikaMapper;
import com.johnkuper.epam.jpaservice.model.CarWeb;
import com.johnkuper.epam.jpaservice.model.CustomerWeb;
import com.johnkuper.epam.jpaservice.model.MerchantWeb;
import com.johnkuper.epam.jpaservice.model.SaleWeb;
import com.johnkuper.epam.jpaservice.model.StoreWeb;

public class JPAServiceImpl implements JPAService {

	private OrikaMapper mapper;
	private CarDAOImpl carDaoImpl;
	private CustomerDAOImpl custDaoImpl;
	private MerchantDAOImpl merchDaoImpl;
	private SaleDAOImpl saleDaoImpl;
	private StoreDAOImpl storeDaoImpl;
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	public JPAServiceImpl() {
		this.mapper = new OrikaMapper(new DomainServiceFieldRegistator());
		this.carDaoImpl = new CarDAOImpl();
		this.custDaoImpl = new CustomerDAOImpl();
		this.merchDaoImpl = new MerchantDAOImpl();
		this.saleDaoImpl = new SaleDAOImpl();
		this.storeDaoImpl = new StoreDAOImpl();
	}

	// Private methods for inside service work
	private String calledMethod(String methodName) {
		return String.format("Service method '%s' was called", methodName);
	}

	private void checkId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("ID can't be less than 1.");
		}
	}

	private java.sql.Date getCurrentDate() {

		Calendar calendar = Calendar.getInstance();

		java.util.Date now = calendar.getTime();
		java.sql.Date currentDate = new java.sql.Date(now.getTime());

		return currentDate;
	}

	private List<CarWeb> getWebCarsFromDomainCars(List<CarDomain> domainCars) {
		List<CarWeb> webCars = new ArrayList<>();
		if (domainCars.size() != 0) {
			for (CarDomain car : domainCars) {
				webCars.add(mapper.map(car, CarWeb.class));
			}
		}
		return webCars;
	}

	private List<StoreWeb> getWebStoresFromDomainStores(
			List<StoreDomain> domainStores) {
		List<StoreWeb> webStores = new ArrayList<>();
		if (domainStores.size() != 0) {
			for (StoreDomain store : domainStores) {
				webStores.add(mapper.map(store, StoreWeb.class));
			}
		}
		return webStores;
	}

	// Car methods
	@Override
	public List<CarWeb> findCarByName(String name) {

		if (!(name instanceof String)) {
			throw new IllegalArgumentException(
					"Car name should be instance of String.");
		}

		logger.debug(calledMethod("findCarByName"));
		List<CarDomain> carDomains = carDaoImpl.findByName(name);
		List<CarWeb> carWebs = getWebCarsFromDomainCars(carDomains);

		return carWebs;
	}

	@Override
	public CarWeb findCar(int id) {

		logger.debug(calledMethod("findCar"));
		checkId(id);
		CarDomain carDomain = carDaoImpl.findOne(id);
		CarWeb carWeb = mapper.map(carDomain, CarWeb.class);
		logger.debug("CarWeb: {}", carWeb);

		return carWeb;
	}

	@Override
	public String createCar(CarWeb carWeb) {

		logger.debug(calledMethod("createCar"));
		CarDomain carDomain = mapper.map(carWeb, CarDomain.class);
		carDaoImpl.create(carDomain);

		return "New car was successfully create";
	}

	@Override
	public String updateCar(CarWeb carWeb) {

		logger.debug(calledMethod("updateCar"));
		CarDomain carDomain = mapper.map(carWeb, CarDomain.class);
		carDaoImpl.update(carDomain);

		return String.format(
				"Information about car with ID = %s was successfully updated",
				carDomain.getId());
	}

	@Override
	public List<CarWeb> findCarsByMotorPower(int minPower, int maxPower) {

		if (minPower < 0 || maxPower < 0) {
			throw new IllegalArgumentException(
					"Motor power can't be less than 0.");
		}
		if (minPower > maxPower) {
			throw new IllegalArgumentException(
					"Min power can't be more than max power.");
		}

		logger.debug(calledMethod("findCarsByMotorPower"));
		List<CarDomain> domainCars = carDaoImpl.findByMotorPower(minPower,
				maxPower);
		List<CarWeb> webCars = getWebCarsFromDomainCars(domainCars);
		return webCars;
	}

	@Override
	public List<StoreWeb> getAllCarsFromStore() {

		logger.debug(calledMethod("getAllCarsFromStore"));

		List<StoreDomain> domainStores = storeDaoImpl.findAll();
		List<StoreWeb> webStores = getWebStoresFromDomainStores(domainStores);

		return webStores;
	}

	// Customer methods
	@Override
	public String createCustomer(CustomerWeb customer) {

		logger.debug(calledMethod("createCustomer"));
		CustomerDomain custDomain = mapper.map(customer, CustomerDomain.class);
		custDaoImpl.create(custDomain);

		return "New customer was successfully create";

	}

	@Override
	public CustomerWeb findCustomer(int id) {

		logger.debug(calledMethod("findCustomer"));
		checkId(id);
		CustomerDomain custDomain = custDaoImpl.findOne(id);
		CustomerWeb custWeb = mapper.map(custDomain, CustomerWeb.class);
		logger.debug("CustomerWeb: {}", custWeb);

		return custWeb;
	}

	// Merchant methods
	@Override
	public MerchantWeb findMerchant(int id) {

		logger.debug(calledMethod("findMerchant"));
		checkId(id);
		MerchantDomain merchDomain = merchDaoImpl.findOne(id);
		MerchantWeb merchWeb = mapper.map(merchDomain, MerchantWeb.class);
		logger.debug("MerchantWeb: {}", merchWeb);

		return merchWeb;

	}

	@Override
	public String createMerchant(MerchantWeb merchant) {

		logger.debug(calledMethod("createMerchant"));
		MerchantDomain merchDomain = mapper.map(merchant, MerchantDomain.class);
		merchDaoImpl.create(merchDomain);

		return "New merchant was successfully create";
	}

	// Sale methods
	@Override
	public String buyCar(CarWeb car, CustomerWeb customer,
			MerchantWeb merchant, BigDecimal price) {

		logger.debug(calledMethod("buyCar"));

		java.sql.Date currentDate = getCurrentDate();

		SaleWeb saleWeb = new SaleWeb(car, customer, merchant, price,
				currentDate);
		SaleDomain saleDomain = mapper.map(saleWeb, SaleDomain.class);
		saleDaoImpl.create(saleDomain);

		String status = "New sale was created";

		return status;

	}

	// Store methods
	@Override
	public List<StoreWeb> findCarsBetweenPrices(BigDecimal minPrice,
			BigDecimal maxPrice) {

		if (minPrice.compareTo(BigDecimal.ZERO) < 0
				|| maxPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Price can't be less than 0.");
		}
		if (maxPrice.compareTo(minPrice) < 0) {
			throw new IllegalArgumentException(
					"Min price can't be more than max price.");
		}

		logger.debug(calledMethod("findCarsBetweenPrices"));
		List<StoreDomain> domainStorses = storeDaoImpl.findItemsBetweenPrices(
				minPrice, maxPrice);
		List<StoreWeb> webStores = getWebStoresFromDomainStores(domainStorses);
		return webStores;

	}

	@Override
	public StoreWeb findStoreByCarID(int id) {

		logger.debug(calledMethod("findStoreByCarID"));
		checkId(id);
		StoreDomain storeDomain = storeDaoImpl.findStoreByCarID(id);
		StoreWeb storeWeb = mapper.map(storeDomain, StoreWeb.class);
		logger.debug("StoreWeb: {}", storeWeb);

		return storeWeb;
	}

	@Override
	public String updateStore(StoreWeb store) {

		logger.debug(calledMethod("updateStore"));
		StoreDomain storeDomain = mapper.map(store, StoreDomain.class);
		storeDaoImpl.update(storeDomain);

		return String
				.format("Information about store with ID = %s was successfully updated",
						storeDomain.getId());
	}

}
