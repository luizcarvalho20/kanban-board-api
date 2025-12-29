package br.com.dio.board.ui;

import java.util.Scanner;

public final class Input {
    private static final Scanner SC = new Scanner(System.in);

    private Input() {}

    public static String text(String label) {
        System.out.print(label);
        return SC.nextLine();
    }

    public static long longVal(String label) {
        while (true) {
            try {
                System.out.print(label);
                return Long.parseLong(SC.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    public static int intVal(String label) {
        while (true) {
            try {
                System.out.print(label);
                return Integer.parseInt(SC.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
}
