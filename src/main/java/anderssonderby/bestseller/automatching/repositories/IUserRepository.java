package anderssonderby.bestseller.automatching.repositories;

import anderssonderby.bestseller.automatching.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.nickname = ?1")
    User findUserByNickname(String nickname);

    @Query("select (count(u) > 0) from User u where u.nickname = ?1")
    boolean existsByNickname(String nickname);
}