package ru.vsu.csf.starkina.model.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Machine {

    private static Random random;

    private final static HashMap<FigureName, Integer> values = new HashMap<FigureName, Integer>()
    {{
            put(FigureName.VADER, 1000);
            put(FigureName.R2D2, 100);
            put(FigureName.FETT, 10);
            put(FigureName.STORM_TROOPER, 1);
        }};

    private static HashMap<Integer, Integer> combinations = new HashMap<Integer, Integer>()
    {{
            put(3000, 800);
            put(300, 300);
            put(2100, 700);
            put(2010, 600);
            put(2001, 500);
            put(1200, 500);
            put(210, 100);
            put(1020, 200);
            put(1110, 400);
        }};

    private static final ArrayList<FigureName> drum = new ArrayList<FigureName>() {{
        add(FigureName.STORM_TROOPER);
        add(FigureName.STORM_TROOPER);
        add(FigureName.STORM_TROOPER);
        add(FigureName.STORM_TROOPER);
        add(FigureName.VADER);
        add(FigureName.FETT);
        add(FigureName.FETT);
        add(FigureName.FETT);
        add(FigureName.R2D2);
        add(FigureName.R2D2);
    }};

    private FigureName[] screen;
    private int profit;

    public FigureName[] getScreen() {
        return screen;
    }

    public int getProfit() {
        return profit;
    }

    public Machine() {
        screen = new FigureName[]{FigureName.VADER, FigureName.VADER, FigureName.VADER};
        random = new Random();
        profit = 0;
    }

    /**
     * Метод обновления экрана
     */
    public void roll() {
        for (int i = 0; i < screen.length; i++)
            screen[i] = drum.get(random.nextInt(drum.size()));

        int sum = estimate();
        if (combinations.containsKey(sum))
            profit = combinations.get(sum);
        else
            profit = 0;
    }

    private int estimate() {
        int result = 0;
        for (FigureName figureName : screen) {
            result += values.get(figureName);
        }
        return result;
    }
}
