package org.openlca.io.ecospold2;

import java.util.HashMap;
import java.util.Map;

import org.openlca.core.model.Category;
import org.openlca.core.model.FlowProperty;
import org.openlca.core.model.Location;
import org.openlca.core.model.Unit;

/**
 * An index with cached reference data for an EcoSpold 02 import.
 */
class RefDataIndex {

	private Map<String, Category> processCategories = new HashMap<>();
	private Map<String, Category> compartments = new HashMap<>();
	private Map<String, Location> locations = new HashMap<>();
	private Map<String, Unit> units = new HashMap<>();
	private Map<String, FlowProperty> flowProperties = new HashMap<>();

	public Category getProcessCategory(String key) {
		return processCategories.get(key);
	}

	public void putProcessCategory(String key, Category category) {
		processCategories.put(key, category);
	}

	public Location getLocation(String key) {
		return locations.get(key);
	}

	public void putLocation(String key, Location location) {
		locations.put(key, location);
	}

	public Unit getUnit(String key) {
		return units.get(key);
	}

	public void putUnit(String key, Unit unit) {
		units.put(key, unit);
	}

	public FlowProperty getFlowProperty(String key) {
		return flowProperties.get(key);
	}

	public void putFlowProperty(String key, FlowProperty property) {
		flowProperties.put(key, property);
	}

	public Category getCompartment(String key) {
		return compartments.get(key);
	}

	public void putCompartment(String key, Category category) {
		compartments.put(key, category);
	}

}
