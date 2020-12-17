package com.hanzo.starter.kafka.config;

import com.hanzo.starter.kafka.factory.YamlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;

@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:hanzo-kafka.yml")
public class KafkaConfiguration {
}
