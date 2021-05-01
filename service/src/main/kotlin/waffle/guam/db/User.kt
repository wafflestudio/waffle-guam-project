package waffle.guam.db

import waffle.guam.db.Project.Project
import javax.persistence.*

@Table(name = "users")
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @OneToMany(mappedBy = "user")
    val front_projects: List<UserProject> = ArrayList(),
    @OneToMany(mappedBy = "user")
    val back_projects: List<UserProject> = ArrayList(),
    @OneToMany(mappedBy = "user")
    val design_projects: List<UserProject> = ArrayList()
)

