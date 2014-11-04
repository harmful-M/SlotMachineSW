package ru.vsu.csf.starkina.model.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Machine {

    private static Random random;

    private final static HashMap<FigureName, Integer> values = new HashMap<FigureName, Integer>()
    {{
            put(FigureName.SQUARE, 1000);
            put(FigureName.TRIANGLE, 100);
            put(FigureName.LINE, 10);
            put(FigureName.DOT, 1);
        }};

    private static HashMap<Integer, Integer> combinations = new HashMap<Integer, Integer>()
    {{
            put(3000, 10);
            put(300, 5);
            put(30, 1);
            put(2100, 10);
            put(3, 2);
            put(1101, 4);
            put(1100, 4);
        }};

    private static final ArrayList<FigureName> drum = new ArrayList<FigureName>() {{
        add(FigureName.DOT);
        add(FigureName.DOT);
        add(FigureName.DOT);
        add(FigureName.DOT);
        add(FigureName.SQUARE);
        add(FigureName.LINE);
        add(FigureName.LINE);
        add(FigureName.LINE);
        add(FigureName.TRIANGLE);
        add(FigureName.TRIANGLE);
    }};

    private FigureName[] screen;

    public FigureName[] getScreen() {
        return screen;
    }

    public Machine() {
        screen = new FigureName[3];
        random = new Random();
    }

    /**
     * Метод обновления экрана
     * @return Количество выигранных монет
     */
    public int roll() {
        for (int i = 0; i < screen.length; i++)
            screen[i] = drum.get(random.nextInt(drum.size()));

        int sum = estimate();
        if (combinations.containsKey(sum))
            return combinations.get(sum);
        return 0;
    }

    private int estimate() {
        int result = 0;
        for (FigureName figureName : screen) {
            result += values.get(figureName);
        }
        return result;
    }
}
