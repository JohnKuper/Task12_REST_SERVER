package com.johnkuper.epam.jpa.daointerface;

import java.math.BigDecimal;
import java.util.List;

import com.johnkuper.epam.jpa.domain.StoreDomain;
import com.johnkuper.epam.jpa.entity.Store;

public interface StoreDAO extends GenericDAO<Store,StoreDomain,Integer> {

	List<StoreDomain> findItemsBetweenPrices(BigDecimal maxprice, BigDecimal minprice);

}
