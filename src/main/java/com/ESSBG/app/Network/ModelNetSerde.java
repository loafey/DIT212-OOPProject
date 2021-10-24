package com.ESSBG.app.Network;

import org.json.JSONObject;

/**
 * Author: Björn Rosengren
 *
 * This class is made to convert the modeldata to a serialized representation to
 * be able to send and receive data over the net. Since the usage of JSON
 * package got pretty heavy, the need of reducing dependency on one package is
 * solved by adapter/facade pattern. A strategy pattern would be optimal if
 * there is time.
 */
public class ModelNetSerde {
    private static ModelNetSerde self;

    private ModelNetSerde() {
    }

    public static ModelNetSerde getInstance() {
        if (self == null) {
            self = new ModelNetSerde();
        }
        return self;
    }

    public Object nullvalue() {
        return JSONObject.NULL;
    }

    public String serialize(HashMapWithTypes m) {
        JSONObject n = new JSONObject(m.getMap());
        return n.toString();
    }

    public HashMapWithTypes deserialize(JSONObject json) {
        return new HashMapWithTypes(json.toMap());
    }

    public HashMapWithTypes parse(String str) {
        HashMapWithTypes d = new HashMapWithTypes(new JSONObject(str).toMap());
        return d;
    }
}
