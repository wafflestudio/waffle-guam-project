package waffle.guam.repository

import org.springframework.data.jpa.repository.JpaRepository
import waffle.guam.db.User

interface UserRepository : JpaRepository<User, Long>