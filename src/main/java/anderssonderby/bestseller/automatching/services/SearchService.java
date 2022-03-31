package anderssonderby.bestseller.automatching.services;

import anderssonderby.bestseller.automatching.models.MatchingPlayersDTO;
import anderssonderby.bestseller.automatching.repositories.IGameRepository;
import anderssonderby.bestseller.automatching.repositories.IUserGameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final IUserGameRepository userGameRepository;
    private final IGameRepository gameRepository;

    public SearchService(IUserGameRepository userGameRepository, IGameRepository gameRepository) {
        this.userGameRepository = userGameRepository;
        this.gameRepository = gameRepository;
    }

    public List<MatchingPlayersDTO> searchForPlayers(String title, String level) {

        var game = gameRepository.findGameByTitle(title);

        var matchingPlayers = new ArrayList<MatchingPlayersDTO>();

        userGameRepository.findAllByGameAndLevel(game, level).forEach(userGame -> {
            matchingPlayers.add(new MatchingPlayersDTO(userGame.getUser().getNickname(), userGame.getGame().getTitle(), userGame.getLevel(), userGame.getUser().getRegion()));
        });

        return matchingPlayers;
    }

    public List<MatchingPlayersDTO> getTopPlayers() {

        var topPlayers = new ArrayList<MatchingPlayersDTO>();

        userGameRepository.findTopPlayers().forEach(userGame -> {
            topPlayers.add(new MatchingPlayersDTO(userGame.getUser().getNickname(), userGame.getGame().getTitle(), userGame.getLevel(), userGame.getUser().getCredits() , userGame.getUser().getRegion()));
        });

        return topPlayers;
    }
}
