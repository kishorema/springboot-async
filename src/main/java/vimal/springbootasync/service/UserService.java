package vimal.springbootasync.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vimal.springbootasync.domain.User1;
import vimal.springbootasync.model.UserDto;
import vimal.springbootasync.repo.UserRepository;

@Service
@Transactional
public class UserService {

  final UserRepository userRepo;

  public UserService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  public List<UserDto> getUserByName(String firstName) {
    List<UserDto> userDtos = new ArrayList<>();
    List<User1> user1s = userRepo.findAllByFirstName(firstName);
    for (User1 u : user1s) {
      userDtos.add(userToUserDto(u));
    }
    return userDtos;
  }

  @Async
  public CompletableFuture<List<UserDto>> getUserByNameAsync(String lastName) {
    CompletableFuture<List<User1>> users = userRepo.findAllByLastName(lastName);
    return users.thenApply(
        urs -> {
          return urs.stream().map(this::userToUserDto).collect(Collectors.toList());
        });
  }

  public UserDto createUser(UserDto u) {
    User1 user1 = new User1();
    user1.setEmailId(u.getEmailId());
    user1.setPhoneNbr(u.getPhoneNbr());
    user1.setFirstName(u.getFirstName());
    user1.setLastName(u.getLastName());
    user1 = userRepo.save(user1);
    return userToUserDto(user1);
  }

  public UserDto getUserById(Integer id) {
    Optional<User1> userOpt = userRepo.findById(id);
    UserDto u = new UserDto();

    if (userOpt.isPresent()) {
      u = userToUserDto(userOpt.get());
    }

    return u;
  }

  private UserDto userToUserDto(User1 u) {

    UserDto userDto = new UserDto();
    userDto.setEmailId(u.getEmailId());
    userDto.setPhoneNbr(u.getPhoneNbr());
    userDto.setFirstName(u.getFirstName());
    userDto.setLastName(u.getLastName());
    userDto.setUserId(u.getUserId());

    return userDto;
  }
}
