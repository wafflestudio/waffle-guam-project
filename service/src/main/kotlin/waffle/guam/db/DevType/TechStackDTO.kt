package waffle.guam.db.DevType

data class TechStackDTO (
    val type: String = "",
    val mapping: String = "",
    val thumbnail: String = ""
){
    companion object{
        fun of(e: TechStack): TechStackDTO{
            return TechStackDTO(e.type, e.mapping, e.thumbnail )
        }
    }

    fun toEntity(): TechStack {
        return TechStack( type = type, mapping = mapping, thumbnail = thumbnail )
    }
}