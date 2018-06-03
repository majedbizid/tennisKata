package com.sgcib.kata.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ScoreTableTest {
    @Test
    public void verify_score_table_singleton(){
        // verify score Table
        Map<String, Integer> firstCall = ScoreTable.getScoreTable();
        Map<String, Integer> secondCall = ScoreTable.getScoreTable();
        Assert.assertNotNull(firstCall);
        Assert.assertEquals(firstCall, secondCall);
    }
}
