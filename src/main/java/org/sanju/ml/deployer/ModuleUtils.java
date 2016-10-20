package org.sanju.ml.deployer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * All the utility methods that can be leveraged across different deployer
 * implementations.
 *
 * @author Sanju Thomas
 *
 */
public class ModuleUtils {

	/**
	 * Load all files from a given location.
	 *
	 * @param sourceCodeLocation
	 * @return
	 * @throws IOException
	 */
	public static List<File> loadAssets(final String sourceCodeLocation) throws IOException {

		final List<File> files = new ArrayList<>();
		final File f = new File(sourceCodeLocation);
		if (f.exists() && f.isDirectory()) {
			try (Stream<Path> paths = Files.walk(Paths.get(sourceCodeLocation))) {
				paths.forEach(filePath -> {
					if (Files.isRegularFile(filePath)) {
						files.add(new File(filePath.toUri()));
					}
				});
			}
		}
		return files;
	}
}
