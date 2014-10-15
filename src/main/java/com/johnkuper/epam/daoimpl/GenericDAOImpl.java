package com.johnkuper.epam.daoimpl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.daointerface.GenericDAO;
import com.johnkuper.epam.mapper.EntityDomainFieldRegistator;
import com.johnkuper.epam.mapper.Mapper;

public class GenericDAOImpl<Entity, Domain, IdType> implements
		GenericDAO<Entity, Domain, IdType> {

	protected EntityManager entityManager;

	protected Class<Entity> entityType;
	protected Class<Domain> domainType;
	protected static Mapper mapper;
	private String entityName;

	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.entityType = (Class<Entity>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		getEntityManager();
		this.domainType = (Class<Domain>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		getEntityManager();
		if (mapper == null) {
			mapper = new Mapper(new EntityDomainFieldRegistator());
		}
		entityName = entityType.getSimpleName();

	}

	private void getEntityManager() {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("AutoShowDataBase");
		entityManager = emf.createEntityManager();
	}

	@Override
	public void create(Domain domain) {
		logger.debug("--- Start 'create' method for {} entity ---", entityName);
		logger.debug("Domain for create: {}", domain);
		Entity entity = mapper.map(domain, entityType);
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		logger.debug("New entity: {} ", entity);

	}

	@Override
	public void update(Domain domain) {
		logger.debug("--- Start 'update' method for {} entity ---",
				entityType.getSimpleName());
		entityManager.getTransaction().begin();
		Entity entity = mapper.map(domain, entityType);
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		logger.debug("New entity is: {}", entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Domain> findAll() {
		logger.debug("--- Start 'findAll' method for {} entity ---",
				entityType.getSimpleName());
		Query query = entityManager.createQuery("SELECT entity FROM "
				+ entityType.getName() + " entity");
		List<Entity> entities = query.getResultList();
		List<Domain> domains = new ArrayList<Domain>();
		if (entities.size() != 0) {
			for (Entity entity : entities) {
				domains.add(mapper.map(entity, domainType));
				logger.debug("Found: {}", entity);
			}
		}
		return domains;
	}

	@Override
	public Domain findOne(IdType id) {
		logger.debug(
				"--- Start 'findOne' method for {} entity with ID = {} ---",
				entityType.getSimpleName(), id);
		Entity entity = entityManager.find(entityType, id);
		Domain domain = mapper.map(entity, domainType);
		logger.debug("Found entity: {}", entity);
		logger.debug("After mapping: {}", domain);
		return domain;
	}

	@Override
	public void delete(IdType id) {
		logger.debug(
				"--- Start 'delete' method for {} entity with ID = {} ---",
				entityType.getSimpleName(), id);
		Entity entity = entityManager.find(entityType, id);
		if (entity != null) {
			logger.debug("Entity for delete: {}", entity);
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
		} else {
			logger.error("Can't find entity with ID = {}", id);
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Domain> findAll(int limit) {
		logger.debug(
				"--- Start 'findAll' method with limit = {} for {} entity ---",
				limit, entityType.getSimpleName());
		Query query = entityManager.createQuery(
				"SELECT entity FROM " + entityType.getName() + " entity")
				.setMaxResults(limit);
		List<Entity> entities = query.getResultList();
		List<Domain> domains = new ArrayList<Domain>();
		if (entities.size() != 0) {
			for (Entity entity : entities) {
				domains.add(mapper.map(entity, domainType));
				logger.debug("Found: {}", entity);
			}
		}
		return domains;
	}

}
