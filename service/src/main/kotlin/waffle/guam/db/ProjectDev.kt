package waffle.guam.db

import waffle.guam.db.Project.Project
import javax.persistence.*

@Table(name = "DevProject")
@Entity
data class ProjectDev(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @ManyToOne()
    @JoinColumn(name = "id", insertable = false, updatable = false )
    val project: Project = Project(),
    @ManyToOne()
    @JoinColumn(name = "id", insertable = false, updatable = false )
    val user: User = User()
){

}