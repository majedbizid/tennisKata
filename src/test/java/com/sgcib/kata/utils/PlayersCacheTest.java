package com.sgcib.kata.utils;

import com.sgcib.kata.model.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlayersCacheTest {
    @Test
    public void verify_players_cache_singleton() {
        List<Player> playersCache1 = PlayersCache.getPlayersCache();
        List<Player> playersCache2 = PlayersCache.getPlayersCache();
        Assert.assertNotNull(playersCache1);
        Assert.assertNotNull(playersCache2);
        Assert.assertEquals(playersCache1, playersCache2);
    }
}
