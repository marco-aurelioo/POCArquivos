package poc.fileio.writeFile.domain.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import poc.fileio.writeFile.domain.entity.BoletoEntity

@Repository
interface BoletoRepository: CrudRepository<BoletoEntity,String> {

    @Query("select b.raw from BoletoEntity b order by b.lineNumber")
    fun findRawField(): List<String>

}