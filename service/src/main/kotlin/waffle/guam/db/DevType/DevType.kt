package waffle.guam.db.DevType

import javax.persistence.*

@Table(name = "DevType")
@Entity
data class DevType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val type: String = "",
    val mapping: String = "",
    val thumbnail: String = ""
){


}
