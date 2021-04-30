package waffle.guam.db.Project

import waffle.guam.db.User
import java.time.LocalDateTime

class ProjectDTO(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val difficulty: Difficulties? = null,
    val thumbnail: String? = null,
    val dev_type: String? = null,
    val members: List<User> = ArrayList<User>(),
    val createdAt: LocalDateTime? = null

) {
    fun update(p: ProjectUpdateDTO): ProjectDTO {
        return ProjectDTO( this.id, p.title, p.description, p.difficulty, p.thumbnail, p.dev_type, p.members, this.createdAt )
    }

}
