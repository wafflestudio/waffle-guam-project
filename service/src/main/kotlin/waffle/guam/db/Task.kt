package waffle.guam.db
import waffle.guam.db.Project.Project
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "tasks")
@Entity
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val position: Int = 0,
    val task: String = "Let's get it started!",

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),

    val user_id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    val project: Project = Project(),
)

//  Position 구조
//
//  enum class Position{
//     Frontend, Backend, Designer, NULL
//  }
