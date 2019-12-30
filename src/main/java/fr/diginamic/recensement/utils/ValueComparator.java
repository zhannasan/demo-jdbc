package fr.diginamic.recensement.utils;

import java.util.Comparator;
import java.util.HashMap;

public class ValueComparator <K,V extends Comparable<V>> implements Comparator<K>{
	HashMap<K,V> map = new HashMap<K,V>();

	/**
	 * @param map HashMap<K, V>
	 */
	public ValueComparator(HashMap<K, V> map) {
		super();
		this.map.putAll(map);
	}

	@Override
	public int compare(K arg0, K arg1) {
		return -map.get(arg0).compareTo(map.get(arg1));
	}
	
	

}
