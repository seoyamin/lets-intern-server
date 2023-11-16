package com.letsintern.letsintern.domain.program.vo;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class ProgramThumbnailVo {

    private Long id;

    private ProgramStatus status;

    @Enumerated(EnumType.STRING)
    private ProgramType type;

    private Integer th;

    private String title;

    private String dueDate;

    private String startDate;

    @QueryProjection
    public ProgramThumbnailVo(Long id, ProgramStatus status, ProgramType type, Integer th, String title, Date dueDate, String startDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        this.id = id;
        this.status = status;
        this.type = type;
        this.th = th;
        this.title = title;
        this.dueDate = simpleDateFormat.format(dueDate);
        this.startDate = startDate;
    }
}