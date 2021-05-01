package waffle.guam.service

import org.springframework.stereotype.Service
import waffle.guam.db.DevType.DevTypeDTO
import waffle.guam.repository.StackRepository

@Service
class StackService(
    private val stackRepository: StackRepository
) {
    private val searchEngine: SearchEngine = SearchEngine()

    fun init() {
        val stream = this.javaClass.getResourceAsStream("/stacks.csv")
        val reader = java.io.InputStreamReader(stream)
        reader.forEachLine {
            val idx = it.indexOf(",")
            stackRepository.save( (DevTypeDTO( it.dropLast( it.length - (idx+1) ), (it.drop(idx+2)).dropLast(1), "")).toEntity() )
        }
    }

    fun create(o: DevTypeDTO): Boolean{
        stackRepository.save(o.toEntity())
        return true
    }

    fun getAll(): List<DevTypeDTO> {
        val target = stackRepository.findAll()
        return target.map { DevTypeDTO.of(it) }
    }

    // 여러가지 키워드로 검색했을 경우, 검색어마다 OR 처리 -> AND 위주로 보여줌
    // case Insensitive
    fun searchByName(word: String): List<DevTypeDTO> {

        val map = mutableMapOf<DevTypeDTO, Int>()
        val queries = word.split(", ") // 기술 스택에 대한 쿼리 검색어

        val devTypes = getAll()
        for (dev in devTypes) {
            val mappings = dev.mapping.split(", ")
            for (name in mappings) {
                var cnt = 0
                for(q in queries) {
                    // OR 처리, AND 우선 -> cnt 로 구현 가능
                    if(searchEngine.containsQ(name, q) >= 0) cnt++
                }
                if(cnt > 0){
                    map[dev] = cnt
                    break
                }
            }
        }
        return map.toList().sortedWith(compareBy { -it.second }).map {it.first}
    }


}