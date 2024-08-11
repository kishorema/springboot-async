package vimal.springbootasync.repo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import vimal.springbootasync.domain.User1;

@Repository
public interface UserRepository extends JpaRepository<User1, Integer> {

  List<User1> findAllByFirstName(String name);

  @Async
  CompletableFuture<List<User1>> findAllByLastName(String name);
}
