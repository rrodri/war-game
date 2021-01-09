package com.aspencapital.core.model.classic;

import lombok.Getter;

@Getter
public enum ClassicRank
{
    ACE(14), DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

    private final int value;

    ClassicRank(int value)
    {
        this.value = value;
    }
}