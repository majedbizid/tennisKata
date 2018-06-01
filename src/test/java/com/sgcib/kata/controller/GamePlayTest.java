package com.sgcib.kata.controller;

import com.sgcib.kata.TennisGameBootApplication;
import com.sgcib.kata.model.Player;
import com.sgcib.kata.utils.PlayersCache;
import com.sgcib.kata.utils.ScoreTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TennisGameBootApplication.class)
public class GamePlayTest {

    @Autowired
    TennisGamePlay game;
    @Autowired
    TennisGameManagement gameManagement;

    @Before
    public void initGame(){
        gameManagement.initGame();
    }

    // Sprint 1
    //UserStory1
    @Test
    public void verify_players_win_points() {
        //given

        //Player player1 = new Player("player1",0,false);
        //Player player2 = new Player("player2",0,false);
        Player player1 = PlayersCache.getPlayersCache().get(0);
        Player player2 = PlayersCache.getPlayersCache().get(1);

        //when
        game.winPoint("player1");
        game.winPoint("player1");
        game.winPoint("player2");
        game.winPoint("player1");
        game.winPoint("player2");
        //then
        Assert.assertEquals(40, player1.getScore());
        Assert.assertEquals(30, player2.getScore());
    }

    @Test
    public void end_of_game_p1_win_game_score_initialized() {
        //given
        //Player player1 = new Player("player1",0,false);
        Player player1 = PlayersCache.getPlayersCache().get(0);
        //when
        game.winPoint("player1");
        game.winPoint("player1");
        game.winPoint("player1");
        game.winPoint("player1");
        //then
        Assert.assertEquals(0, player1.getScore());
    }

    @Test
    public void end_of_game_p2_win_game() {
        //given
        //Player player1 = new Player("player1",0,false);
        Player player1 = PlayersCache.getPlayersCache().get(0);
        Player player2 = PlayersCache.getPlayersCache().get(0);

        //when
        game.winPoint("player1");
        game.winPoint("player2");
        game.winPoint("player2");
        game.winPoint("player2");
        game.winPoint("player1");
        game.winPoint("player2");

        //then
        Assert.assertEquals(0, player2.getScore());
        Assert.assertTrue(player2.isGameWinner());
    }

    // Story2 tests
    @Test
    public void verify_score_when_deuce_rule_activated_p1_win() {
        //given
        Player player1 = PlayersCache.getPlayersCache().get(0);
        Player player2 = PlayersCache.getPlayersCache().get(1);
        //when
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player2"); //p2 = 15
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player2"); //p2 = 30
        game.winPoint("player2"); //p1, p2 = DEUCE
        game.winPoint("player2"); //p2 = 40, ADV
        //then
        Assert.assertEquals(40, player1.getScore());
        Assert.assertEquals("ADV", player2.getScoreDeuceRule());

        game.winPoint("player1"); //p1, p2 = DEUCE
        Assert.assertEquals("DEUCE", player1.getScoreDeuceRule());
        Assert.assertEquals("DEUCE", player2.getScoreDeuceRule());

        game.winPoint("player1"); //p1 = ADV
        Assert.assertEquals("ADV", player1.getScoreDeuceRule());

        game.winPoint("player1"); //p1 winner
        Assert.assertEquals(0, player1.getScore());
        Assert.assertEquals(0, player2.getScore());
        Assert.assertTrue(player1.isGameWinner());
    }

    @Test
    public void verify_score_when_deuce_rule_activated_p2_win() {
        //given
        Player player1 = PlayersCache.getPlayersCache().get(0);
        Player player2 = PlayersCache.getPlayersCache().get(1);

        //when
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player2"); //p2 = 15
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player2"); //p2 = 30
        game.winPoint("player2"); //p1, p2 = DEUCE
        game.winPoint("player2"); //p2 = 40, ADV

        //then
        Assert.assertEquals(40, player1.getScore());
        Assert.assertEquals("ADV", player2.getScoreDeuceRule());

        game.winPoint("player1"); //p1, p2 = DEUCE
        Assert.assertEquals("DEUCE", player1.getScoreDeuceRule());
        Assert.assertEquals("DEUCE", player2.getScoreDeuceRule());

        game.winPoint("player2"); //p2 = ADV
        Assert.assertEquals("ADV", player2.getScoreDeuceRule());

        game.winPoint("player2"); //p2 winner
        Assert.assertEquals(0, player2.getScore());
        Assert.assertEquals(0, player1.getScore());
        Assert.assertTrue(player2.isGameWinner());
    }

    // Sprint2
    // UserStory 1

    @Test
    public void verify_score_table_incrementation(){
        //given
        Player player1 = PlayersCache.getPlayersCache().get(0);
        //Player player2 = PlayersCache.getPlayersCache().get(1);

        //when

        //1st game
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player1"); //p1 game winner

        //then
        Assert.assertTrue(player1.isGameWinner());
        Assert.assertEquals(1, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(0, ScoreTable.getScoreTable().get("player2").intValue());
    }

    @Test
    public void verify_score_table_and_winner_player(){

        //given
        Player player1 = PlayersCache.getPlayersCache().get(0);
        Player player2 = PlayersCache.getPlayersCache().get(1);
        //when
        //1st game
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player1"); //p1 game winner

        //then
        Assert.assertTrue(player1.isGameWinner());
        Assert.assertEquals(1, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(0, ScoreTable.getScoreTable().get("player2").intValue());

        //2nd game
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player1"); //p1 game winner

        Assert.assertTrue(player1.isGameWinner());
        Assert.assertEquals(2, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(0, ScoreTable.getScoreTable().get("player2").intValue());

        //3rd game
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player1"); //p1 game winner

        Assert.assertTrue(player1.isGameWinner());
        Assert.assertEquals(3, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(0, ScoreTable.getScoreTable().get("player2").intValue());

        //4th game
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player1"); //p1 game winner

        Assert.assertTrue(player1.isGameWinner());
        Assert.assertEquals(4, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(0, ScoreTable.getScoreTable().get("player2").intValue());

        //5th game
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player1"); //p1 game winner

        Assert.assertTrue(player1.isGameWinner());
        Assert.assertEquals(5, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(0, ScoreTable.getScoreTable().get("player2").intValue());

        //6st game
        game.winPoint("player2"); //p1 = 15
        game.winPoint("player2"); //p1 = 30
        game.winPoint("player2"); //p1 = 40
        game.winPoint("player2"); //p1 game winner

        Assert.assertTrue(player2.isGameWinner());
        Assert.assertEquals(5, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(1, ScoreTable.getScoreTable().get("player2").intValue());

        //6st game
        game.winPoint("player1"); //p1 = 15
        game.winPoint("player1"); //p1 = 30
        game.winPoint("player1"); //p1 = 40
        game.winPoint("player1"); //p1 game winner

        Assert.assertTrue(player1.isGameWinner());
        Assert.assertEquals(6, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(1, ScoreTable.getScoreTable().get("player2").intValue());
        Assert.assertTrue(player1.isMatchWinner());
        Assert.assertFalse(player2.isMatchWinner());

    }

    // Sprint2
    // UserStory 2
    @Test
    public void verify_tibreak_rule_activated_p1_win(){

        Player player1 = PlayersCache.getPlayersCache().get(0);
        Player player2 = PlayersCache.getPlayersCache().get(1);

        ScoreTable.getScoreTable().put("player1",6);
        ScoreTable.getScoreTable().put("player2",6);

        game.winPoint("player1"); //p1 = 1
        game.winPoint("player1"); //p1 = 2
        game.winPoint("player1"); //p1 = 3
        game.winPoint("player1"); //p1 = 4

        Assert.assertFalse(player1.isMatchWinner());

        game.winPoint("player1"); //p1 = 5
        game.winPoint("player1"); //p1 = 6
        game.winPoint("player1"); //p1 = 7

        Assert.assertTrue(player1.isMatchWinner());
        Assert.assertFalse(player2.isMatchWinner());
        Assert.assertEquals(7, ScoreTable.getScoreTable().get("player1").intValue());
    }


    @Test
    public void verify_tibreak_rule_activated(){

        Player player1 = PlayersCache.getPlayersCache().get(0);
        Player player2 = PlayersCache.getPlayersCache().get(1);

        ScoreTable.getScoreTable().put("player1",6);
        ScoreTable.getScoreTable().put("player2",6);

        game.winPoint("player1"); //p1 = 1
        game.winPoint("player1"); //p1 = 2
        game.winPoint("player1"); //p1 = 3
        game.winPoint("player1"); //p1 = 4
        game.winPoint("player1"); //p1 = 5
        game.winPoint("player1"); //p1 = 6

        game.winPoint("player2"); //p2 = 1
        game.winPoint("player2"); //p2 = 2
        game.winPoint("player2"); //p2 = 3
        game.winPoint("player2"); //p2 = 4
        game.winPoint("player2"); //p2 = 5
        game.winPoint("player2"); //p2 = 6

        game.winPoint("player1"); //p1 = 7

        Assert.assertFalse(player1.isMatchWinner());
        Assert.assertFalse(player2.isMatchWinner());

        game.winPoint("player1"); //p1 = 8

        Assert.assertTrue(player1.isMatchWinner());

        Assert.assertEquals(7, ScoreTable.getScoreTable().get("player1").intValue());
        Assert.assertEquals(6, ScoreTable.getScoreTable().get("player2").intValue());

    }
}
