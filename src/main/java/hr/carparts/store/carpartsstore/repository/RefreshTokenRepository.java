package hr.carparts.store.carpartsstore.repository;

import hr.carparts.store.carpartsstore.domain.UserInfo;
import hr.carparts.store.carpartsstore.model.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserInfo(UserInfo userInfo);

}