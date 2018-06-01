package com.sgcib.kata.utils;

import com.sgcib.kata.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersCache {

    private static List<Player> playerCache = null;

    public static List<Player> getPlayersCache() {
        if (playerCache == null) {
            playerCache = new ArrayList<Player>() {
                {
                    add(new Player(Players.PLAYER1.getPlayerName(), 0, null, 0,false, false));
                    add(new Player(Players.PLAYER2.getPlayerName(), 0, null, 0,false, false));
                }
            };
        }
        return playerCache;
    }
}
