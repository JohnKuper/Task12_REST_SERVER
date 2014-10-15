package com.johnkuper.epam.jpaservice.mapper;

import ma.glasnost.orika.MapperFactory;

public interface FieldRegistator {
	
	void registerFields(MapperFactory factory);

}
