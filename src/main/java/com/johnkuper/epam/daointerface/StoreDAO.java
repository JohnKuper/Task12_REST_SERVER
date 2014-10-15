package com.johnkuper.epam.daointerface;

import java.math.BigDecimal;
import java.util.List;

import com.johnkuper.epam.domain.StoreDomain;
import com.johnkuper.epam.entity.Store;

public interface StoreDAO extends GenericDAO<Store,StoreDomain,Integer> {

	List<StoreDomain> findItemsBetweenPrices(BigDecimal maxprice, BigDecimal minprice);

}
