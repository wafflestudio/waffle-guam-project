package waffle.guam.controller

import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import waffle.guam.db.DevType.DevTypeDTO
import waffle.guam.db.Project.ProjectDTO
import waffle.guam.db.Project.ProjectReadDTO
import waffle.guam.service.StackService

@Controller
class StackController(
    private val stackService: StackService
) {

    @GetMapping("/stackinit")
    @ResponseBody
    fun createInitialStack(){
        stackService.init()
    }

    // C
    @PostMapping("/stack")
    @ResponseBody
    fun createStack(@RequestBody devTypeDTO: DevTypeDTO): Boolean{
        return stackService.create(devTypeDTO)
    }

    //R
    @GetMapping("/stacks")
    @ResponseBody
    fun getAllProjects(): List<DevTypeDTO> {
        return stackService.getAll()
    }

    // ** Hashtag Search, Filters DevType

    @GetMapping("/hashtag")
    @ResponseBody
    fun findByName(@RequestParam word: String): List<DevTypeDTO> {
        return stackService.searchByName(word)
    }

    //U
    //D
}