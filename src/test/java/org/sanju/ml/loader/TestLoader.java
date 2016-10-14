package org.sanju.ml.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class TestLoader {
	
	private Loader loader;
	
	@Before
	public void setup() throws FileNotFoundException, IOException{
		final Properties properties = new Properties();
		properties.load(new FileInputStream(new File("src/test/resources/ml-server-config.properties")));
		loader = new ModulesLoader(properties);
	}
	
	@Test
	public void shouldLoadAllModulesIntoMLServer() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		loader.load();
	}
	

}
