package com.johnkuper.epam.daointerface;

import java.util.List;

/**
 * Common DAO interface.
 * 
 * @author Dmitriy Korobeynikov
 *
 */
public interface GenericDAO<Entity, Domain, IdType> {

	/**
	 * Create and persist new entity.
	 * 
	 * @param domain
	 */
	void create(Domain domain);

	/**
	 * Update entity based on the data of domain model.
	 * 
	 * @param domain
	 */
	void update(Domain domain);

	/**
	 * Find all entities.
	 * 
	 * @return Domain objects from all found entities
	 */
	List<Domain> findAll();

	/**
	 * Find all entities with paging.
	 * 
	 * @param limit
	 *            Results on a page.
	 * @return Domain objects from all found entities with limited number of
	 */
	List<Domain> findAll(int limit);

	/**
	 * Find one entity.
	 * 
	 * @param id
	 *            Entity's id in database
	 * @return Domain object from found entity
	 */
	Domain findOne(IdType id);

	/**
	 * Delete entity from database.
	 * 
	 * @param id
	 *            Entity's id
	 */
	void delete(IdType id);

}
