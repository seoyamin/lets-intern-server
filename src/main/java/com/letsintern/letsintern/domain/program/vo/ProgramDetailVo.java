package com.letsintern.letsintern.domain.program.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.program.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProgramDetailVo {
    private ProgramStatus status;
    private String title;
    private String contents;
    private String notice;
    private ProgramType type;
    private ProgramWay way;
    private String location;
    private ProgramTopic topic;
    private ProgramFeeType feeType;
    private LocalDateTime dueDate;
    private LocalDateTime announcementDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer feeRefund;
    private Integer feeCharge;
    private Integer discountValue;

    @JsonIgnore
    private String faqListStr;

    @Builder
    public ProgramDetailVo(ProgramStatus status, String title, String contents, String notice,
                           ProgramType type, ProgramWay way, String location, ProgramTopic topic, ProgramFeeType feeType, String faqListStr,
                           LocalDateTime dueDate, LocalDateTime announcementDate, LocalDateTime startDate, LocalDateTime endDate,
                           Integer feeRefund, Integer feeCharge, Integer discountValue) {
        this.status = status;
        this.title = title;
        this.contents = contents;
        this.notice = notice;
        this.type = type;
        this.way = way;
        this.location = location;
        this.topic = topic;
        this.feeType = feeType;
        this.faqListStr = faqListStr;
        this.dueDate = dueDate;
        this.announcementDate = announcementDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.feeRefund = feeRefund;
        this.feeCharge = feeCharge;
        this.discountValue = discountValue;
    }
}
