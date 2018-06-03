package com.sgcib.kata.service;

import com.sgcib.kata.model.Player;
import com.sgcib.kata.utils.PlayersCache;
import com.sgcib.kata.utils.ScoreTable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Tennis GamePlay service exposes method to win points in a tennis game
 */
@Service
public class GamePlay {

    private final String DEUCE = "DEUCE";
    private final String ADV = "ADV";

    /**
     * This method take winner point player name as argument and launch score updates
     *
     * @param player
     */
    public void winPoint(final String player) {
        // verify player names
        Optional<Player> winPointPlayerOpt = PlayersCache.getPlayersCache().stream()
                .filter(p -> p.getPlayerName().equals(player))
                .findFirst();
        Optional<Player> opposedPlayerOpt = PlayersCache.getPlayersCache().stream()
                .filter(p -> !p.getPlayerName().equals(player))
                .findFirst();


        if (winPointPlayerOpt.isPresent() && opposedPlayerOpt.isPresent()) {
            updateScoreTable(winPointPlayerOpt.get(), opposedPlayerOpt.get());
        }

    }

    /**
     * this method will verify if some rules as activated as tiebreak rule or deuce rule
     * before launch score computations
     *
     * @param winPointPlayer
     * @param opposedPlayer
     */
    private void updateScoreTable(final Player winPointPlayer, final Player opposedPlayer) {
        if (winPointPlayer.getTieBreakScore() == 0
                && opposedPlayer.getTieBreakScore() == 0
                && !isTieBreakRuleActivated(winPointPlayer, opposedPlayer)) {

            if (winPointPlayer.getScore() == 40 && !isDeuceRuleActivated()) {
                declareWinnerAndInitializeScore(winPointPlayer, opposedPlayer);
            } else {
                computeAndUpdateScore(winPointPlayer, opposedPlayer);
            }

        } else {
            computeAndManageScoreTableWhenTieBreak(winPointPlayer, opposedPlayer);
        }
    }

    /**
     * test deuce rule
     *
     * @param winnerPointPlayer
     * @param opposedPlayer
     */
    private void computeAndUpdateScore(final Player winnerPointPlayer, final Player opposedPlayer) {
        if (!isDeuceRuleActivated()) {
            computeBeginMatchScore(winnerPointPlayer, opposedPlayer);
        } else {
            computeScoreWhenDeuceRuleActivatedAndDeclareWinner(winnerPointPlayer, opposedPlayer);
        }
    }

    /**
     * compute game score
     *
     * @param winnerPointPlayer
     * @param opposedPlayer
     */
    private void computeBeginMatchScore(final Player winnerPointPlayer, final Player opposedPlayer) {
        int winnerPointScore = winnerPointPlayer.getScore();
        if (winnerPointScore == 30) {
            winnerPointScore += 10;
        } else {
            winnerPointScore += 15;
        }

        for (Player playerToUpdate : PlayersCache.getPlayersCache()) {
            if (winnerPointScore == 40 && opposedPlayer.getScore() == 40) {
                playerToUpdate.setScoreDeuceRule(DEUCE);
            }
            if (playerToUpdate.getPlayerName().equals(winnerPointPlayer.getPlayerName())) {
                playerToUpdate.setScore(winnerPointScore);
            }
        }
    }

    /**
     * declare winner and initialize score
     *
     * @param winnerPointPlayer
     * @param opposedPlayer
     */
    private void declareWinnerAndInitializeScore(final Player winnerPointPlayer, final Player opposedPlayer) {
        for (Player playerToUpdate : PlayersCache.getPlayersCache()) {
            winnerPointPlayer.setScore(0);
            if (playerToUpdate.getPlayerName().equals(winnerPointPlayer.getPlayerName())) {
                playerToUpdate.setGameWinner(true);
                manageScoreTable(winnerPointPlayer, opposedPlayer);
                break;
            }

        }
    }

    /**
     * compute score when deuce rule was activated and then declare winner
     *
     * @param winnerPointPlayer
     * @param opposedPlayer
     */
    private void computeScoreWhenDeuceRuleActivatedAndDeclareWinner(final Player winnerPointPlayer, final Player opposedPlayer) {

        if (DEUCE.equals(winnerPointPlayer.getScoreDeuceRule())) {
            winnerPointPlayer.setScoreDeuceRule(ADV);
            opposedPlayer.setScoreDeuceRule(null);
        } else if (ADV.equals(winnerPointPlayer.getScoreDeuceRule())) {
            winnerPointPlayer.setGameWinner(true);
            for (Player player : PlayersCache.getPlayersCache()) {
                player.setScore(0);
            }
            // manage score table
            manageScoreTable(winnerPointPlayer, opposedPlayer);

        } else {
            winnerPointPlayer.setScoreDeuceRule(DEUCE);
            opposedPlayer.setScoreDeuceRule(DEUCE);
        }
    }

    /**
     * test if deuce rule is activated
     *
     * @return boolean
     */
    private boolean isDeuceRuleActivated() {
        boolean isDeuceRuleActivated = false;
        for (Player player : PlayersCache.getPlayersCache()) {
            if (!StringUtils.isEmpty(player.getScoreDeuceRule())) {
                isDeuceRuleActivated = true;
            }
        }
        return isDeuceRuleActivated;
    }

    /**
     * manage score table of the tennis match
     *
     * @param gameWinnerPlayer
     * @param opposedPlayer
     */
    private void manageScoreTable(final Player gameWinnerPlayer, final Player opposedPlayer) {

        int gameWinnerSetScore = ScoreTable.getScoreTable().get(gameWinnerPlayer.getPlayerName());
        int opposedSetScore = ScoreTable.getScoreTable().get(opposedPlayer.getPlayerName());

        int newSetScore = gameWinnerSetScore + 1;
        ScoreTable.getScoreTable().put(gameWinnerPlayer.getPlayerName(),
                newSetScore);
        if ((newSetScore == 6 && opposedSetScore < 5) || newSetScore == 7) {
            for (Player playerToUpdate : PlayersCache.getPlayersCache()) {
                if (playerToUpdate.getPlayerName().equals(gameWinnerPlayer.getPlayerName())) {
                    playerToUpdate.setMatchWinner(true);
                    break;
                }
            }
        }
    }

    /**
     * test if tiebreak rule is activated
     *
     * @param gameWinnerPlayer
     * @param opposedPlayer
     * @return
     */
    private boolean isTieBreakRuleActivated(final Player gameWinnerPlayer, final Player opposedPlayer) {
        if (ScoreTable.getScoreTable().get(gameWinnerPlayer.getPlayerName()) == 6
                && ScoreTable.getScoreTable().get(opposedPlayer.getPlayerName()) == 6) {
            return true;
        }
        return false;
    }

    /**
     * compute score when tiebreak was activated and update score table
     *
     * @param winnerPointPlayer
     * @param opposedPlayer
     */
    private void computeAndManageScoreTableWhenTieBreak(final Player winnerPointPlayer, final Player opposedPlayer) {
        int newTieBreakScore = winnerPointPlayer.getTieBreakScore() + 1;
        int deltaScore = newTieBreakScore - opposedPlayer.getTieBreakScore();

        for (Player playerToUpdate : PlayersCache.getPlayersCache()) {
            if (playerToUpdate.getPlayerName().equals(winnerPointPlayer.getPlayerName())) {
                playerToUpdate.setTieBreakScore(newTieBreakScore);
                if (newTieBreakScore >= 7 && deltaScore >= 2) {
                    ScoreTable.getScoreTable().put(winnerPointPlayer.getPlayerName(),
                            ScoreTable.getScoreTable().get(winnerPointPlayer.getPlayerName()).intValue() + 1);
                    playerToUpdate.setMatchWinner(true);
                }
                break;
            }
        }

    }
}