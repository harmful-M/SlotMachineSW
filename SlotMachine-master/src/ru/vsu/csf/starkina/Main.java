package ru.vsu.csf.starkina;

import ru.vsu.csf.starkina.model.Game;
import ru.vsu.csf.starkina.model.ProfileManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s;
        do {
            s = sc.nextLine();
            System.out.println(Game.getInstance().click(2,3));
        } while (!s.equals("2"));
    }
}
