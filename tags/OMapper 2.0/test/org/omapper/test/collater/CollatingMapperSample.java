package org.omapper.test.collater;

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
		System.out.println("Collating Mapper Dempo.....Starts");
		Bean1 bean1=getBean1();
		Bean2 bean2=getBean2();
		Bean3 bean3=new Bean3();
		/* Initializing Collating mapper */
		@SuppressWarnings("unchecked")
		CollatingMapper<Bean3> collatingMapper = new CollatingMapper<Bean3>(
				Bean3.class, Bean1.class, Bean2.class);

		collatingMapper.mapBean(bean3, bean1, bean2);
		System.out.println("Bean1="+ bean1);
		System.out.println("Bean2=" + bean2);
		System.out.println("Bean3="+bean3);
		System.out.println("Collating Mapper Dempo.....Ends");

		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - end"); //$NON-NLS-1$
		}
	}

	private static Bean2 getBean2() {
		// TODO Auto-generated method stub
		Bean2 bean2 = new Bean2();
		bean2.setI(25);
		bean2.setX("sachin2");
		Child1 child1=new Child1();
		child1.setAge(10);
		child1.setName("Junior Sachin2");
		Child1 child2=new Child1();
		child2.setAge(11);
		child2.setName("Junior Vipin2");
		List<Child1> child1List=new ArrayList<Child1>();
		child1List.add(child1);
		child1List.add(child2);
		bean2.setChild(child1);
		bean2.setChildList(child1List);
		bean2.setChildArray(new Child1[]{child1,child2});
		bean2.setIntArray(new int[]{1,2,3,2});
		bean2.setCharArray(new char[]{'s','a','c','h','2'});
		bean2.setStringArray(new String[]{"sachin","vipin","guptas","2"});
	return bean2;	}

	private static Bean1 getBean1() {
		// TODO Auto-generated method stub
		Bean1 bean1 = new Bean1();
		bean1.setI(25);
		bean1.setX("sachin1");
		Child1 child1=new Child1();
		child1.setAge(10);
		child1.setName("Junior Sachin1");
		Child1 child2=new Child1();
		child2.setAge(11);
		child2.setName("Junior Vipin1");
		List<Child1> child1List=new ArrayList<Child1>();
		child1List.add(child1);
		child1List.add(child2);
		bean1.setChild(child1);
		bean1.setChildList(child1List);
		bean1.setChildArray(new Child1[]{child1,child2});
		bean1.setIntArray(new int[]{1,2,3,1});
		bean1.setCharArray(new char[]{'s','a','c','h','1'});
		bean1.setStringArray(new String[]{"sachin","vipin","guptas","1"});
	return bean1;	
	}

}
