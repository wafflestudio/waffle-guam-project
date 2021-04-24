package waffle.guam.service

import org.springframework.stereotype.Service
import waffle.guam.db.User
import waffle.guam.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser() {
        println(userRepository.save(User()).id)
    }
}