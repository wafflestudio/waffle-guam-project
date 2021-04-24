package waffle.guam.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import waffle.guam.db.Project.ProjectCreateDTO
import waffle.guam.db.Project.ProjectUpdateDTO
import waffle.guam.service.ProjectService

@RestController
@RequestMapping()
class ProjectController(
    private val projectService: ProjectService
) {

    @PostMapping("/project")
    fun createProject(@RequestBody projectCreateDTO: ProjectCreateDTO): ResponseEntity<Any>?{
        projectService.createProject(projectCreateDTO)
        return ResponseEntity.ok().body(true)
    }

    @PostMapping("/projects/{id}/update")
    fun deleteProject(@PathVariable id: Long, @RequestBody projectUpdateDTO: Pro jectUpdateDTO): ResponseEntity<Any>? {
        projectService.updateProject(id, projectUpdateDTO)
        return ResponseEntity.ok().body(true)
    }

    @GetMapping("/projects")
    fun getAllProjects(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(projectService.getAllProjects())
    }

    @GetMapping("/projects/{id}")
    fun findProject(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok().body(projectService.findProject(id))
    }

    @PostMapping("/projects/{id}/delete")
    fun updateProject(@PathVariable id: Long): ResponseEntity<Any>? {
        projectService.deleteProject(id)
        return ResponseEntity.ok().body(true)
    }
}.



