package br.com.tiagopedroso.dioparkingchallenge.tool;

import java.util.Objects;
import java.util.UUID;

public class Uuid {

    private Uuid() {
        super();
    }

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean equals(String uuid1, String uuid2) {
        return Objects.equals(uuid1, uuid2);
    }

}
