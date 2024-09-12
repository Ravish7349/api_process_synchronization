package org.example;

import org.springframework.stereotype.Service;

@Service
public class ServerManager {

    private boolean isServer1Active = true;

    public void switchServer() {
        isServer1Active = !isServer1Active;
        System.out.println("Switched to " + (isServer1Active ? "Server 1" : "Server 2"));
    }

    public boolean isServer1Active() {
        return isServer1Active;
    }
}
