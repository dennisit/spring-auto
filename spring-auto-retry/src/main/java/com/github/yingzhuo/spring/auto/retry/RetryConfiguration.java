package com.github.yingzhuo.spring.auto.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@EnableRetry
@Configuration
@ConditionalOnClass(RetryTemplate.class)
@ConditionalOnProperty(name = "spring.auto.retry.enabled", havingValue = "true", matchIfMissing = false)
public class RetryConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryConfiguration.class);

    public RetryConfiguration() {
        LOGGER.debug("spring-auto: '{}' enabled.", RetryConfiguration.class.getSimpleName());
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(2000l);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);

        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }
}
