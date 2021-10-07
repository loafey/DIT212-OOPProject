package com.ESSBG.app.Network;

/**
 * Author: Björn Rosengren
 *
 * A constant interface to be able to customize without knowing what the code
 * does.
 */
public interface Constants {
    final String IP = "0.0.0.0";
    final int PORT = 27015;
    final int MAXPLAYERS = 7;
    final int BUFFER_SIZE = 4096;
    final String encoding = "utf-8";
    final int HEADERLEN = 4;
}