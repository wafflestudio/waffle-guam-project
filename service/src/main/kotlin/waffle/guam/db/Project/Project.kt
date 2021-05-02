package waffle.guam.db.Project

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import waffle.guam.db.ProjectStack
import waffle.guam.db.ProjectUser
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

    @OneToMany( orphanRemoval = true, mappedBy = "project", cascade = [CascadeType.ALL])
    val techStacks: List<ProjectStack> = ArrayList(),

    @OneToMany( orphanRemoval = true, cascade = [CascadeType.ALL], mappedBy = "project")
    val frontends: List<ProjectUser> = ArrayList(),
    val front_left: Int = 0,

    @OneToMany( orphanRemoval = true, cascade = [CascadeType.ALL], mappedBy = "project")
    val backends: List<ProjectUser> = ArrayList(),
    val back_left: Int = 0,

    @OneToMany( orphanRemoval = true, cascade = [CascadeType.ALL], mappedBy = "project")
    val designers: List<ProjectUser> = ArrayList(),
    val design_left: Int = 0
) {

}

enum class Difficulty{
    Beginner, Easy, Normal, Intermediate, Master
}

