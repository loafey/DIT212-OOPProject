package com.ESSBG.app.Network;

import java.io.*;
import java.net.*;
import org.json.*;

abstract class Base {
    /**
     * @return a boolean if data was successfully sent
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    protected boolean naiveSendData(Socket clientSocket, JSONObject jsonobj) throws UnsupportedEncodingException {
        byte[] b = jsonobj.toString().getBytes(Constants.encoding);
        int msgLen = b.length;
        try {
            OutputStream clientStream = clientSocket.getOutputStream();
            clientStream.write(Converter.intToByteArray(msgLen));
            clientStream.write(b);
        } catch (IOException e) {
            // for example if a client has disconnected without telling us.
            return false;
        }
        return true;

    }
}
