package com.letsintern.letsintern.domain.mission.service;

import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.mission.dto.response.MissionAdminListResponse;
import com.letsintern.letsintern.domain.mission.dto.response.MissionIdResponse;
import com.letsintern.letsintern.domain.mission.helper.MissionHelper;
import com.letsintern.letsintern.domain.mission.mapper.MissionMapper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionHelper missionHelper;
    private final MissionMapper missionMapper;

    @Transactional
    public MissionIdResponse createMission(Long programId, MissionCreateDTO missionCreateDTO) {
        return missionMapper.toMissionIdResponse(missionHelper.createMission(programId, missionCreateDTO));
    }

    @Transactional(readOnly = true)
    public MissionAdminListResponse getMissionAdminList(Long programId, Pageable pageable) {
        return missionHelper.getMissionAdminList(programId, pageable);
    }

    @Transactional(readOnly = true)
    public Object getMissionDetail(Long missionId, MissionDashboardListStatus status, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return missionHelper.getMissionDetail(missionId, status, user.getId());
    }
}
