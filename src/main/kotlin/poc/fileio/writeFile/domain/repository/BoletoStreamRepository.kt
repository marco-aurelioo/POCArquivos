package poc.fileio.writeFile.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import poc.fileio.writeFile.domain.entity.BoletoEntity
import java.util.stream.Stream

@org.springframework.stereotype.Repository
interface BoletoStreamRepository: JpaRepository<BoletoEntity,String> {

    @Query("select b.raw from BoletoEntity b")
    fun streamBoleto() : Stream<String>

}