package com.gujo_park.gujo_Parkdaejang.controller

import com.gujo_park.gujo_Parkdaejang.dto.FormDto
import com.gujo_park.gujo_Parkdaejang.entity.FormEntity
import com.gujo_park.gujo_Parkdaejang.repository.FormRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FormController(
    private val formRepository: FormRepository
) {

    // 1) 신청 저장 (로그인 없이 가능)
    @PostMapping("/apply")
    fun saveForm(@RequestBody formDto: FormDto): ResponseEntity<String> {
        val entity: FormEntity = formDto.toEntity()
        formRepository.save(entity)
        return ResponseEntity.ok("신청이 저장되었습니다.")
    }

    // 2) 관리자만 목록 조회
    @GetMapping("/admin/applications")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAllApplications(): ResponseEntity<List<FormEntity>> {
        val all = formRepository.findAll()
        return ResponseEntity.ok(all)
    }
}
