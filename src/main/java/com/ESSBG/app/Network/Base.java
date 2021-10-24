package com.ESSBG.app.Network;

import java.io.*;
import java.net.Socket;

/**
 * Author: Björn Rosengren
 *
 * Baseclass to reduce copy paste, easier testing.
 *
 * Just the same method for client and server.
 *
 * Sends a 4 byte long header to tell the receiver how long the message is. Most
 * sockets have "read-all" function, but if there is a small delay in the
 * middle, this function could think it received the entire message and thus
 * crash the program. For programminglanguages with "read-until", you can do it
 * with a "stop-byte", this ensures that whatever reads has to read exactly this
 * many bytes.
 */
abstract class Base {
    private final static ModelNetSerde serde = ModelNetSerde.getInstance();

    protected void sendData(Socket clientSocket, HashMapWithTypes data) throws IOException, IllegalArgumentException {
        byte[] b = serde.serialize(data).getBytes(Constants.encoding);
        OutputStream clientStream = clientSocket.getOutputStream();
        clientStream.write(Converter.intToByteArray(4, b.length));
        clientStream.write(b);
    }
}