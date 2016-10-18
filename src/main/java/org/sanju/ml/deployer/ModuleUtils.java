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
 *
 * @author Sanju Thomas
 *
 */
public class ModuleUtils {

	public static List<File> loadAssets(final String sourceCodeLocation) throws IOException{
		final List<File> files = new ArrayList<>();
		try(Stream<Path> paths = Files.walk(Paths.get(sourceCodeLocation))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					files.add(new File(filePath.toUri()));
				}
			});
		}
		return files;
	}
}
