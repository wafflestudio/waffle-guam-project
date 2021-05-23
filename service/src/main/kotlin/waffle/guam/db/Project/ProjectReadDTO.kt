package waffle.guam.db.Project

import waffle.guam.db.DevType.TechStack
import waffle.guam.db.DevType.TechStackDTO
import waffle.guam.db.ProjectStack
import waffle.guam.db.Task
import java.time.LocalDateTime

// DTO for reading & passing information
data class ProjectReadDTO(
    var id: Long = 0L,
    var title: String = "",
    var description: String = "",

    var thumbnail: String = "",

    var front_left: Int = 0,
    var back_left: Int = 0,
    var design_left: Int = 0,

    var isRecruiting: Boolean = false,

    var createdAt: LocalDateTime = LocalDateTime.now(),
    var modifiedAt: LocalDateTime = LocalDateTime.now(),

    // Entity 랑 Type 달라짐
    var techStacks: MutableList<TechStackDTO> = ArrayList(),
    val members: MutableList<Task> = ArrayList(),
) {

    companion object {
        fun of(e: Project): ProjectReadDTO {
            val l = e.techStacks.map { TechStackDTO.of( it.stack!! ) }
            return ProjectReadDTO(
                e.id, e.title, e.description, e.thumbnail, e.front_left, e.back_left, e.design_left,
                e.isRecruiting, e.createdAt, e.modifiedAt, l.toMutableList(), e.members
            )
        }
    }

}
