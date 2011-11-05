package org.omapper.test;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;
import org.omapper.mapper.CollatingMapper;
import org.omapper.mapper.SimpleMapper;

/**
 * Test Class for OMapper Framework
 * @author Sachin
 *
 */
public class Main {
	
	public static void main(String[] args) throws UnableToMapException, UnknownPropertyException, UnknownTypeException, IllegalArgumentException, IllegalAccessException {
		
		SimpleMapper<Bean2, Bean1> mapper=new SimpleMapper<Bean2, Bean1>(Bean2.class,Bean1.class);
		Bean1 bean1=new Bean1();
		Bean2 bean2=new Bean2();
		Bean3 bean3=new Bean3();
		
		mapper.mapBean(bean2, bean1);
		
		System.out.println("Bean2="+bean2);
		System.out.println("Bean1="+bean1);
		@SuppressWarnings("unchecked")
		CollatingMapper<Bean2> collatingMapper=new CollatingMapper<Bean2>(Bean2.class,Bean1.class, Bean3.class);
		collatingMapper.mapBean(bean2, bean1,bean3);
		
	}

}
