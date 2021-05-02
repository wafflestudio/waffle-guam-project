package waffle.guam.db.Project

import waffle.guam.db.DevType.TechStackDTO
import waffle.guam.db.ProjectUser
import java.time.LocalDateTime

data class ProjectReadDTO(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val difficulty: Int = Difficulty.Beginner.ordinal,
    val thumbnail: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val due: LocalDateTime = LocalDateTime.MAX,
    val isRecruiting: Boolean = false,
    // 클라에서 오고 갈 때는 DevType List 로 가지고 있자
    val techStacks: List<TechStackDTO> = ArrayList(),
    val frontends: List<ProjectUser> = ArrayList(),
    val front_left: Int = 0,
    val backends: List<ProjectUser> = ArrayList(),
    val back_left: Int = 0,
    val designers: List<ProjectUser> = ArrayList(),
    val design_left: Int = 0
) {

    companion object {
        fun of(e: Project): ProjectReadDTO {
            val l = e.techStacks.map { TechStackDTO.of( it.stack!! ) }
            return ProjectReadDTO(
                e.id, e.title, e.description, e.difficulty, e.thumbnail, e.createdAt, e.due, e.isRecruiting,
                l, e.frontends, e.front_left, e.backends, e.back_left, e.designers, e.design_left
            )
        }
    }

    fun toEntity() : Project {
        // dev_type 은 service 에서 변환 후에 추가해주면 된다.
        return Project(  title = this.title, description = this.description, difficulty = this.difficulty, thumbnail = this.thumbnail,
            due = this.due, isRecruiting = this.isRecruiting, frontends = this.frontends,
            front_left = this.front_left, backends = this.backends, back_left = this.back_left, designers = this.designers, design_left = this.design_left  )
    }

}
