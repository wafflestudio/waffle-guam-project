package waffle.guam.service

import org.springframework.stereotype.Service
import waffle.guam.db.DevType.TechStack
import waffle.guam.db.Project.ProjectDTO
import waffle.guam.db.Project.ProjectReadDTO
import waffle.guam.db.ProjectStack
import waffle.guam.repository.Junctions.ProjectStackRepository
import waffle.guam.repository.ProjectRepository

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val projectStackRepository: ProjectStackRepository
) {
    //C
    fun createProject(p: ProjectReadDTO, l:List<TechStack>): Boolean {
        l.map {
            val devs = ProjectStack( project = ProjectDTO().update(p).toEntity(), stack = it )
            projectStackRepository.save(devs)
        }
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
    fun updateProject(id: Long, p: ProjectReadDTO, l: List<TechStack>): ProjectReadDTO {

        val target = (projectRepository.findById(id)).get()
        val new = ProjectDTO.of(target).update(p)

        println(l)

        // 포함중인건 포인터만 바꿔
        new.techStacks.filter { l.contains(it.stack) }.map {
            println("업데이트할 엔티티: ${it.stack}")
            it.project = new.toEntity()
            projectStackRepository.save(it)
        }

        l.toMutableSet().subtract( new.techStacks.map { it.stack }.toSet()).map {
            // 포함 안하는 것중에 l 에 남아있는건 따로 저장해줘야 함
            println("새로 만들 엔티티: ${it}")
            projectStackRepository.save(ProjectStack( stack = it, project = new.toEntity() ))
        }

        //포함 안하는건 지워
        new.techStacks.filter{ !l.contains(it.stack) }.map {
            println("쳐 지울 엔티티: ${it.stack}")
            projectStackRepository.deleteById(it.id)
        }
        return p
    }

    //D
    fun deleteProject(id: Long): Boolean{
        projectRepository.deleteById(id)
        return true
    }
}