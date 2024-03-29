package com.letsintern.letsintern.domain.coupon;

import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponAllResponseDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponApplyResponseDto;
import com.letsintern.letsintern.domain.coupon.service.CouponService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Coupon")
@RequiredArgsConstructor
@RequestMapping("/coupon")
@RestController
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<CouponAllResponseDto> getCouponsForAdmin(@PageableDefault(size = 10) final Pageable pageable) {
        final CouponAllResponseDto responseDto = couponService.getCoupons(pageable);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CouponApplyResponseDto> getAvailableCoupon(@AuthenticationPrincipal final PrincipalDetails principalDetails,
                                                                     @PathVariable("code") final String code) {
        final CouponApplyResponseDto responseDto = couponService.getAvailableCoupon(principalDetails, code);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public void createNewCouponForAdmin(@RequestBody final BaseCouponRequestDto baseCouponRequestDto) {
        couponService.createNewCoupon(baseCouponRequestDto);
    }

    @PatchMapping("/{id}")
    public void updateCouponInfoForAdmin(@PathVariable("id") final Long couponId,
                                 @RequestBody final BaseCouponRequestDto baseCouponRequestDto) {
        couponService.updateCouponInfo(couponId, baseCouponRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCouponForAdmin(@PathVariable("id") final Long couponId) {
        couponService.deleteCoupon(couponId);
    }
}
