package com.aspencapital.core.util;

import com.aspencapital.core.model.classic.ClassicCard;
import com.aspencapital.core.model.classic.ClassicRank;
import com.aspencapital.core.model.classic.ClassicSuit;
import com.aspencapital.core.model.generic.Card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class GameUtils
{
    public static List<ClassicCard> generateNewClassicCardDeck()
    {
        List<ClassicRank> classicRanks = Arrays.asList(ClassicRank.values());
        List<ClassicSuit> classicSuits = Arrays.asList(ClassicSuit.values());

        return classicSuits
                .stream()
                .flatMap(s -> classicRanks
                        .stream()
                        .map(r -> ClassicCard.builder()
                                .rank(r)
                                .suit(s)
                                .build()))
                .collect(Collectors.toList());
    }

    public static void shuffleDeck(List<? extends Card<?, ?>> deck)
    {
        Collections.shuffle(deck);
    }
}
