package waffle.guam.repository.Junctions

import org.springframework.data.jpa.repository.JpaRepository
import waffle.guam.db.ProjectUser

interface ProjectUserRepository: JpaRepository<ProjectUser, Long> {
}