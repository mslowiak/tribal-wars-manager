package com.slowiak.twmanager.mapper;

import com.slowiak.twmanager.WorldInfoProps;
import com.slowiak.twmanager.model.WorldInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WorldInfoMapper {
    private WorldInfoProps worldInfoProps;
    private Map<String, String> properties;

    public WorldInfoMapper(WorldInfoProps worldInfoProps) {
        this.worldInfoProps = worldInfoProps;
        this.properties = new HashMap<>();
    }

    public WorldInfo toWorldInfoData(Document document) {
        Element table = document.selectFirst("table.widget");
        Elements tableTrs = table.select("tr");
        for (Element tableTr : tableTrs) {
            Elements tds = tableTr.select("td");
            if (tds.size() == 2) {
                Element elementDescription = tds.get(0);
                Element elementValue = tds.get(1);
                properties.putIfAbsent(elementDescription.text(), elementValue.text());
            }
        }
        System.out.println("XD: " + properties.get(worldInfoProps.getCancelSalesmanTimeSeconds()));
        return WorldInfo.builder()
                .allowMorale(toBoolean(properties.get(worldInfoProps.getAllowMorale())))
                .allowDestroy(toBoolean(properties.get(worldInfoProps.getAllowDestroy())))
                .allowKnight(toBoolean(properties.get(worldInfoProps.getAllowKnight())))
                .allowArcher(toBoolean(properties.get(worldInfoProps.getAllowArcher())))
                .allowCoins(toBoolean(properties.get(worldInfoProps.getAllowCoins())))
                .cancelSalesmanTimeSeconds(Integer.parseInt(properties.get(worldInfoProps.getCancelSalesmanTimeSeconds())))
                .cancelCommandTimeSeconds(Integer.parseInt(properties.get(worldInfoProps.getCancelCommandTimeSeconds())))
                .speed(Double.parseDouble(properties.get(worldInfoProps.getSpeed())))
                .unitSpeed(Double.parseDouble(properties.get(worldInfoProps.getUnitSpeed())))
                .newbieProtectionDays(Integer.parseInt(properties.get(worldInfoProps.getNewbieProtectionDays())))
                .maxNoblemanRange(Integer.parseInt(properties.get(worldInfoProps.getMaxNoblemanRange())))
                .tribeMembersLimit(Integer.parseInt(properties.get(worldInfoProps.getTribeMembersLimit())))
                .build();
    }

    private boolean toBoolean(String word) {
        if (word.equals("Tak")) {
            return true;
        } else if (word.equals("Nie")) {
            return false;
        }
        throw new IllegalStateException("Cannot convert " + word + " to boolean");
    }
}
