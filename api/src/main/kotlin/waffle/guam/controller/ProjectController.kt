package waffle.guam.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import waffle.guam.db.Project.*
import waffle.guam.service.ProjectService

@RestController
@RequestMapping()
class ProjectController(
    private val projectService: ProjectService
) {

    @PostMapping("/project")
    @ResponseBody
    fun createProject(@RequestBody projectCreateDTO: ProjectCreateDTO){
        return projectService.createProject(projectCreateDTO)
    }

    @PostMapping("/projects/{id}/update")
    @ResponseBody
    fun deleteProject(@PathVariable id: Long, @RequestBody projectUpdateDTO: ProjectUpdateDTO): ProjectDTO{
        return projectService.updateProject(id, projectUpdateDTO)
    }

    @GetMapping("/projects")
    @ResponseBody
    fun getAllProjects(): List<ProjectDTO> {
        return projectService.getAllProjects()
    }

    @GetMapping("/projects/{id}")
    @ResponseBody
    fun findProject(@PathVariable id: Long): ProjectDTO {
        return projectService.findProject(id)
    }

    @PostMapping("/projects/{id}/delete")
    @ResponseBody
    fun updateProject(@PathVariable id: Long) {
        return projectService.deleteProject(id)
    }
}

