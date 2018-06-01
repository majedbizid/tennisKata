package com.sgcib.kata.utils;

public enum Players {

    PLAYER1("player1"),
    PLAYER2("player2");

    private String playerName;

    Players(String player) {
        this.playerName = player;
    }

    public String getPlayerName() {
        return playerName;
    }
}
