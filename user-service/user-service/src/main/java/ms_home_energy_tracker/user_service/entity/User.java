package ms_home_energy_tracker.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Builder.Default
    @Column(nullable = false)
    private Boolean alerting = false;

    @Builder.Default
    @Column(name = "energy_alerting_threshold", nullable = false)
    private Double alertingThreshold = 0.0;
}
