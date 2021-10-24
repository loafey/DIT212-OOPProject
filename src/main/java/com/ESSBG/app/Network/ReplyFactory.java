package com.ESSBG.app.Network;

/**
 * Author: Björn Rosengren
 *
 * To produce easy passable statements for the networking module. These types
 * are to help where to route the data.
 */
final class ReplyFactory {
    static ModelNetSerde serde = ModelNetSerde.getInstance();
    // Don't allow creating obj of this.
    private ReplyFactory() {
    }

    /**
     * @param bool Connect parameter: bool ? "I want to connect" : "I want to
     *             disconnect";
     * @return a network json object.
     */
    protected static HashMapWithTypes getNetwork(boolean bool) {
        HashMapWithTypes data = HashMapWithTypesTemplate();
        data.put("reason", "net");
        data.put("data", bool);
        return data;
    }

    /**
     * NOT FOR CLIENT USE! This should only be used for a multiplayer server.
     *
     * @param bool Connect parameter: bool ? "I want to connect" : "I want to
     *             disconnect";
     * @param id   ID of the sender/receiver/user. To be able to identify who this
     *             is and who to reply to.
     * @return a network json object with id.
     */
    protected static HashMapWithTypes getNetworkWithID(int id, boolean bool) {
        HashMapWithTypes data = getNetwork(bool);
        data.put("id", id);
        return data;
    }

    /**
     * @param data The datastructure the server and the client agreed on.
     * @return a network json object.
     */
    protected static HashMapWithTypes getGame(HashMapWithTypes data) {
        HashMapWithTypes d = HashMapWithTypesTemplate();
        d.put("reason", "game");
        d.put("data", data);
        return d;
    }

    /**
     * @param data The datastructure the server and the client agreed on.
     * @param id   ID of the sender/receiver/user. To be able to identify who this
     *             is and who to reply to.
     * @return a network json object with id.
     */
    protected static HashMapWithTypes getGameWithID(int id, HashMapWithTypes data) {
        HashMapWithTypes d = getGame(data);
        d.put("id", id);
        return d;
    }

    // Skeleton template for the jsons.
    private static HashMapWithTypes HashMapWithTypesTemplate() {
        HashMapWithTypes data = new HashMapWithTypes();
        data.put("reason", "null");
        data.put("data", serde.nullvalue());
        return data;
    }
}