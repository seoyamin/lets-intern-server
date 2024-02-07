package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long>, ApplicationRepositoryCustom {

    Optional<Application> findById(Long id);

    List<Application> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved);

    Integer countAllByProgramIdAndStatus(Long programId, ApplicationStatus status);

}
