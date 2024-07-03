package com.example.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.api.repository.IssueCouponRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CouponApplyServiceTest {

    @Autowired
    private CouponApplyService couponApplyService;

    @Autowired
    private IssueCouponRepository issueCouponRepository;

    @Test
    @DisplayName("쿠폰 한번만 응모하기")
    public void apply_one_coupon_test() throws InterruptedException {
        //given
        couponApplyService.apply("537af763-22a9-46b6-ba43-80a80e4bf7e5");
        Thread.sleep(10000);
        //when
        long count = issueCouponRepository.count();
        //then
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("쿠폰 여러분 응모하기")
    public void apply_multiple_coupon_test() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i<threadCount; i++) {
            String userId = "4b757d25-1ef6-469e-824c-8540b474046d" + i;
            executorService.submit(() -> {
                try{
                    couponApplyService.apply(userId);
                }finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(10000);

        long count = issueCouponRepository.count();
        assertThat(count).isEqualTo(100);
    }

    @Test
    @DisplayName("한명 당 하나의 쿠폰 발급하기")
    public void apply_one_coupon_per_person_test() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i<threadCount; i++) {
            String userId = "537af763-22a9-46b6-ba43-80a80e4bf7e5";
            executorService.submit(() -> {
                try{
                    couponApplyService.apply(userId);
                }finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(10000);

        long count = issueCouponRepository.count();
        assertThat(count).isEqualTo(1);
    }

}
