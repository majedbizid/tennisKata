package com.sgcib.kata.model;

import java.util.Objects;

/**
 * Player to get player name, game score and if he is winner
 */
public class Player {

    private String playerName;
    private int score;
    private boolean isGameWinner;
    private boolean isMatchWinner;
    private String scoreDeuceRule;
    private int tieBreakScore;

    public Player(String playerName, int score, String scoreDeuceRule, int tieBreakScore, boolean isGameWinner, boolean isMatchWinner) {
        this.playerName = playerName;
        this.score = score;
        this.isGameWinner = isGameWinner;
        this.scoreDeuceRule = scoreDeuceRule;
        this.isMatchWinner = isMatchWinner;
        this.tieBreakScore = tieBreakScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameWinner() {
        return isGameWinner;
    }

    public void setGameWinner(boolean gameWinner) {
        isGameWinner = gameWinner;
    }

    public String getScoreDeuceRule() {
        return scoreDeuceRule;
    }

    public void setScoreDeuceRule(String scoreDeuceRule) {
        this.scoreDeuceRule = scoreDeuceRule;
    }

    public boolean isMatchWinner() {
        return isMatchWinner;
    }

    public void setMatchWinner(boolean matchWinner) {
        isMatchWinner = matchWinner;
    }

    public int getTieBreakScore() {
        return tieBreakScore;
    }

    public void setTieBreakScore(int tieBreakScore) {
        this.tieBreakScore = tieBreakScore;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score &&
                isGameWinner == player.isGameWinner &&
                isMatchWinner == player.isMatchWinner &&
                tieBreakScore == player.tieBreakScore &&
                Objects.equals(playerName, player.playerName) &&
                Objects.equals(scoreDeuceRule, player.scoreDeuceRule);
    }

    @Override
    public int hashCode() {

        return Objects.hash(playerName, score, isGameWinner, isMatchWinner, scoreDeuceRule, tieBreakScore);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                ", isGameWinner=" + isGameWinner +
                ", isMatchWinner=" + isMatchWinner +
                ", scoreDeuceRule='" + scoreDeuceRule + '\'' +
                ", tieBreakScore=" + tieBreakScore +
                '}';
    }


}
