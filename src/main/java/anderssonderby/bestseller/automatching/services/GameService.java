package anderssonderby.bestseller.automatching.services;

import anderssonderby.bestseller.automatching.AlreadyExistsException;
import anderssonderby.bestseller.automatching.models.Game;
import anderssonderby.bestseller.automatching.repositories.IGameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final IGameRepository gameRepository;

    public GameService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game addGame(Game game) throws AlreadyExistsException {

        var exists = gameRepository.existsByTitle(game.getTitle());

        if (exists) {
            throw new AlreadyExistsException(game.getTitle() + " already exists");
        }
        return gameRepository.save(game);
    }

    public List<Game> getAllGames() {
        var games = new ArrayList<Game>();
        gameRepository.findAll().forEach(games::add);

        return games;
    }
}
