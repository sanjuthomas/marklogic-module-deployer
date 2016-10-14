package org.sanju.ml.loader;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Sanju Thomas
 *
 */
public interface Loader {

	void load() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException;
}
