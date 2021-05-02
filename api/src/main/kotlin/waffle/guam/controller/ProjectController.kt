package waffle.guam.controller

import org.springframework.web.bind.annotation.*
import waffle.guam.db.DevType.TechStack
import waffle.guam.db.DevType.TechStackDTO
import waffle.guam.db.Project.*
import waffle.guam.service.ProjectService
import waffle.guam.service.StackService

@RestController
@RequestMapping()
class ProjectController(
    private val projectService: ProjectService,
    private val stackService: StackService
) {

    // C
    @PostMapping("/project")
    @ResponseBody
    fun createProject(@RequestBody projectReadDTO: ProjectReadDTO): Boolean{
        val devIdList = projectReadDTO.techStacks.map { stackService.searchIdByDTO(it) }
        val devList = devIdList.map {stackService.searchById(it) }
        return projectService.createProject(projectReadDTO, devList)
    }

    //R
    @GetMapping("/projects")
    @ResponseBody
    fun getAllProjects(): List<ProjectReadDTO> {
        return projectService.getAllProjects()
    }

    @GetMapping("/projects/{id}")
    @ResponseBody
    fun findProject(@PathVariable id: Long): ProjectReadDTO {
        return projectService.findProject(id)
    }

    // Filters : Left_Position ( _f _b _d  ) -> maybe OHE can be used, Stacks, Due?
    @GetMapping("/projects/search")
    @ResponseBody
    fun searchProject(@RequestParam query: String, @RequestBody(required=false) body: Pair<Int, List<TechStackDTO>>): List<ProjectReadDTO> {
        return projectService.searchByKeyword(query)
    }

    //U
    @PutMapping("/projects/{id}")
    @ResponseBody
    fun updateProject(@PathVariable id: Long, @RequestBody projectReadDTO: ProjectReadDTO): ProjectReadDTO{
        val devIdList = projectReadDTO.techStacks.map { stackService.searchIdByDTO(it) }
        val devList = devIdList.map {stackService.searchById(it) }
        return projectService.updateProject(id, projectReadDTO, devList)
    }

    //D
    @DeleteMapping("/projects/{id}")
    @ResponseBody
    fun deleteProject(@PathVariable id: Long): Boolean {
        return projectService.deleteProject(id)
    }

}

