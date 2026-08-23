package ms_home_energy_tracker.device_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ms_home_energy_tracker.device_service.model.DeviceType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "DeviceType is required")
    private DeviceType type;

    private String location;

    @NotNull(message = "userId is required")
    private Long userId;
}
