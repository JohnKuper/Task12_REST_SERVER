package com.johnkuper.epam.jpa.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.johnkuper.epam.jpa.daointerface.StoreDAO;
import com.johnkuper.epam.jpa.domain.StoreDomain;
import com.johnkuper.epam.jpa.entity.Store;

public class StoreDAOImpl extends GenericDAOImpl<Store, StoreDomain, Integer>
		implements StoreDAO {

	@Override
	public List<StoreDomain> findItemsBetweenPrices(BigDecimal minprice,
			BigDecimal maxprice) {

		logger.debug("--- Start 'findItemsBetweenPrices' method for Store entity --- ");

		TypedQuery<Store> query = entityManager
				.createQuery(
						"SELECT s FROM Store s WHERE s.price BETWEEN :minprice AND :maxprice",
						Store.class);
		query.setParameter("minprice", minprice);
		query.setParameter("maxprice", maxprice);
		List<Store> items = query.getResultList();
		List<StoreDomain> domainItems = new ArrayList<StoreDomain>();
		logger.debug("--- {} Items between {} and {} prices was found ---",
				items.size(), minprice, maxprice);
		if (items.size() > 0) {
			for (Store item : items) {
				domainItems.add(mapper.map(item, StoreDomain.class));
				logger.debug("{}", item);
			}
		}
		return domainItems;
	}

	@Override
	public StoreDomain findStoreByCarID(int id) {

		logger.debug(
				"--- Start 'findStoreByCarID' method for Store entity with ID = {} --- ",
				id);
		TypedQuery<Store> query = entityManager.createQuery(
				"SELECT s FROM Store s WHERE s.car.id = :carID", Store.class);
		query.setParameter("carID", id);
		Store store = query.getSingleResult();
		if (store != null) {
			StoreDomain storeDomain = mapper.map(store, StoreDomain.class);
			logger.debug("{}", storeDomain);
			return storeDomain;
		}

		return null;

	}

}
