package vimal.springbootasync.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.*;
import vimal.springbootasync.model.UserDto;
import vimal.springbootasync.service.UserService;

@RestController
@RequestMapping("/userAsync")
public class UserAsyncController {

  final UserService userService;

  public UserAsyncController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/name/{lastName}")
  public CompletableFuture<List<UserDto>> getUserByName(@PathVariable String lastName) {
    return userService.getUserByNameAsync(lastName);
  }
}
