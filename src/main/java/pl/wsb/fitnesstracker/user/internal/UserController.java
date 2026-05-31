package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User newUser = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(newUser);
        return userMapper.toUserDto(savedUser);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return this.userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserDto> getSimpleUsers() {
        return this.userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .map(dto -> new UserDto(dto.id(), dto.firstName(), dto.lastName(), null, null))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public List<UserDto> getUserByEmailFragment(@RequestParam String email) {
        return userProvider.findUsersByEmailFragment(email).stream()
                .map(userMapper::toUserDto)
                .map(dto -> new UserDto(dto.id(), null, null, null, dto.email()))
                .toList();
    }

    @GetMapping("/older/{date}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate date) {
        return userProvider.findUsersBornBefore(date).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User userUpdateData = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(id, userUpdateData);
        return userMapper.toUserDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}