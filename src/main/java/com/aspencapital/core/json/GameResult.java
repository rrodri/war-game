package com.aspencapital.core.json;

import com.aspencapital.core.model.generic.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public final class GameResult
{
    private List<RoundResult> results = new LinkedList<>();

    private Player<?> winningPlayer;

    public void addNewRoundResult(String highlight, int score)
    {
        results.add(new RoundResult(highlight, score));
    }
}
