package controller;

import model.CalculatorModel;
import model.HistoryManager;
import view.CalculatorView;
import java.util.Scanner;

public class CalculatorController {
    private final CalculatorModel model = new CalculatorModel();
    private final CalculatorView view = new CalculatorView();
    private final HistoryManager historyManager = new HistoryManager();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            view.printMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Введите уравнение: ");
                    String input = scanner.nextLine();
                    try {
                        double result = model.evaluate(input);
                        System.out.println("Результат: " + result);
                        historyManager.addToHistory(input, result);
                    } catch (IllegalArgumentException | ArithmeticException e) {
                        System.out.println("Ошибка вычисления: " + e.getMessage());
                    }
                    break;

                case "2":
                    view.printHistory(historyManager.getHistory());
                    break;

                case "3":
                    System.out.println("Выберите номера выражений через запятую (например, 1,3):");
                    view.printHistory(historyManager.getHistory());
                    System.out.print("→ ");
                    String[] selections = scanner.nextLine().split(",");
                    System.out.print("Введите имя или путь к файлу (или оставьте пустым): ");
                    String path = scanner.nextLine();
                    historyManager.saveSelectedToFile(selections, path.trim());
                    break;

                case "4":
                    historyManager.saveHistory(); // сохранить перед выходом
                    running = false;
                    break;

                default:
                    System.out.println("Некорректная опция.");
            }
        }
    }
}
