package waffle.guam.db.Project

import org.springframework.data.annotation.CreatedDate
import waffle.guam.db.ProjectDev
import waffle.guam.db.UserProject
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "projects")
@Entity
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "title")
    val title: String = "",

    @Column(name = "description")
    val description: String = "",

    @Column(name = "difficulty")
    val difficulty: Int = Difficulty.Beginner.ordinal,

    @Column(name = "thumbnail")
    val thumbnail: String = "",

    @CreatedDate
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "due")
    val due: LocalDateTime = LocalDateTime.MAX,
    val isRecruiting: Boolean = false,

    @OneToMany( orphanRemoval = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "project")
    val dev_type: List<ProjectDev> = ArrayList(),

    @OneToMany( orphanRemoval = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "project")
    val frontends: List<UserProject> = ArrayList(),
    val front_left: Int = 0,

    @OneToMany( orphanRemoval = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "project")
    val backends: List<UserProject> = ArrayList(),
    val back_left: Int = 0,

    @OneToMany( orphanRemoval = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "project")
    val designers: List<UserProject> = ArrayList(),
    val design_left: Int = 0
) {

}

enum class Difficulty{
    Beginner, Easy, Normal, Intermediate, Master
}

