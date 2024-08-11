package vimal.springbootasync.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import vimal.springbootasync.model.UserDto;
import vimal.springbootasync.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

  final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create")
  public UserDto create(@RequestBody UserDto user) {
    return userService.createUser(user);
  }

  @GetMapping("/id/{id}")
  @ResponseBody
  public UserDto getUser(@PathVariable Integer id) {
    return userService.getUserById(id);
  }

  @GetMapping("/name/{lastName}")
  public List<UserDto> getUserByName(@PathVariable String lastName) {
    return userService.getUserByName(lastName);
  }
}
