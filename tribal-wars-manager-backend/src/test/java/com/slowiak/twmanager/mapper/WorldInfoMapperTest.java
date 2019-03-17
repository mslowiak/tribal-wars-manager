package com.slowiak.twmanager.mapper;

import com.slowiak.twmanager.JsoupTestUtils;
import com.slowiak.twmanager.WorldInfoProps;
import com.slowiak.twmanager.model.WorldInfo;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;


class WorldInfoMapperTest {

    @Mock
    private WorldInfoProps worldInfoProps;
    private WorldInfoMapper worldInfoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        worldInfoMapper = new WorldInfoMapper(worldInfoProps);
        given(worldInfoProps.getAllowMorale()).willReturn("Morale");
        given(worldInfoProps.getAllowDestroy()).willReturn("Burzenie");
        given(worldInfoProps.getAllowKnight()).willReturn("Rycerz");
        given(worldInfoProps.getAllowArcher()).willReturn("Łucznicy");
        given(worldInfoProps.getAllowCoins()).willReturn("Monety");
        given(worldInfoProps.getCancelSalesmanTimeSeconds()).willReturn("Czas na cofnięcie handlarza (s.)");
        given(worldInfoProps.getCancelCommandTimeSeconds()).willReturn("Czas na cofnięcie komendy (s.)");
        given(worldInfoProps.getSpeed()).willReturn("Prędkość");
        given(worldInfoProps.getUnitSpeed()).willReturn("Prędkość jednostek");
        given(worldInfoProps.getNewbieProtectionDays()).willReturn("Ochrona początkujących (dni)");
        given(worldInfoProps.getMaxNoblemanRange()).willReturn("Max. odległość dla szlachcica");
        given(worldInfoProps.getTribeMembersLimit()).willReturn("Limit liczby członków plemienia");

    }

    @Test
    void testValidMappingWithAllFields() {
        // given
        String fileName = "resource.txt";
        Document document = JsoupTestUtils.toJsoupDocument(fileName);
        WorldInfo expectedWorldInfo = WorldInfo.builder()
                .allowArcher(true)
                .allowCoins(true)
                .allowDestroy(true)
                .allowKnight(true)
                .allowMorale(true)
                .cancelSalesmanTimeSeconds(300)
                .cancelCommandTimeSeconds(600)
                .speed(2.0)
                .unitSpeed(0.5)
                .newbieProtectionDays(5)
                .maxNoblemanRange(1000)
                .tribeMembersLimit(50)
                .build();

        // when
        WorldInfo worldInfo = worldInfoMapper.toWorldInfoData(document);

        // then
        Assertions.assertEquals(expectedWorldInfo, worldInfo);

    }
}