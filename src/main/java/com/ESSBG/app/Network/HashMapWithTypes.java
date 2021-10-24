package com.ESSBG.app.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Björn Rosengren
 *
 * This class is to emulate JSON object, has to be done so that entrypoints are
 * alike but not dependent on one JSON module. Just make it work, then error
 * check.
 *
 * Just promise that our assumptions on the data is true and sound. Very ugly...
 */
@SuppressWarnings("unchecked")
public class HashMapWithTypes {
    private final static ModelNetSerde serde = ModelNetSerde.getInstance();
    private final Map<String, Object> map;

    public HashMapWithTypes() {
        this.map = new HashMap<String, Object>();
    }

    public HashMapWithTypes(Map<String, Object> map) {
        this.map = map;
    }

    // This is very ugly... no time
    public HashMapWithTypes(String str) {
        this.map = serde.parse(str).getMap();
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public boolean has(String o) {
        return map.containsKey(o);
    }

    public boolean getBoolean(String key) {
        return (boolean) map.get(key);
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public int getInt(String key) {
        return (int) map.get(key);
    }
    public float getFloat(String key) {
        return (float) map.get(key);
    }

    public ArrayList<String> getStringList(String key) {
        return (ArrayList<String>) map.get(key);
    }

    public ArrayList<Integer> getIntegerList(String key) {
        return (ArrayList<Integer>) map.get(key);
    }

    public ArrayList<HashMapWithTypes> getHashMapWithTypesList(String key) {
        return (ArrayList<HashMapWithTypes>) map.get(key);
    }

    public String getString(String key) {
        return (String) map.get(key);
    }

    public HashMapWithTypes getHashMapWithTypes(String key) {
        return (HashMapWithTypes) map.get(key);
    }
}