package com.sgcib.kata.utils;

import com.sgcib.kata.model.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlayersCacheTest {
    @Test
    public void verify_players_cache_singleton() {
        //verify Cache
        List<Player> firstCacheCall = PlayersCache.getPlayersCache();
        List<Player> secondCacheCall = PlayersCache.getPlayersCache();
        Assert.assertNotNull(firstCacheCall);
        Assert.assertNotNull(secondCacheCall);
        Assert.assertEquals(firstCacheCall, secondCacheCall);
    }
}
