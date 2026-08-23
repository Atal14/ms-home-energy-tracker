package ms_home_energy_tracker.device_service.entity;

import jakarta.persistence.*;
import lombok.*;
import ms_home_energy_tracker.device_service.model.DeviceType;

@Entity
@Table(name = "device")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DeviceType type;

    @Column(length = 100)
    private String location;

    @Column(name = "user_id", nullable = false)
    private Long userId;

}
