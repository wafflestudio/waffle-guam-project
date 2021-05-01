package waffle.guam.db.Project

import waffle.guam.db.ProjectDev
import waffle.guam.db.User
import waffle.guam.db.UserProject
import java.time.LocalDateTime

class ProjectReadDTO(

    val title: String = "",
    val description: String = "",
    val difficulty: Int = Difficulty.Beginner.ordinal,
    val thumbnail: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val due: LocalDateTime = LocalDateTime.MAX,
    val isRecruiting: Boolean = false,
    val dev_type: List<ProjectDev> = ArrayList(),
    val frontends: List<UserProject> = ArrayList(),
    val front_left: Int = 0,
    val backends: List<UserProject> = ArrayList(),
    val back_left: Int = 0,
    val designers: List<UserProject> = ArrayList(),
    val design_left: Int = 0
) {

    companion object {
        fun of(e: Project): ProjectReadDTO {
            return ProjectReadDTO(
                e.title, e.description, e.difficulty, e.thumbnail, e.createdAt, e.due, e.isRecruiting,
                e.dev_type, e.frontends, e.front_left, e.backends, e.back_left, e.designers, e.design_left
            )
        }
    }

    fun toEntity() : Project {
        return Project(  title = this.title, description = this.description, difficulty = this.difficulty, thumbnail = this.thumbnail, createdAt = this.createdAt,
            due = this.due, isRecruiting = this.isRecruiting, dev_type = this.dev_type, frontends = this.frontends,
            front_left = this.front_left, backends = this.backends, back_left = this.back_left, designers = this.designers, design_left = this.design_left  )
    }

}
