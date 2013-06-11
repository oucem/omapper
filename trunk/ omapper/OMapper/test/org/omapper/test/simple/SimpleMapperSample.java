/*
 * 
 */
package org.omapper.test.simple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	 * @param args
	 *            the arguments
	 * @throws UnableToMapException
	 *             the unable to map exception
	 * @throws UnknownPropertyException
	 *             the unknown property exception
	 * @throws UnknownTypeException
	 *             the unknown type exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	public static void main(String[] args) throws UnableToMapException,
			UnknownPropertyException, UnknownTypeException,
			IllegalArgumentException, IllegalAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - start"); //$NON-NLS-1$
		}

		DOMConfigurator.configure("config/log4j.xml");
		logger.debug("Sample program starts");

		logger.debug("Simple Mapper Dempo.....Starts");
		
		/* Sample for Simple Mapper */
		SimpleMapper<Bean2, Bean1> mapper = new SimpleMapper<Bean2, Bean1>(
				Bean2.class, Bean1.class);
		Bean1 bean1 = new Bean1();
		bean1.setI(25);
		bean1.setX("sachin");
		Child1 child1=new Child1();
		child1.setAge(10);
		child1.setName("Junior Sachin");
		Child1 child2=new Child1();
		child2.setAge(11);
		child2.setName("Junior Vipin");
		List<Child1> child1List=new ArrayList<Child1>();
		child1List.add(child1);
		child1List.add(child2);
		bean1.setChild(child1);
		bean1.setChildList(child1List);
		bean1.setChildArray(new Child1[]{child1});
		
		logger.debug("Bean1=" + bean1);
		Bean2 bean2 = new Bean2();

		mapper.mapBean(bean2, bean1);
		logger.debug("Bean1=" + bean1);
		logger.debug("Bean2=" + bean2);

		logger.debug("Simple Mapper Dempo.....Ends");

	}

}
