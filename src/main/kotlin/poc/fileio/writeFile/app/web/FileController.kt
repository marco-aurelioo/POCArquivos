package poc.fileio.writeFile.app.web

import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import poc.fileio.writeFile.domain.file.WriteFileService
import java.io.FileInputStream


@RestController
class FileController(var writeFileService: WriteFileService) {

    @GetMapping("/file")
    fun getFileBoleto(): ResponseEntity<Resource>{
        var file = writeFileService.createBoletoFile()
        val i = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(i);
    }

    @GetMapping("/file2")
    fun getFileBoleto2(): ResponseEntity<Resource>{
        var file = writeFileService.createBoletoFileV2()
        val i = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(i);
    }

    @GetMapping("/file3")
    fun getFileBoleto3(): ResponseEntity<Resource>{
        var file = writeFileService.createBoletoFileV3()
        val i = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(i);
    }

    @GetMapping("/file4")
    fun getFileBoleto4(): ResponseEntity<Resource>{
        var file = writeFileService.createBoletoFileV4()
        val i = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(i);
    }

    @GetMapping("/file5")
    fun getFileBoleto5(): ResponseEntity<Resource>{
        var file = writeFileService.createBoletoFileV4()
        val i = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(i);
    }

    @GetMapping("/file6")
    fun getFileBoleto6(): ResponseEntity<Resource>{
        var file = writeFileService.createBoletoFileV6()
        val i = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(i);
    }

    @GetMapping("/file7")
    fun getFileBoleto7(): ResponseEntity<Resource>{
        var file = writeFileService.createBoletoFileV7()
        val i = InputStreamResource(FileInputStream(file))
        return ResponseEntity.ok().contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(i);
    }

}