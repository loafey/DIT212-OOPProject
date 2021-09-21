package com.ESSBG.app.Network;

import java.io.*;
import java.net.*;
import org.json.*;

abstract class Base {
    /**
     * @return a boolean if data was successfully sent
     * @throws IOException
     */
    protected void sendData(Socket clientSocket, JSONObject jsonobj) throws IOException {
        byte[] b = jsonobj.toString().getBytes(Constants.encoding);
        OutputStream clientStream = clientSocket.getOutputStream();
        clientStream.write(Converter.intToByteArray(b.length));
        clientStream.write(b);
    }
}