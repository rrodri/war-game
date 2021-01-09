package com.aspencapital.core.model;

import com.aspencapital.core.model.generic.Card;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class Player<C extends Card<?, ?>>
{
    private final Long id;

    private final String name;

    private final Queue<C> hand;

    public Player(String name, Long id)
    {
        this.name = name;
        this.id = id;
        hand = new LinkedList<>();
    }

    public C drawCardFromTopHand()
    {
        return hand.poll();
    }

    public void addCardToBottomHand(C card)
    {
        hand.add(card);
    }
}
