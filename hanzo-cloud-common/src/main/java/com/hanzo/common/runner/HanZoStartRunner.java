package com.hanzo.common.runner;

import com.hanzo.common.util.HanZoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author thy
 * @Date 2020/9/28 11:27
 * @Description:
 */
@Component
@RequiredArgsConstructor
public class HanZoStartRunner implements ApplicationRunner {

    private final ConfigurableApplicationContext context;
    private final Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (context.isActive()) {
            HanZoUtil.printSystemUpBanner(environment);
        }
    }
}
