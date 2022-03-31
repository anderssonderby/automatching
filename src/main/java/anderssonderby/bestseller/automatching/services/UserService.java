package anderssonderby.bestseller.automatching.services;

import anderssonderby.bestseller.automatching.AlreadyExistsException;
import anderssonderby.bestseller.automatching.models.User;
import anderssonderby.bestseller.automatching.models.UserGame;
import anderssonderby.bestseller.automatching.repositories.IGameRepository;
import anderssonderby.bestseller.automatching.repositories.IUserGameRepository;
import anderssonderby.bestseller.automatching.repositories.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IUserGameRepository userGameRepository;
    private final IGameRepository gameRepository;

    public UserService(IUserRepository userRepository, IUserGameRepository userGameRepository, IGameRepository gameRepository) {
        this.userRepository = userRepository;
        this.userGameRepository = userGameRepository;
        this.gameRepository = gameRepository;
    }

    public User enrollUser(User user) throws AlreadyExistsException {

        var exists = userRepository.existsByNickname(user.getNickname());

        if (exists) {
            throw new AlreadyExistsException(user.getNickname() + " already exists");
        }
        return userRepository.save(user);
    }

    public UserGame addGameToUser(String nickname, String title, String level) {

        var user = userRepository.findUserByNickname(nickname);

        var game = gameRepository.findGameByTitle(title);

        var usergame = new UserGame(user, game, level);

        return userGameRepository.save(usergame);
    }

    public User addCreditsToUser(String nickname, int credits) {

        var user = userRepository.findUserByNickname(nickname);

        user.setCredits(user.getCredits() + credits);

        return userRepository.save(user);
    }
}
