package com.slowiak.twmanager.controller;

import com.slowiak.twmanager.WorldInfoProps;
import com.slowiak.twmanager.model.WorldInfo;
import com.slowiak.twmanager.service.WorldInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/world-info")
public class WorldInfoController {
    private final WorldInfoService worldInfoService;
    private final WorldInfoProps worldInfoProps;

    @GetMapping("/{worldId}")
    public Mono<WorldInfo> getWorldInfo(@PathVariable("worldId") String worldId) {
        System.out.println(worldInfoProps.getAllowArcher());
        return worldInfoService.getWorldInfo(worldId);
    }
}
