package com.sgcib.kata.controller;

import com.sgcib.kata.service.GamePlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TennisGamePlay is the controller that exposes service
 * to win points in a Tennis Game
 *
 * @author mbizid
 */
@RestController
public class TennisGamePlay {

    @Autowired
    GamePlay gamePlay;

    @RequestMapping(value = "/winPoint/{player}")
    public void winPoint(@PathVariable String player) {
        gamePlay.winPoint(player);
    }
}
