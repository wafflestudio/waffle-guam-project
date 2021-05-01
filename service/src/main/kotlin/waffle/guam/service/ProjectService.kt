package waffle.guam.service

import org.springframework.stereotype.Service
import waffle.guam.db.DevType.DevTypeDTO
import waffle.guam.db.Project.ProjectDTO
import waffle.guam.db.Project.ProjectReadDTO
import waffle.guam.repository.ProjectRepository
import waffle.guam.repository.StackRepository

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val stackService: StackService
) {
    //C
    fun createProject(projectDTO: ProjectReadDTO): Boolean {
        projectRepository.save(projectDTO.toEntity())
        return true
    }

    //R
    fun getAllProjects(): List<ProjectReadDTO>{
        val allProjects = projectRepository.findAll()
        return allProjects.map { ProjectReadDTO.of(it) }
    }

    fun findProject(id: Long): ProjectReadDTO{
        val target = projectRepository.findById(id).get()
        return ProjectReadDTO.of(target)
    }

    //U
    fun updateProject(id: Long, p: ProjectReadDTO): ProjectDTO {
        val target = (projectRepository.findById(id)).get()
        val res = ProjectDTO.of(target).update(p)
        projectRepository.save(res.toEntity())
        return res
    }

    // for test
    // add all devType available, return added project
//    fun addAllDevType(id: Long): ProjectDTO {
//        val target = projectRepository.findById(id).get()
//        val list: List<DevTypeDTO> = stackService.getAll()
//        return projectRepository.save(ProjectDTO.of(target).addDev(list))
//    }

    //D
    fun deleteProject(id: Long): Boolean{
        projectRepository.deleteById(id)
        return true;
    }
}