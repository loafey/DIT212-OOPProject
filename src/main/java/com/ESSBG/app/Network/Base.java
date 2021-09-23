package com.ESSBG.app.Network;

import java.io.*;
import java.net.*;
import org.json.*;

abstract class Base {
    protected void sendData(Socket clientSocket, JSONObject jsonobj) throws IOException, IllegalArgumentException {
        byte[] b = jsonobj.toString().getBytes(Constants.encoding);
        OutputStream clientStream = clientSocket.getOutputStream();
        clientStream.write(Converter.intToByteArray(4, b.length));
        clientStream.write(b);
    }
}