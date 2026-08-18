package ms_home_energy_tracker.user_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import ms_home_energy_tracker.user_service.dto.UserDto;
import ms_home_energy_tracker.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        final UserDto userDto = userService.getUserById(userId); // Throws exception if null/not found
        return ResponseEntity.ok(userDto);
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@NotNull @PathVariable Long userId,
                                              @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@NotNull @PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Please Provide a valid user Id", HttpStatus.BAD_REQUEST);
        }


    }
}