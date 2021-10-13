package com.ESSBG.app;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.ESSBG.app.Model.ConcurrentCircularList;
import com.ESSBG.app.Model.Game;
import com.ESSBG.app.Model.Player.Player;
import com.ESSBG.app.Network.Client;
import com.ESSBG.app.Network.IServer;
import com.ESSBG.app.Network.Server;

import org.json.JSONObject;

public class reduntant_GameServerAdapter {
    private Thread serverThread;
    private Client client;    
    private Game game;

    private void startServer(){
        game = new Game();
        IServer server = new Server();
        ConcurrentHashMap<Integer, Player> joinedUsers = new ConcurrentHashMap<>();
        ConcurrentHashMap<Integer, Boolean> confirmedStart = new ConcurrentHashMap<>();
        ConcurrentCircularList<Player> players = new ConcurrentCircularList<>();
        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Start messageListener
        serverThread = new Thread(new GameServer());
        serverThread.start();
        try { 
            Thread.sleep(500); // How do we know if the server has started correctly?
        } catch (InterruptedException e) {}

        client = new Client();
        client.runClient();
        clientLoop();
    }

    private void clientLoop(){
        LinkedBlockingQueue<JSONObject> messages = client.getMsgQueue();
        while (true) {
            try {
                System.out.println("Message: " + messages.take());
            } catch (InterruptedException e) {}
        }
    }
}