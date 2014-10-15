package com.johnkuper.epam.jpaservice.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.jpa.domain.CarDomain;
import com.johnkuper.epam.jpa.domain.CustomerDomain;
import com.johnkuper.epam.jpa.domain.MerchantDomain;
import com.johnkuper.epam.jpa.domain.SaleDomain;
import com.johnkuper.epam.jpa.domain.StoreDomain;
import com.johnkuper.epam.jpa.entity.Car;
import com.johnkuper.epam.jpa.entity.Customer;
import com.johnkuper.epam.jpa.entity.Merchant;
import com.johnkuper.epam.jpa.entity.Sale;
import com.johnkuper.epam.jpa.entity.Store;

import ma.glasnost.orika.MapperFactory;

public class EntityDomainFieldRegistator implements FieldRegistator {

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@Override
	public void registerFields(MapperFactory factory) {

		logger.debug("--- Start fields registration Entity ->> Domain ---");

		factory.classMap(Car.class, CarDomain.class).field("name", "car_mark")
				.field("model", "car_model")
				.field("modification", "motorpower")
				.field("color", "car_color").byDefault().register();

		factory.classMap(Customer.class, CustomerDomain.class)
				.field("name", "custName").field("surname", "custSurname")
				.field("patronymic", "custPatronymic")
				.field("passport_series", "passportSeries")
				.field("passport_number", "passportNumber")
				.field("birthdate", "dateOfBirth").byDefault().register();

		factory.classMap(Merchant.class, MerchantDomain.class)
				.field("name", "merchName").field("surname", "merchSurname")
				.field("patronymic", "merchPatronymic").byDefault().register();

		factory.classMap(Sale.class, SaleDomain.class)
				.field("price", "salePrice").field("saleDate", "dateOfSale")
				.byDefault().register();

		factory.classMap(Store.class, StoreDomain.class)
				.field("count", "amount").field("price", "carPrice")
				.field("testdrive_avaible", "testDrive").byDefault().register();

		logger.debug("--- Fields registration Entity ->> Domain complete ---");

	}

}
