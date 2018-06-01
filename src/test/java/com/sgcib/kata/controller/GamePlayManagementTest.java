package com.sgcib.kata.controller;

import com.sgcib.kata.TennisGameBootApplication;
import com.sgcib.kata.utils.PlayersCache;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TennisGameBootApplication.class)
public class GamePlayManagementTest {

    @Autowired
    TennisGameManagement tennisGameManagement;

    @Before
    public void initGame() {
        // init all player statistics each start new game
        tennisGameManagement.initGame();
    }

    // Story1
    @Test
    public void no_player_win_cause_start_game() {

        //Player player1 = new Player("player1",0,false);
        //Player player2 = new Player("player2",0,false);
        Assert.assertNull(tennisGameManagement.getWinner());
    }

    @Test
    public void player1_win_the_game_ok() {
        //Player player1 = new Player("player1",0,true);
        //Player player2 = new Player("player2",0,false);

        PlayersCache.getPlayersCache().get(0).setGameWinner(true);
        Assert.assertEquals(tennisGameManagement.getScore("player1"), 0);
        Assert.assertEquals(tennisGameManagement.getWinner(), "player1");

    }

    @Test
    public void no_player_win_the_game_cause_in_progress() {
        //Player player1 = new Player("player1",30,false);
        //Player player2 = new Player("player2",40,false);
        PlayersCache.getPlayersCache().get(0).setScore(30);
        PlayersCache.getPlayersCache().get(1).setScore(40);

        Assert.assertEquals(tennisGameManagement.getScore("player1"), 30);
        Assert.assertEquals(tennisGameManagement.getScore("player2"), 40);
        Assert.assertNull(tennisGameManagement.getWinner());
    }

    @Test
    public void verify_players_name() {
        //Player player1 = new Player("player1",30,false);
        //Player player2 = new Player("player2",40,false);

        Assert.assertEquals(tennisGameManagement.getPlayers().stream().count(), 2);
        Assert.assertEquals(tennisGameManagement.getPlayers().get(0), "player1");
        Assert.assertEquals(tennisGameManagement.getPlayers().get(1), "player2");
    }

}
