package waffle.guam.db

import waffle.guam.db.Project.Project
import javax.persistence.*

@Table(name = "users")
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @ManyToMany(mappedBy = "members")
    val projects: List<Project>? = ArrayList<Project>()
)

