package com.johnkuper.epam.jpa.daointerface;

import com.johnkuper.epam.jpa.domain.CustomerDomain;
import com.johnkuper.epam.jpa.entity.Customer;

public interface CustomerDAO extends
		GenericDAO<Customer, CustomerDomain, Integer> {


}
