package waffle.guam.db

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import waffle.guam.db.DevType.TechStack
import waffle.guam.db.Project.Project
import javax.persistence.*

@Table(name = "DevProject")
@Entity
data class ProjectStack(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "PROJECT_ID_2")
    var project: Project? = null,
    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "DEV_ID" )
    var stack: TechStack? = null
){

}