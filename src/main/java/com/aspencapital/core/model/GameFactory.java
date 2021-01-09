package com.aspencapital.core.model;

import com.aspencapital.core.model.classic.ClassicCard;
import com.aspencapital.core.model.generic.CardGame;
import com.aspencapital.core.model.generic.GameType;
import com.aspencapital.core.model.generic.Player;
import com.aspencapital.core.model.war.WarGame;
import com.aspencapital.sql.repository.PlayerRepository;
import com.aspencapital.core.util.GameUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GameFactory
{
    final PlayerRepository playerRepository;

    public GameFactory(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

    public CardGame<?> generateNewGame(GameType type)
    {
        switch (type)
        {
            case WAR:
                List<ClassicCard> deck = GameUtils.generateNewClassicCardDeck();
                GameUtils.shuffleDeck(deck);
                return new WarGame(Arrays.asList(new Player<>("Player 1", 1L), new Player<>("Player 2", 2L)), deck, playerRepository);
            default:
                throw new IllegalArgumentException("Please enter a valid game type.");
        }

    }
}
