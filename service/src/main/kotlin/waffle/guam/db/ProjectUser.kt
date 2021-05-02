package waffle.guam.db
import waffle.guam.db.Project.Project
import javax.persistence.*

@Table(name = "UserProject")
@Entity
data class ProjectUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    var project: Project = Project(),
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    var user: User = User(),
    val position: Int = Position.Frontend.ordinal,
)


enum class Position{
    Frontend, Backend, Designer, NULL
}
