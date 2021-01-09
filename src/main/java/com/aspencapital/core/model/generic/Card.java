package com.aspencapital.core.model.generic;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@SuperBuilder
public abstract class Card<S extends Enum<?>, R extends Enum<?>> implements Comparable<Card<S, R>>
{
    @NonNull
    private final S suit;

    @NonNull
    private final R rank;

    @Override
    public String toString()
    {
        return String.format("%s OF %s", this.rank, this.suit);
    }
}
