package study_spring.pingpong.game.model

import study_spring.pingpong.user.model.User
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "game_records")
data class GameRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val score: Int,
    val createdAt: Instant = Instant.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)
