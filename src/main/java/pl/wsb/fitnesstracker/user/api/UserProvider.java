package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    Optional<User> getUser(Long userId);

    Optional<User> getUserByEmail(String email);

    List<User> findAllUsers();

    List<User> findUsersByEmailFragment(String emailFragment);

    List<User> findUsersBornBefore(LocalDate date);
}