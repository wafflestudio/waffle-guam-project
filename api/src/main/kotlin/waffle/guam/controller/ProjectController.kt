package waffle.guam.controller

import org.springframework.web.bind.annotation.*
import waffle.guam.db.Project.*
import waffle.guam.service.ProjectService

@RestController
@RequestMapping()
class ProjectController(
    private val projectService: ProjectService
) {

    // C
    @PostMapping("/project")
    @ResponseBody
    fun createProject(@RequestBody projectDTO: ProjectReadDTO): Boolean{
        return projectService.createProject(projectDTO)
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

    //U
    @PutMapping("/projects/{id}/update")
    @ResponseBody
    fun updateProject(@PathVariable id: Long, @RequestBody projectReadDTO: ProjectReadDTO): ProjectDTO{
        return projectService.updateProject(id, projectReadDTO)
    }

//    @PutMapping("/projects/{id}/addall")
//    @ResponseBody
//    fun addAllDT(@PathVariable id: Long): ProjectDTO{
//        return projectService.addAllDevType(id)
//    }

    //D
    @DeleteMapping("/projects/{id}/delete")
    @ResponseBody
    fun deleteProject(@PathVariable id: Long): Boolean {
        return projectService.deleteProject(id)
    }




}

