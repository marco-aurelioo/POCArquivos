package poc.fileio.writeFile.domain.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class BoletoEntity(
    @Id
    val id: String,
    val cnabFileId: String,
    val lineNumber: Int,
    val type: String,
    val raw: String,
    val structured: String
)