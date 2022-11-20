package br.com.tiagopedroso.dioparkingchallenge.tool;

/**
 * Class imported from https://github.com/supertsp/livraria-online-api
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderedMapHandler {

    private OrderedMapHandler() {}

    public static <T> Map create(T... chavesValores) {
        if (chavesValores.length == 0 || chavesValores.length % 2 != 0) return new LinkedHashMap<>();

        final var map = new LinkedHashMap<>();

        for (int index = 0; index < chavesValores.length; index += 2) {
            map.put(chavesValores[index], chavesValores[index + 1]);
        }

        return map;
    }

}
