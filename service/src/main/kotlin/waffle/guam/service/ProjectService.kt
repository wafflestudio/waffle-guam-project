package waffle.guam.service

import org.springframework.stereotype.Service
import waffle.guam.db.Project.ProjectCreateDTO
import waffle.guam.db.Project.ProjectDTO
import waffle.guam.db.Project.ProjectUpdateDTO
import waffle.guam.repository.ProjectRepository

@Service
class ProjectService(
    private val projectRepository: ProjectRepository
) {

    fun createProject(projectCreateDTO: ProjectCreateDTO) {
        val done = projectRepository.save(projectCreateDTO.toEntity())
    }

    fun getAllProjects(): List<ProjectDTO>{
        val allProjects = projectRepository.findAll()
        return allProjects.map { it.toProjectDTO() }
    }

    fun findProject(id: Long): ProjectDTO{
        val target = projectRepository.findById(id)
        return target.get().toProjectDTO()
    }

    fun updateProject(id: Long, p: ProjectUpdateDTO): ProjectDTO {
        val target = (projectRepository.findById(id)).get().toProjectDTO()
        return target.update(p)
    }

    fun deleteProject(id: Long){
        projectRepository.deleteById(id)
    }
}