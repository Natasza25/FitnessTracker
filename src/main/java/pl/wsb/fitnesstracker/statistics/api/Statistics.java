package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
// Zaimportuj swoją klasę User - upewnij się, że ścieżka jest poprawna
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics") // Małe litery w nazwach tabel są dobrą praktyką
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    // --- DODAJ TO POLE ---
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    // ---------------------

    private int totalTrainings;

    private double totalDistance;

    private int totalCaloriesBurned;

    // Zaktualizuj konstruktor, aby przyjmował użytkownika
    public Statistics(User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}