package ms_home_energy_tracker.user_service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String address;
    private boolean alerting;
    private double alertingThreshold;
}
