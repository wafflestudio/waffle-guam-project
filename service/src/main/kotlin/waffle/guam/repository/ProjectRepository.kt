package waffle.guam.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import waffle.guam.db.Project.Project

interface ProjectRepository : JpaRepository<Project, Long>{

    @Query(
        value ="SELECT p FROM projects p WHERE p.is_recruiting = true AND (p.headcount_back <=1 OR p.headcount_front <= 1 OR p.headcount_designer <= 1)",
        nativeQuery = true
    )
    fun findImminent(): List<Project>
}
