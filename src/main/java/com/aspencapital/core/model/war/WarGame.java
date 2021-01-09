package com.aspencapital.core.model.war;

import com.aspencapital.core.model.Player;
import com.aspencapital.core.json.GameResult;
import com.aspencapital.core.model.classic.ClassicCard;
import com.aspencapital.core.model.generic.CardGame;
import com.aspencapital.sql.entity.PlayerEntity;
import com.aspencapital.sql.repository.PlayerRepository;
import com.aspencapital.core.util.GameUtils;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class WarGame extends CardGame<ClassicCard>
{

    private final WarCardCompare warCardCompare;

    private PlayerRepository playerRepository;

    public WarGame(@NonNull List<Player<ClassicCard>> players, @NonNull List<ClassicCard> deck, PlayerRepository playerRepository)
    {
        super(players, deck);
        warCardCompare = new WarCardCompare();
        this.playerRepository = playerRepository;
    }

    @Override
    public void setUpGame()
    {
        GameUtils.shuffleDeck(this.getDeck());
        int count = 0;
        for (ClassicCard card : this.getDeck())
        {
            this.getPlayers().get(count++ % this.getPlayers().size()).addCardToBottomHand(card);
        }
    }

    @Override
    public GameResult newGameRepl()
    {
        GameResult gameResult = new GameResult();

        Player<ClassicCard> player1 = this.getPlayers().get(0);
        Player<ClassicCard> player2 = this.getPlayers().get(1);
        Player<ClassicCard> winner = null;

        this.setUpGame();


        ClassicCard c1, c2;

        Queue<ClassicCard> tableCards = new LinkedList<>();
        StringBuilder summary;

        int outcome;

        while (!player1.getHand().isEmpty() && !player2.getHand().isEmpty())
        {
            summary = new StringBuilder();

            c1 = player1.drawCardFromTopHand();
            summary.append(String.format("%s DRAWS %s.\n", player1.getName(), c1));

            c2 = player2.drawCardFromTopHand();
            summary.append(String.format("%s DRAWS %s.\n", player2.getName(), c2));

            tableCards.add(c1);
            tableCards.add(c2);

            if (warCardCompare.compare(c1, c2) == 0)
            {
                summary.append("WAR!!!\n");

                while (true)
                {
                    // face down cards
                    c1 = player1.drawCardFromTopHand();
                    c2 = player2.drawCardFromTopHand();


                    tableCards.add(c1);
                    tableCards.add(c2);

                    if (c1 == null || c2 == null)
                    {
                        break;
                    }

                    summary.append("BOTH PLAYERS SET DOWN BURN CARDS!\n");

                    // face up cards
                    c1 = player1.drawCardFromTopHand();
                    c2 = player2.drawCardFromTopHand();

                    tableCards.add(c1);
                    tableCards.add(c2);

                    if (c1 == null || c2 == null)
                    {
                        break;
                    }

                    summary.append(String.format("%s DRAWS %s.\n", player1.getName(), c1));
                    summary.append(String.format("%s DRAWS %s.\n", player2.getName(), c2));

                    if (warCardCompare.compare(c1, c2) != 0)
                    {
                        break;
                    }
                }
            }

            outcome = warCardCompare.compare(c1, c2);
            winner = outcome == 0 ? null : outcome > 0 ? player1 : player2;
            // shuffling the winning cards... slight modification to increase randomness
            // and reduce number of rounds played
            Collections.shuffle((List<?>) tableCards);
            while (winner != null && !tableCards.isEmpty())
            {
                if (tableCards.peek() == null)
                {
                    tableCards.poll();
                    continue;
                }
                winner.addCardToBottomHand(tableCards.poll());
            }
            gameResult.addNewRoundResult(summary.toString(), player1.getHand().size() - player2.getHand().size());
        }
        gameResult.setWinningPlayer(winner);
        if (winner != null)
        {
            Optional<PlayerEntity> playerEntity = playerRepository.findById(winner.getId());
            playerEntity.ifPresent(p ->
            {
                p.setWin_count(p.getWin_count() + 1);
                playerRepository.save(p);
            });
        }
        return gameResult;
    }

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

    private static class WarCardCompare implements Comparator<ClassicCard>
    {
        @Override
        public int compare(ClassicCard o1, ClassicCard o2)
        {
            return o1 == null && o2 == null ? 0 : o1 == null ? -1 : o2 == null ? 1 : o1.getRank().getValue() - o2.getRank().getValue();
        }
    }
}
