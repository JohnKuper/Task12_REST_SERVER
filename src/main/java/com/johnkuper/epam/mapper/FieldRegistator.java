package com.johnkuper.epam.mapper;

import ma.glasnost.orika.MapperFactory;

public interface FieldRegistator {
	
	void registerFields(MapperFactory factory);

}
