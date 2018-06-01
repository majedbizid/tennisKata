package com.sgcib.kata.service;

import com.sgcib.kata.model.Player;
import com.sgcib.kata.utils.PlayersCache;
import com.sgcib.kata.utils.ScoreTable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Tennis GameManagement service
 */
@Service
public class GameManagement {

    public int getScore(final String playerName) {
        return PlayersCache.getPlayersCache().stream()
                .filter(player -> player.getPlayerName().equals(playerName))
                .mapToInt(player -> player.getScore()).sum();

    }

    public String getWinner() {
        for (Player player : PlayersCache.getPlayersCache()) {
            if (player.isGameWinner()) {
                return player.getPlayerName();
            }
        }
        return null;
    }

    public List<String> getPlayerNames() {
        return PlayersCache.getPlayersCache().stream()
                .map(player -> player.getPlayerName())
                .collect(Collectors.toList());
    }

    public void initGame() {
        PlayersCache.getPlayersCache().forEach(p -> {
                    p.setGameWinner(false);
                    p.setScore(0);
                    p.setScoreDeuceRule(null);
                    p.setMatchWinner(false);
                    p.setTieBreakScore(0);
                }
        );

        ScoreTable.getScoreTable().replaceAll((k, v) -> v = 0);
    }

    public Map<String, Integer> getMatchScore() {
        return ScoreTable.getScoreTable();
    }
}
