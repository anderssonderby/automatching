package anderssonderby.bestseller.automatching.repositories;

import anderssonderby.bestseller.automatching.models.Game;
import anderssonderby.bestseller.automatching.models.UserGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface IUserGameRepository extends CrudRepository<UserGame, Long> {

    @Query("select u from UserGame u where u.game = ?1 and u.level = ?2")
    Collection<UserGame> findAllByGameAndLevel(@NotNull Game game, @NotBlank String level);

    @Query("select u from UserGame u group by u.id, u.game, u.level order by u.user.credits desc")
    Collection<UserGame> findTopPlayers();
}
