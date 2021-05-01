package waffle.guam.repository

import org.springframework.data.jpa.repository.JpaRepository
import waffle.guam.db.DevType.DevType

interface StackRepository: JpaRepository<DevType, Long> {
}