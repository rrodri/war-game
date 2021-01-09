package com.aspencapital.core.model.classic;

import com.aspencapital.core.model.generic.Card;
import lombok.Builder;
import lombok.NonNull;

public class ClassicCard extends Card<ClassicSuit, ClassicRank>
{


    @Builder
    public ClassicCard(@NonNull ClassicSuit suit, @NonNull ClassicRank rank)
    {
        super(suit, rank);
    }

    @Override
    public int compareTo(Card<ClassicSuit, ClassicRank> o)
    {
        return 0;
    }
}
