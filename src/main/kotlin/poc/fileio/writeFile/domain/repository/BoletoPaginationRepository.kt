package poc.fileio.writeFile.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import poc.fileio.writeFile.domain.entity.BoletoEntity
import java.awt.print.Pageable

@Repository
interface BoletoPaginationRepository: PagingAndSortingRepository<BoletoEntity,String> {

    @Query(
        value = "select b.raw from BoletoEntity b",
        countQuery = "select count(b.raw) from BoletoEntity b"
    )
    fun findRawField(page: PageRequest): Page<String>

}