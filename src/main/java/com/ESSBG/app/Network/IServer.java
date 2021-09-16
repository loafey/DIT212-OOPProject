package com.ESSBG.app.Network;

interface IServer {
    void runServer();
    boolean stopServer();
    boolean sendData();
    boolean initServer();
    boolean getData();
}