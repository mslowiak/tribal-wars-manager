package com.slowiak.twmanager.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorldInfo {
    boolean allowMorale;
    boolean allowDestroy;
    boolean allowKnight;
    boolean allowArcher;
    boolean allowCoins;
    int cancelSalesmanTimeSeconds;
    int cancelCommandTimeSeconds;
    double speed;
    double unitSpeed;
    int newbieProtectionDays;
    int maxNoblemanRange;
    int tribeMembersLimit;
}
