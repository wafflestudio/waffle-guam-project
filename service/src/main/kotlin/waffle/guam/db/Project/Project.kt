package waffle.guam.db.Project

import org.springframework.data.annotation.CreatedDate
import waffle.guam.db.ProjectStack
import waffle.guam.db.Task
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "projects")
@Entity
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    val title: String = "",

    val description: String = "",

    val thumbnail: String = "",

    val front_left: Int = 0,
    val back_left: Int = 0,
    val design_left: Int = 0,

    val isRecruiting: Boolean = false,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany( orphanRemoval = true, mappedBy = "project", cascade = [CascadeType.ALL])
    val techStacks: MutableList<ProjectStack> = ArrayList(),

    @OneToMany( orphanRemoval = true, mappedBy = "project", cascade = [CascadeType.ALL])
    val members: MutableList<Task> = ArrayList()
) {

}
