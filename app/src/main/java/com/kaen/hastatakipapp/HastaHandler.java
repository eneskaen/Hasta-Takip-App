package com.kaen.hastatakipapp;

import com.kaen.hastatakipapp.database.Hasta;

public class HastaHandler {
    private static Hasta hastaInstance;

    public static Hasta getHasta() {
        if (hastaInstance == null) {
            hastaInstance = new Hasta();
        }
        return hastaInstance;
    }

    public static void setHasta(Hasta hasta) {
        hastaInstance = hasta;
    }
}
