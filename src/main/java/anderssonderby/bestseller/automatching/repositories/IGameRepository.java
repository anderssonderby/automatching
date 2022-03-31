package anderssonderby.bestseller.automatching.repositories;

import anderssonderby.bestseller.automatching.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IGameRepository extends CrudRepository<Game, Long> {

    @Query("select g from Game g where g.title = ?1")
    Game findGameByTitle(String title);

    @Query("select (count(g) > 0) from Game g where g.title = ?1")
    boolean existsByTitle(String title);
}
