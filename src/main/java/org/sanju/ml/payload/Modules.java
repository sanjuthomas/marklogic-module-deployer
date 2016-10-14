package org.sanju.ml.payload;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanju Thomas
 *
 */
public class Modules {

	private final List<RestExtensionPayload> restExtensions = new ArrayList<>();
	private final List<TransformPayload> transforms = new ArrayList<>();
	private final List<QueryOptionPayload> queryOptions = new ArrayList<>();
	private final List<LibraryPayload> libraries = new ArrayList<>();

	public List<RestExtensionPayload> getRestExtensions() {
		return new ArrayList<>(this.restExtensions);
	}

	public List<TransformPayload> getTransforms() {
		return new ArrayList<>(this.transforms);
	}

	public List<QueryOptionPayload> getQueryOptions() {
		return new ArrayList<>(this.queryOptions);
	}

	public List<LibraryPayload> getLibraries() {
		return new ArrayList<>(this.libraries);
	}

	public void add(final RestExtensionPayload payload){
		this.restExtensions.add(payload);
	}

	public void add(final TransformPayload payload){
		this.transforms.add(payload);
	}

	public void add(final QueryOptionPayload payload){
		this.queryOptions.add(payload);
	}

	public void add(final LibraryPayload payload){
		this.libraries.add(payload);
	}

}
