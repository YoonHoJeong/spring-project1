package study_spring.pingpong.user.model

import study_spring.pingpong.game.model.GameRecord
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val username: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val gameRecords: List<GameRecord> = emptyList()
)
