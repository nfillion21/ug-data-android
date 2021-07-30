package pgm.poolp.ugdata.data

import androidx.room.*

data class ChampionWithSkills(
    @Embedded val champion: Champion,
    @Relation(
        parentColumn = "championId",
        entityColumn = "skillId",
        associateBy = Junction(ChampionSkillCrossRef::class)
    )
    val skills: List<Skill>
)
