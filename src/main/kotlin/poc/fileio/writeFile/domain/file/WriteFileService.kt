package poc.fileio.writeFile.domain.file

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import poc.fileio.writeFile.domain.repository.BoletoPaginationRepository
import poc.fileio.writeFile.domain.repository.BoletoRepository
import poc.fileio.writeFile.domain.repository.BoletoStreamRepository
import java.io.*

import java.nio.file.*


@Service
class WriteFileService(val boletoRepository: BoletoRepository,
val boletoStreamRepository: BoletoStreamRepository,
val boletoPaginationRepository: BoletoPaginationRepository) {

    var logger: Logger = LoggerFactory.getLogger("Logger")

    fun createBoletoFile(): File{
        var file = File("tmpfile.txt")
        file.createNewFile()
        // contexto
        var timeIni = System.currentTimeMillis()
        var lines = boletoRepository.findAll()
        logger.info("v1 tempo consulta:${System.currentTimeMillis() - timeIni} ms")

        var timeIniFile = System.currentTimeMillis()
        val fos = FileOutputStream(file)
        val bw = BufferedWriter(OutputStreamWriter(fos))
        for (i in lines) {
            bw.write(i.raw)
            bw.newLine()
        }
        bw.close()
        logger.info("v1 tempo construcao arquivo:${System.currentTimeMillis() - timeIniFile} ms")

        //
        return file
    }

    @Transactional(readOnly = true)
    fun createBoletoFileV2(): File{

        var file = File("tmpfile.txt")
        var timeIni = System.currentTimeMillis()
        var lines = boletoStreamRepository.findAll()
        logger.info("v2 tempo consulta:${System.currentTimeMillis() - timeIni} ms")
        var timeIniFile = System.currentTimeMillis()
        try {
            val fos = FileOutputStream(file)
            val bw = BufferedWriter(OutputStreamWriter(fos))
            lines.forEach {
                bw.write(it.raw)
                bw.newLine()
            }
            bw.close()
        } catch (x: IOException) {
            System.err.println(x)
        }
        logger.info("v2 tempo construcao arquivo:${System.currentTimeMillis() - timeIniFile} ms")
        return file
    }

    fun createBoletoFileV3(): File{

        var file = File("tmpfile.txt")

        var timeIniFile = System.currentTimeMillis()
        try {
            var size = 50000
            var page = 0

            val fos = FileOutputStream(file)
            val bw = BufferedWriter(OutputStreamWriter(fos))
            do {
                var pageable = PageRequest.of(page,size)
                var lines = boletoPaginationRepository.findAll(pageable)
                lines.forEach {
                    bw.write(it.raw)
                    bw.newLine()
                }
                page++
            }while (!lines.isLast)
            bw.close()
        } catch (x: IOException) {
            System.err.println(x)
        }
        logger.info("v3 tempo construcao arquivo:${System.currentTimeMillis() - timeIniFile} ms")
        return file
    }

    fun createBoletoFileV4(): File{
        var file = File("tmpfile.txt")
        var timeIniFile = System.currentTimeMillis()
        try {
            var size = 50000
            var page = 0
            val fos = FileOutputStream(file)
            val bw = BufferedWriter(OutputStreamWriter(fos),8192 * 4 )
            do {
                var pageable = PageRequest.of(page,size)
                var lines = boletoPaginationRepository.findAll(pageable)
                lines.forEach {
                    bw.write(it.raw)
                    bw.newLine()
                }
                page++
            }while (!lines.isLast)
            bw.close()
        } catch (x: IOException) {
            System.err.println(x)
        }
        logger.info("v4 tempo construcao arquivo:${System.currentTimeMillis() - timeIniFile} ms")
        return file
    }


    @Transactional(readOnly = true)
    fun createBoletoFileV5(): File{

        var file = File("tmpfile.txt")
        var timeIni = System.currentTimeMillis()
        var lines = boletoRepository.findRawField()
        logger.info("v5 tempo consulta:${System.currentTimeMillis() - timeIni} ms")
        var timeIniFile = System.currentTimeMillis()
        try {
            val fos = FileOutputStream(file)
            val bw = BufferedWriter(OutputStreamWriter(fos))
            lines.forEach {
                bw.write(it)
                bw.newLine()
            }
            bw.close()
        } catch (x: IOException) {
            System.err.println(x)
        }
        logger.info("v5 tempo construcao arquivo:${System.currentTimeMillis() - timeIniFile} ms")
        return file
    }


    fun createBoletoFileV6(): File{
        var file = File("tmpfile.txt")
        var timeIniFile = System.currentTimeMillis()
        try {
            var size = 50000
            var page = 0
            val fos = FileOutputStream(file)
            val bw = BufferedWriter(OutputStreamWriter(fos),8192 * 4)
            do {
                var pageable = PageRequest.of(page,size).withSort(Sort.by("lineNumber"))
                var lines = boletoPaginationRepository.findRawField(pageable)
                lines.forEach {
                    bw.write(it)
                    bw.newLine()
                }
                page++
            }while (!lines.isLast)
            bw.close()
        } catch (x: IOException) {
            System.err.println(x)
        }
        logger.info("v6 tempo construcao arquivo:${System.currentTimeMillis() - timeIniFile} ms")
        return file
    }


    fun createBoletoFileV7(): File{
        var file = File("tmpfile.txt")
        file.createNewFile()
        var timeIniFile = System.currentTimeMillis()
        try {
            var size = 50000
            var page = 0
            val bw = BufferedWriter(FileWriter("tmpfile.txt",false),8192 * 4)
            do {
                var pageable = PageRequest.of(page,size).withSort(Sort.by("lineNumber"))
                var lines = boletoPaginationRepository.findRawField(pageable)
                lines.forEach {
                    bw.write(it)
                    bw.newLine()
                }
                page++
            }while (!lines.isLast)
            bw.close()
        } catch (x: IOException) {
            System.err.println(x)
        }

        logger.info("v6 tempo construcao arquivo:${System.currentTimeMillis() - timeIniFile} ms")
        return file
    }


}