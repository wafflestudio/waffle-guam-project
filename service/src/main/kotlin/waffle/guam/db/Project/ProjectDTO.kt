package waffle.guam.db.Project

import waffle.guam.db.DevType.TechStack
import waffle.guam.db.DevType.TechStackDTO
import waffle.guam.db.Task
import waffle.guam.db.ProjectStack
import java.time.LocalDateTime

data class ProjectDTO(
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
    constructor(p: ProjectReadDTO): this( id = p.id,
        title = p.title, description = p.description,
        thumbnail = p.thumbnail, createdAt = p.createdAt,
        isRecruiting = p.isRecruiting, front_left = p.front_left,
        back_left = p.back_left, design_left = p.design_left
    )

    companion object {
        fun of(e: Project): ProjectDTO {
            val l = e.techStacks.map { TechStackDTO.of( it.stack!! ) }
            return ProjectDTO(
                e.id, e.title, e.description, e.thumbnail, e.front_left, e.back_left, e.design_left,
                e.isRecruiting, e.createdAt, e.modifiedAt, l.toMutableList(), e.members
            )
        }
    }

    fun toEntity( ) : Project {
        return Project( this.id, this.title, this.description, this.thumbnail, this.front_left,
            this.back_left, this.design_left,
            this.isRecruiting, this.createdAt, this.modifiedAt )
    }

    fun update(p: ProjectReadDTO): ProjectDTO{
            title = p.title
            description = p.description
            thumbnail = p.thumbnail
            isRecruiting = p.isRecruiting
            front_left = p.front_left
            back_left = p.back_left
            design_left = p.design_left
            return this
    }

}
