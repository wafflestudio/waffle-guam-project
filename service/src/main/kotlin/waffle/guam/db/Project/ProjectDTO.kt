package waffle.guam.db.Project

import waffle.guam.db.ProjectUser
import waffle.guam.db.ProjectStack
import java.time.LocalDateTime

data class ProjectDTO(
    var id: Long = 0L,
    var title: String = "",
    var description: String = "",
    var difficulty: Int = Difficulty.Beginner.ordinal,
    var thumbnail: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var due: LocalDateTime = LocalDateTime.MAX,
    var isRecruiting: Boolean = false,
    // Entity 랑 Type 달라짐
    var techStacks: List<ProjectStack> = ArrayList(),
    var frontends: List<ProjectUser> = ArrayList(),
    var front_left: Int = 0,
    var backends: List<ProjectUser> = ArrayList(),
    var back_left: Int = 0,
    var designers: List<ProjectUser> = ArrayList(),
    var design_left: Int = 0
) {
    constructor(p: ProjectReadDTO): this( id = p.id,
        title = p.title, description = p.description, difficulty = p.difficulty,
        thumbnail = p.thumbnail, createdAt = p.createdAt, due = p.due,
        isRecruiting = p.isRecruiting, front_left = p.front_left,
        back_left = p.back_left, design_left = p.design_left
    )

    companion object {
        fun of(e: Project): ProjectDTO {
            val toProjectDev = e.techStacks.map { it.stack }
            return ProjectDTO(
                e.id, e.title, e.description, e.difficulty, e.thumbnail, e.createdAt, e.due, e.isRecruiting,
                (e.techStacks), e.frontends, e.front_left, e.backends, e.back_left, e.designers, e.design_left
            )
        }
    }

    fun toEntity( ) : Project {
        return Project( this.id, this.title, this.description, this.difficulty, this.thumbnail, this.createdAt, this.due, this.isRecruiting,
            this.techStacks, this.frontends, this.front_left, this.backends, this.back_left, this.designers, this.design_left  )
    }

    fun update(p: ProjectReadDTO): ProjectDTO{
            title = p.title
            description = p.description
            difficulty = p.difficulty
            thumbnail = p.thumbnail
            due = p.due
            isRecruiting = p.isRecruiting
            front_left = p.front_left
            back_left = p.back_left
            design_left = p.design_left
            return this
    }

}
