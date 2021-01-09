package com.aspencapital.core.model.generic;

import com.aspencapital.core.model.Player;
import com.aspencapital.core.json.GameResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Getter
@AllArgsConstructor
public abstract class CardGame<C extends Card<?, ?>>
{
    @NonNull
    private final List<Player<C>> players;

    @NonNull
    private final List<C> deck;

    public abstract GameResult newGameRepl();

    public abstract void setUpGame();
}
