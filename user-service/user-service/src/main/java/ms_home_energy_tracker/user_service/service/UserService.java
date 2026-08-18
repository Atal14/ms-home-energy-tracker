package ms_home_energy_tracker.user_service.service;

import lombok.extern.slf4j.Slf4j;
import ms_home_energy_tracker.user_service.dto.UserDto;
import ms_home_energy_tracker.user_service.entity.User;
import ms_home_energy_tracker.user_service.exception.ResourceNotFoundException;
import ms_home_energy_tracker.user_service.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(this::getUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("No User Found with Id: " + userId));
    }

    public UserDto createUser(UserDto newUser) {
        final User createdUser = User.builder()
                .name(newUser.getName())
                .surname(newUser.getSurname())
                .email(newUser.getEmail())
                .address(newUser.getAddress())
                .alerting(newUser.isAlerting())
                .alertingThreshold(newUser.getAlertingThreshold())
                .build();

        final User savedUser = userRepository.save(createdUser);
        return getUserDto(savedUser);
    }

    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        BeanUtils.copyProperties(updatedUserDto, user, "id");

        User savedUser = userRepository.save(user);
        return getUserDto(savedUser);
    }

    public void deleteUser(Long userId ) {
        userRepository.deleteById(userId);
    }

    private UserDto getUserDto(User user) {
        final UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(user, savedUserDto);
        return savedUserDto;
    }
}
