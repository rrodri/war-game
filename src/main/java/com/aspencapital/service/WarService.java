package com.aspencapital.service;

import com.aspencapital.core.json.GameResult;
import com.aspencapital.core.model.GameFactory;
import com.aspencapital.core.model.generic.GameType;
import com.aspencapital.sql.entity.PlayerEntity;
import com.aspencapital.sql.repository.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "/war-service")
public class WarService
{
    private final GameFactory factory;

    final PlayerRepository playerRepository;

    public WarService(GameFactory factory, PlayerRepository playerRepository)
    {
        this.factory = factory;
        this.playerRepository = playerRepository;
    }

    @GetMapping(value = "/startGame", produces = APPLICATION_JSON_VALUE)
    public GameResult startNewGame()
    {
        return factory.generateNewGame(GameType.WAR).newGameRepl();
    }

    @GetMapping(value = "/getPlayerStats", produces = APPLICATION_JSON_VALUE)
    public List<PlayerEntity> getPlayerStats() {
        return playerRepository.findAll();
    }
}