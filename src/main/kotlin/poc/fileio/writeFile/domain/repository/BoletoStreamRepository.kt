package poc.fileio.writeFile.domain.repository

import org.springframework.data.repository.Repository
import poc.fileio.writeFile.domain.entity.BoletoEntity
import java.util.stream.Stream

@org.springframework.stereotype.Repository
interface BoletoStreamRepository: Repository<BoletoEntity,String> {

    fun findAll(): Stream<BoletoEntity>

}