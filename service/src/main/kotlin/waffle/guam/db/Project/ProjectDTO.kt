package waffle.guam.db.Project

import waffle.guam.db.DevType.DevTypeDTO
import waffle.guam.db.ProjectDev
import waffle.guam.db.UserProject
import waffle.guam.db.Project.Project
import java.time.LocalDateTime

class ProjectDTO(
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val difficulty: Int = Difficulty.Beginner.ordinal,
    val thumbnail: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val due: LocalDateTime = LocalDateTime.MAX,
    val isRecruiting: Boolean = false,
    var dev_type: List<ProjectDev> = ArrayList(),
    val frontends: List<UserProject> = ArrayList(),
    val front_left: Int = 0,
    val backends: List<UserProject> = ArrayList(),
    val back_left: Int = 0,
    val designers: List<UserProject> = ArrayList(),
    val design_left: Int = 0
) {

    companion object {
        fun of(e: Project): ProjectDTO {
            return ProjectDTO(
                e.id, e.title, e.description, e.difficulty, e.thumbnail, e.createdAt, e.due, e.isRecruiting,
                e.dev_type, e.frontends, e.front_left, e.backends, e.back_left, e.designers, e.design_left
            )
        }
    }

    constructor(l: List<ProjectDev>) : this() {
        this.dev_type = l
    }

    fun toEntity( ) : Project {
        return Project( this.id, this.title, this.description, this.difficulty, this.thumbnail, this.createdAt, this.due, this.isRecruiting,
            this.dev_type, this.frontends, this.front_left, this.backends, this.back_left, this.designers, this.design_left  )
    }

    fun update(e: ProjectReadDTO): ProjectDTO {
        return ProjectDTO( this.id, e.title, e.description, e.difficulty, e.thumbnail, this.createdAt, e.due, e.isRecruiting,
            e.dev_type, e.frontends, e.front_left, e.backends, e.back_left, e.designers, e.design_left )
    }

    fun addDev(l: List<ProjectDev>): ProjectDTO{
        return ProjectDTO(l)
    }
}
