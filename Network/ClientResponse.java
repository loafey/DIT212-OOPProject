package com.ESSBG.app.Network;

public enum CMD {
    CONNECT,
    DISCONNECT}

public class ClientResponse() {
    String id;
    CMD cmd;
    public ClientResponse(String id, CMD cmd) {
        this.id = id;
        this.cmd = cmd;
    }
}