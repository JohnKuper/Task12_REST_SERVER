package com.johnkuper.epam.daointerface;

import com.johnkuper.epam.domain.CustomerDomain;
import com.johnkuper.epam.entity.Customer;

public interface CustomerDAO extends
		GenericDAO<Customer, CustomerDomain, Integer> {


}
