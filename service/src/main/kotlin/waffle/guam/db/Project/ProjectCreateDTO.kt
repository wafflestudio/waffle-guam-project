package waffle.guam.db.Project

import waffle.guam.db.User
import java.time.LocalDateTime

class ProjectCreateDTO(

    val title: String? = null,
    val description: String? = null,
    val difficulty: Difficulties? = null,
    val thumbnail: String? = null,
    val dev_type: String? = null,
    val members: List<User> = ArrayList<User>()

) {

    fun toEntity(): Project{
        val time = LocalDateTime.now()
        return Project(
            title = title,
            description = description,
            difficulty = difficulty,
            thumbnail = thumbnail,
            dev_type = dev_type,
            members = members,
            createdAt = time
        )
    }
}
