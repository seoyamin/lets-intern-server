package com.letsintern.letsintern.domain.banner.domain.popup.maper;

import com.letsintern.letsintern.domain.banner.domain.popup.domain.Popup;
import com.letsintern.letsintern.domain.banner.domain.popup.response.PopupListResponse;
import com.letsintern.letsintern.domain.banner.domain.popup.vo.PopupAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PopupMapper {

    public Popup toEntity(BannerCreateDTO bannerCreateDTO) {
        return Popup.createPopup(bannerCreateDTO);
    }

    public PopupListResponse toPopupListResponse(Page<PopupAdminVo> popupAdminVos) {
        return PopupListResponse.of(
                (popupAdminVos.hasContent()) ? popupAdminVos.getContent() : new ArrayList<>(),
                PageInfo.of(popupAdminVos)
        );
    }

}
