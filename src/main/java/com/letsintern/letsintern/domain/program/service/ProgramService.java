package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;

    private final ProgramRepository programRepository;


    @Transactional
    public ProgramIdResponseDTO createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        return programMapper.toProgramIdResponseDTO(programHelper.createProgram(programCreateRequestDTO));
    }

    @Transactional
    public ProgramIdResponseDTO updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) {
        return programMapper.toProgramIdResponseDTO(programHelper.updateProgram(programId, programUpdateRequestDTO));
    }

    @Transactional
    public ProgramListDTO getProgramList(Pageable pageable) {
        return programHelper.getProgramList(pageable);
    }

    @Transactional
    public ProgramListDTO getProgramTypeList(String type, Pageable pageable) {
        return programHelper.getProgramTypeList(type, pageable);
    }

    public ProgramDetailVo getProgramDetailVo(Long programId) {
        return programHelper.getProgramDetailVo(programId);
    }

    public Program getProgram(Long programId) {
        return checkExistingProgram(programId);
    }

    public void deleteProgram(Long programId) {
        Program program = checkExistingProgram(programId);
        programRepository.delete(program);
    }

    private Program checkExistingProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        return program;
    }
}
