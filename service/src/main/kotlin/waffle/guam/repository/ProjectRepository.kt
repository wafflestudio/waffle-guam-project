package waffle.guam.repository

import org.springframework.data.jpa.repository.JpaRepository
import waffle.guam.db.Project.Project

interface ProjectRepository : JpaRepository<Project, Long>{

}