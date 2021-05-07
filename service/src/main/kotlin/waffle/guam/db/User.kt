package waffle.guam.db

import javax.persistence.*

@Table(name = "users")
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @OneToMany(mappedBy = "user")
    val front_projectUsers: List<Task> = ArrayList(),
    @OneToMany(mappedBy = "user")
    val back_projectUsers: List<Task> = ArrayList(),
    @OneToMany(mappedBy = "user")
    val design_projectUsers: List<Task> = ArrayList()
)

