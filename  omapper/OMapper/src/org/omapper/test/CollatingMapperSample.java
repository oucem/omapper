package org.omapper.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;
import org.omapper.mapper.CollatingMapper;


/**
 * Test Class for OMapper Framework.
 *
 * @author Sachin
 */
public class CollatingMapperSample {
	
	/** The logger. */
	static Logger logger = Logger.getLogger(CollatingMapperSample.class);
	
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
		System.out.println("Collating Mapper Dempo.....Starts");
		Bean1 bean1=new Bean1();
		bean1.setAddress("My address");
		bean1.setAge(26);
		bean1.setEmp_id(7922510);
		bean1.setName("Sachin");
		bean1.setChild1(new Child1("sachin",26));
		
		logger.debug("Source Bean1="+bean1);
		
		Bean3 bean3=new Bean3();
		bean3.setCompanyName("RBS");
		List<String> positionsList=new ArrayList<String>();
		positionsList.add("Software Engineer");
		positionsList.add("Senior Software Engineer");
		positionsList.add("Software Designer");
		bean3.setPositionsList(positionsList);
		
		System.out.println("Source Bean 3="+bean3);
		
		/*Target Bean*/
		Bean2 bean2=new Bean2();
		
		/*Initializing Collating mapper */
		@SuppressWarnings("unchecked")
		CollatingMapper<Bean2> collatingMapper=new CollatingMapper<Bean2>(Bean2.class,Bean1.class, Bean3.class);
		
		collatingMapper.mapBean(bean2, bean1,bean3);
		
		System.out.println("Bean2="+bean2);
		
		
		System.out.println("Collating Mapper Dempo.....Ends");
		
		
	}

}
