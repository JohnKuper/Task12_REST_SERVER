package com.johnkuper.epam.mapper;

import ma.glasnost.orika.MapperFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.domain.CarDomain;
import com.johnkuper.epam.domain.CustomerDomain;
import com.johnkuper.epam.domain.MerchantDomain;
import com.johnkuper.epam.domain.SaleDomain;
import com.johnkuper.epam.domain.StoreDomain;
import com.johnkuper.epam.servicemodel.CarWeb;
import com.johnkuper.epam.servicemodel.CustomerWeb;
import com.johnkuper.epam.servicemodel.MerchantWeb;
import com.johnkuper.epam.servicemodel.SaleWeb;
import com.johnkuper.epam.servicemodel.StoreWeb;

public class DomainServiceFieldRegistator implements FieldRegistator {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@Override
	public void registerFields(MapperFactory factory) {

		logger.debug("--- Start fields registration Domain ->> Service ---");

		factory.classMap(CarDomain.class, CarWeb.class)
				.field("car_mark", "car_mark").field("car_model", "car_model")
				.field("motorpower", "motorpower")
				.field("car_color", "car_color").byDefault().register();

		factory.classMap(CustomerDomain.class, CustomerWeb.class)
				.field("custName", "custName")
				.field("custSurname", "custSurname")
				.field("custPatronymic", "custPatronymic")
				.field("passportSeries", "passportSeries")
				.field("passportNumber", "passportNumber")
				.field("dateOfBirth", "dateOfBirth").byDefault().register();

		factory.classMap(MerchantDomain.class, MerchantWeb.class)
				.field("merchName", "merchName")
				.field("merchSurname", "merchSurname")
				.field("merchPatronymic", "merchPatronymic").byDefault()
				.register();

		factory.classMap(SaleDomain.class, SaleWeb.class)
				.field("salePrice", "salePrice")
				.field("dateOfSale", "dateOfSale").byDefault().register();

		factory.classMap(StoreDomain.class, StoreWeb.class)
				.field("amount", "amount").field("carPrice", "carPrice")
				.field("testDrive", "testDrive").byDefault().register();

		logger.debug("--- Fields registration Domain ->> Service complete ---");
	}

}
