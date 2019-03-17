package com.slowiak.twmanager.service;

import com.slowiak.twmanager.mapper.WorldInfoMapper;
import com.slowiak.twmanager.model.WorldInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
public class WorldInfoService {
    private final static String WORLD_INFO_URL = "http://pl.twstats.com/{%s}/index.php?page=settings";
    private Map<String, WorldInfo> worldInfoCacheMap = new HashMap<>();
    private WorldInfoMapper worldInfoMapper;
    private WebClient webClient;

    public WorldInfoService(WorldInfoMapper worldInfoMapper) {
        this.worldInfoMapper = worldInfoMapper;
        webClient = WebClient.create();
    }

    public Mono<WorldInfo> getWorldInfo(String worldId) {
        return Mono.just(worldId)
                .map(id -> Optional.ofNullable(worldInfoCacheMap.getOrDefault(id, null)))
                .flatMap(mono -> mono.map(Mono::just).orElse(performWorldInfoGet(worldId)));
    }

    private Mono<WorldInfo> performWorldInfoGet(String worldId) {
        return webClient.get().uri(WORLD_INFO_URL, worldId)
                .retrieve()
                .bodyToMono(String.class)
                .map(html -> worldInfoMapper.toWorldInfoData(Jsoup.parse(html)))
                .doOnSuccess(worldInfo -> worldInfoCacheMap.putIfAbsent(worldId, worldInfo))
                .doOnError(error -> log.error(format("Cannot get world info from URL %s", error.getMessage())));
    }
}
