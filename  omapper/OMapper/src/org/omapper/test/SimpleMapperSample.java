package org.omapper.test;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;
import org.omapper.mapper.SimpleMapper;


/**
 * Test Class for OMapper Framework.
 *
 * @author Sachin
 */
public class SimpleMapperSample {
	
	/** The logger. */
	static Logger logger = Logger.getLogger(SimpleMapperSample.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnableToMapException the unable to map exception
	 * @throws UnknownPropertyException the unknown property exception
	 * @throws UnknownTypeException the unknown type exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public static void main(String[] args) throws UnableToMapException, UnknownPropertyException, UnknownTypeException, IllegalArgumentException, IllegalAccessException {
		
		DOMConfigurator.configure("config/log4j.xml");
		logger.debug("Sample program starts");
		
		logger.debug("Simple Mapper Dempo.....Starts");
		/*Sample for Simple Mapper*/
		SimpleMapper<Bean2, Bean1> mapper=new SimpleMapper<Bean2, Bean1>(Bean2.class,Bean1.class);
		Bean1 bean1=new Bean1();
		bean1.setAddress("My address");
		bean1.setAge(26);
		bean1.setEmp_id(7922510);
		bean1.setName("Sachin");
		
		logger.debug("Bean1="+bean1);
		Bean2 bean2=new Bean2();
		
		
		mapper.mapBean(bean2, bean1);
		
		logger.debug("Bean2="+bean2);
		
		logger.debug("Simple Mapper Dempo.....Ends");
		
		
	}

}
