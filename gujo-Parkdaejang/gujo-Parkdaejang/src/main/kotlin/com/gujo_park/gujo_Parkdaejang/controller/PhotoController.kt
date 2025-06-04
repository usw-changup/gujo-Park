package com.gujo_park.gujo_Parkdaejang.controller

import com.gujo_park.gujo_Parkdaejang.entity.PhotoEntity
import com.gujo_park.gujo_Parkdaejang.repository.PhotoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Paths

@RestController
@RequestMapping("/admin")
class PhotoController(
    private val photoRepository: PhotoRepository
) {

//    @CrossOrigin(origins = ["http://127.0.0.1:5500"], allowCredentials = "true")
    @PostMapping("/uploadPhoto")
    fun uploadPhoto(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        if (file.isEmpty) {
            return ResponseEntity.badRequest().body("파일이 비어있어요.")
        }
        println("서버의 작업 디렉토리: " + System.getProperty("user.dir"))

        // 저장할 경로와 파일명
        val fileName = file.originalFilename ?: "unnamed"
        val uploadDir = "C:\\Users\\binac\\Documents\\학교 활동\\usw-changup\\gujo-Parkdaejang\\gujo-Parkdaejang\\uploads"
        val savePath = Paths.get(uploadDir, fileName).toFile()

        // 실제 파일 저장
        file.transferTo(savePath)

        // ✅ DB에 파일 정보 저장
        val photo = PhotoEntity()
        photo.fileName = fileName
        photo.filePath = savePath.absolutePath
        photoRepository.save(photo)

        println("저장 경로: ${savePath.absolutePath}")

        val savedPhoto = photoRepository.save(photo)
        println("DB 저장된 사진 ID: ${savedPhoto.id}, 파일명: ${savedPhoto.fileName}")


        try {
            file.transferTo(savePath)
            println("✅ 파일 저장 성공: ${savePath.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
            println("❌ 파일 저장 중 오류 발생: ${e.message}")
        }

        return ResponseEntity.ok("파일 업로드 및 DB 저장 완료!")
    }
}
