package waffle.guam.db.DevType

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class DevTypeDTO (
    val type: String = "",
    val mapping: String = "",
    val thumbnail: String = ""
){
    companion object{
        fun of(e: DevType): DevTypeDTO{
            return DevTypeDTO(e.type, e.mapping, e.thumbnail )
        }
    }

    fun toEntity(): DevType {
        return DevType( type = type, mapping = mapping, thumbnail = thumbnail )
    }
}