package waffle.guam.service

import org.springframework.stereotype.Service
import waffle.guam.db.DevType.TechStack
import waffle.guam.db.DevType.TechStackDTO
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
    private val searchEngine: SearchEngine = SearchEngine()

    //C
    fun createProject(p: ProjectReadDTO, l:List<TechStack>): Boolean {
        val new = ProjectDTO().update(p).toEntity()
        val stackList: MutableList<ProjectStack> = ArrayList()
        l.map {
            stackList.add(ProjectStack( project = new, stack = it ))
        }
        new.techStacks.addAll(stackList)
        projectRepository.save(new)
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

    fun searchByKeyword(query: String): List<ProjectReadDTO>{
        val map = mutableMapOf<ProjectReadDTO, Int>()

        val projects = getAllProjects()
        for (p in projects) {
            val a: MutableList<String> = ArrayList()
            a.add(p.title)
            a.add(p.description)
            val cnt = searchEngine.search(a, query)
            if( cnt > 0 ) map[p] = cnt
        }
        return map.toList().sortedWith(compareBy { -it.second }).map {it.first}
    }

    //U
    fun updateProject(id: Long, p: ProjectReadDTO, l: List<TechStack>): ProjectReadDTO {

        val target = (projectRepository.findById(id)).get()
        val new = ProjectDTO.of(target).update(p).toEntity()

        // 포함중인건 포인터만 바꿔
        new.techStacks.filter { l.contains(it.stack) }.map {
            it.project = new
        }

        // 포함 안하는 것중에 l 에 남아있는건 따로 저장
        l.toMutableSet().subtract( new.techStacks.map { it.stack }.toSet()).map {
            new.techStacks.add(ProjectStack( stack = it, project = new ))
        }

        //포함 안하는건 지워줌
        new.techStacks.filter{ !l.contains(it.stack) }.map {
            new.techStacks.remove(it)
            projectStackRepository.deleteById(it.id)
        }
        projectRepository.save(new)
        return p
    }

    //D
    fun deleteProject(id: Long): Boolean{
        projectRepository.deleteById(id)
        return true
    }
}