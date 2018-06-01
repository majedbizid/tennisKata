package com.sgcib.kata.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ScoreTableTest {
    @Test
    public void verify_score_table_singleton(){
        Map<String, Integer> tableScore1 = ScoreTable.getScoreTable();
        Map<String, Integer> tableScore2 = ScoreTable.getScoreTable();
        Assert.assertNotNull(tableScore1);
        Assert.assertEquals(tableScore1, tableScore2);
    }
}
