package com.aspencapital.core.model;

import com.aspencapital.core.json.GameResult;
import com.aspencapital.core.model.generic.GameType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WarGameTest
{

    @Autowired
    GameFactory factory;

    /**
     * Given more time I would have generated seeded card shuffles in order to generate a deterministic test.
     * For the purposes of giving you a sample test, I will provide the below code which tests that the game dynamic
     * has an expected net score which approaches zero as sample size -> inf.
     */
    @Test
    void generateNewGame()
    {
        int games = 10_000;
        int netScore = 0;
        GameResult result;
        for(int i = 0; i < games; i++)
        {
            result = factory.generateNewGame(GameType.WAR).newGameRepl();
            netScore += result.getWinningPlayer() == null ? 0 : result.getWinningPlayer().getId() == 1L ? 1 : -1;
        }
        assertTrue(Math.abs(netScore) < games * .05);
    }
}