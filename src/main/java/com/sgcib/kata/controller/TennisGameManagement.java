package com.sgcib.kata.controller;

import com.sgcib.kata.service.GameManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * TennisGameManagement is the controller that exposes REST API
 * to manage a Tennis Game
 *
 * @author mbizid
 */
@RestController
public class TennisGameManagement {

    @Autowired
    GameManagement gameManagement;

    @RequestMapping(value = "/gameScore/{player}", method = RequestMethod.GET)
    public int getScore(@PathVariable("player") String playerName) {
        return gameManagement.getScore(playerName);
    }

    @RequestMapping(value = "/winner", method = RequestMethod.GET)
    public String getWinner() {
        return gameManagement.getWinner();
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<String> getPlayers() {
        return gameManagement.getPlayerNames();
    }

    @RequestMapping(value = "/initGame", method = RequestMethod.GET)
    public String initGame() {
        gameManagement.initGame();
        return "OK";
    }

    @RequestMapping(value = "/matchScore", method = RequestMethod.GET)
    public Map<String, Integer> matchScore() {
        return gameManagement.getMatchScore();
    }
}


