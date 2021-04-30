package waffle.guam.db.Project

import waffle.guam.db.User
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "projects")
@Entity
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    var title: String? = null,
    var description: String? = null,
    var difficulty: Difficulties? = null,
    var thumbnail: String? = null,
    var dev_type: String? = null,
    val createdAt: LocalDateTime? = null,
    @ManyToMany()
    @JoinTable(name = "User_Project")
    var members: List<User> = ArrayList<User>()
) {

    fun toProjectDTO(): ProjectDTO{
        return ProjectDTO(
            id = id,
            title = title,
            description = description,
            difficulty = difficulty,
            thumbnail = thumbnail,
            dev_type = dev_type,
            members = members,
            createdAt = createdAt
        )
    }


}

enum class Difficulties{
    Beginner, Easy, Normal, Intermediate, Master
}

