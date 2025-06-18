package view;

public class CalculatorView {
    public void printMenu() {
        System.out.println("=== Калькулятор MVC ===");
        System.out.println("1. Ввести уравнение");
        System.out.println("2. Показать историю");
        System.out.println("3. Сохранить выборочно историю");
        System.out.println("4. Выход");
        System.out.print("Выберите опцию: ");
    }

    public void printHistory(java.util.List<String> history) {
        if (history.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ") " + history.get(i));
            }
        }
    }
}