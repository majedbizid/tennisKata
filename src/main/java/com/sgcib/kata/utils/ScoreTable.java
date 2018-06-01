package com.sgcib.kata.utils;

import java.util.HashMap;
import java.util.Map;

public class ScoreTable {

    private static HashMap scoreTable;

    public static Map<String, Integer> getScoreTable() {
        if (scoreTable == null) {
            scoreTable = new HashMap<String, Integer>() {
                {
                    put(Players.PLAYER1.getPlayerName(), 0);
                    put(Players.PLAYER2.getPlayerName(), 0);
                }
            };

        }
        return scoreTable;
    }
}
