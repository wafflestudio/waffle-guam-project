package waffle.guam.db.Project

import waffle.guam.db.User
import java.time.LocalDateTime

class ProjectReadDTO(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val difficulty: Difficulties? = null,
    val thumbnail: String? = null,
    val dev_type: String? = null,
    val members: List<User> = ArrayList<User>(),
    val createdAt: LocalDateTime? = null

) {

}
