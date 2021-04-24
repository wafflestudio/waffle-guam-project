package waffle.guam.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import waffle.guam.service.UserService

@RestController
class UserController(
    private val userService: UserService
) {

    @GetMapping("user")
    fun createUser() {
        userService.createUser()
    }
}