package com.johnkuper.epam.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mapper {

	private MapperFacade orikaFacade;
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	public Mapper(FieldRegistator registator) {

		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		registator.registerFields(mapperFactory);
		orikaFacade = mapperFactory.getMapperFacade();
	}

	public <S, D> D map(S source, Class<D> destinationType) {
		return orikaFacade.map(source, destinationType);
	}

	public <S, D> void map(S source, D destination) {
		orikaFacade.map(source, destination);
	}
}
