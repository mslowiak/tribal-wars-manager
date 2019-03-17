package com.slowiak.twmanager;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:world-info-mapper.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "wip")
@Data
public class WorldInfoProps {
    String allowMorale;
    String allowDestroy;
    String allowKnight;
    String allowArcher;
    String allowCoins;
    String cancelSalesmanTimeSeconds;
    String cancelCommandTimeSeconds;
    String speed;
    String unitSpeed;
    String newbieProtectionDays;
    String maxNoblemanRange;
    String tribeMembersLimit;
}