package com.sgcib.kata.controller;

import com.sgcib.kata.TennisGameBootApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TennisGameManagement.class)
@ContextConfiguration(classes = TennisGameBootApplication.class)
public class TennisGameManagementTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    TennisGameManagement tennisGameManagement;

    @Test
    public void check_score_service_response() throws Exception {

        final int scorePlayer1 = 15;
        given(tennisGameManagement.getScore("player1")).willReturn(scorePlayer1);
        MvcResult result = mockMvc.perform(get("/gameScore/player1")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
        String resValue = result.getResponse().getContentAsString();

        Assert.assertEquals(Integer.toString(scorePlayer1), resValue);
    }


    @Test
    public void check_winner_service_response() throws Exception {

        final String winnerPlayer = "player1";
        given(tennisGameManagement.getWinner()).willReturn(winnerPlayer);
        MvcResult result = mockMvc.perform(get("/winner")
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
        String resValue = result.getResponse().getContentAsString();

        Assert.assertEquals(winnerPlayer, resValue);
    }

    // TODO finish TU for better coverage
}
