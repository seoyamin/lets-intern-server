package com.letsintern.letsintern.domain.program;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.service.ProgramService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/program")
@Tag(name = "Program")
public class ProgramController {

    private final ProgramService programService;

    @Operation(summary = "AWS Target Group 상태 확인용")
    @GetMapping("/tg")
    public ResponseEntity<String> targetGroup() {
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "브랜드 스토리 진행 완료 프로그램 개수")
    @GetMapping("/count")
    public ResponseEntity<Long> getProgramCount() {
        return ResponseEntity.ok(programService.getDoneProgramCount());
    }

    @Operation(summary = "프로그램 목록 (전체, 타입 - CHALLENGE, BOOTCAMP, LETS_CHAT)")
    @GetMapping("")
    public ProgramListDTO getProgramThumbnailList(
            @RequestParam(required = false) String type,
            @PageableDefault(size = 20) Pageable pageable) {
        return programService.getProgramThumbnailList(type, pageable);
    }

    @Operation(summary = "프로그램 1개 상세 보기")
    @GetMapping("/{programId}")
    public ProgramDetailDTO getProgramDetailVo(
            @PathVariable Long programId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return programService.getProgramDetailDTO(programId, principalDetails);
    }

    @Operation(summary = "어드민 프로그램 목록 (전체, 타입, 타입&기수)")
    @GetMapping("/admin")
    public AdminProgramListDTO getAdminProgramList(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer th,
            @PageableDefault(size = 20) Pageable pageable) {
        return programService.getProgramAdminList(type, th, pageable);
    }

    @Operation(summary = "어드민 유저 1명의 프로그램 목록")
    @GetMapping("/admin/user/{userId}")
    public UserProgramVoResponse getAdminUserProgramList(
            @PathVariable Long userId,
            @PageableDefault(size = 20) Pageable pageable) {
        return programService.getAdminUserProgramList(userId, pageable);
    }

    @Operation(summary = "어드민 프로그램 1개 상세 보기")
    @GetMapping("/admin/{programId}")
    public Program getProgram(@PathVariable Long programId) {
        return programService.getProgram(programId);
    }

    @Operation(summary = "어드민 프로그램 신규 개설")
    @PostMapping("")
    public ProgramIdResponseDTO createProgram(
            @RequestBody ProgramCreateRequestDTO programCreateRequestDTO) throws Exception {
        return programService.createProgram(programCreateRequestDTO);
    }

    @Operation(summary = "어드민 프로그램 수정")
    @PatchMapping("/{programId}")
    public ProgramIdResponseDTO updateProgram(@PathVariable Long programId, @RequestBody ProgramUpdateRequestDTO programUpdateRequestDTO) throws ParseException {
        return programService.updateProgram(programId, programUpdateRequestDTO);
    }

    @Operation(summary = "어드민 프로그램 삭제")
    @DeleteMapping("/{programId}")
    public ResponseEntity<?> deleteProgram(@PathVariable Long programId) {
        programService.deleteProgram(programId);
        return ResponseEntity.ok(null);
    }

}
