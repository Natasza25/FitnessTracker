package pl.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double weight;

    private Double height;

    @Column(name = "heart_rate")
    private Integer heartRate;
}