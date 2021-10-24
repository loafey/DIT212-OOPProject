package com.ESSBG.app.Render.LobbyScreen;

import com.ESSBG.app.GameServer;
import com.ESSBG.app.Network.Client;
import com.ESSBG.app.Render.ScreenManager;
import com.ESSBG.app.Render.GameScene.GameScene;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.json.JSONObject;

public class LobbyScreenController {
    private Client client;

    public LobbyScreenController(Button hostButton, Button joinButton, Button leaveButton, Button startButton,
            TextField ipField) {
        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                new Thread(new GameServer()).start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                client = new Client();
                client.runClient();
            }
        });

        joinButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client = new Client();
                client.runClient(ipField.getText());
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JSONObject data = new JSONObject("{\"start\": true}");
                data.put("msgNum", 0);      
                client.sendData(data);
            }
        });
    }

    // TODO should really be done using an observer pattern.
    public void pollClient() {
        if (client != null) {
            if (client.getMsgQueue().size() > 0) {
                try {
                    JSONObject message = client.getMsgQueue().take();
                    String reason = message.getString("reason");
                    if (reason.equals("net")) {
                        if (message.getBoolean("data")) {
                            System.out.println("Connected!");
                        } else {
                            System.out.println("Disconnected!");
                        }
                    } else if (reason.equals("game")) {
                        if (message.getJSONObject("data").getBoolean("start")) {
                            ScreenManager.getInstance().setScreen(new GameScene(client));
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
