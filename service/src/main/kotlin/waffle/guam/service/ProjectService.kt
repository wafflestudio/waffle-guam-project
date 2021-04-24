package waffle.guam.service

import org.springframework.stereotype.Service
import waffle.guam.db.Project.ProjectCreateDTO
import waffle.guam.db.Project.ProjectReadDTO
import waffle.guam.db.Project.ProjectUpdateDTO
import waffle.guam.repository.ProjectRepository

@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {

    fun createProject(projectCreateDTO: ProjectCreateDTO): ProjectCreateDTO {
        val done = projectRepository.save(projectCreateDTO.toEntity())
        return done.toProjectDTO()
    }

    fun getAllProjects(): List<ProjectReadDTO>{
        val allProjects = projectRepository.findAll()
        return allProjects.map { it.toProjectReadDTO() }
    }

    fun findProject(id: Long): ProjectCreateDTO{
        val target = projectRepository.findById(id)
        return target.get().toProjectDTO()
    }

    fun updateProject(id: Long, p: ProjectUpdateDTO){
        val target = (projectRepository.findById(id)).get()
        target.title = p.title
        target.description = p.description
        target.dev_type = p.dev_type
        target.difficulty = p.difficulty
        target.thumbnail = p.thumbnail
        projectRepository.flush();
    }

    fun deleteProject(id: Long){
        projectRepository.deleteById(id)
    }
}