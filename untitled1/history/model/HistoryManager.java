package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private final List<String> history = new ArrayList<>();
    private final File defaultHistoryFile = new File("history.log");

    public HistoryManager() {
        loadHistory();
    }

    public void addToHistory(String expression, double result) {
        history.add(expression + " = " + result);
    }

    public List<String> getHistory() {
        return history;
    }

    public void loadHistory() {
        if (defaultHistoryFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(defaultHistoryFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    history.add(line);
                }
            } catch (IOException e) {
                System.out.println("Ошибка при загрузке истории.");
            }
        }
    }

    public void saveHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(defaultHistoryFile))) {
            for (String entry : history) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении истории.");
        }
    }

    public void saveSelectedToFile(String[] selections, String userInput) {
        List<String> selected = new ArrayList<>();

        for (String indexStr : selections) {
            try {
                int index = Integer.parseInt(indexStr.trim()) - 1;
                if (index >= 0 && index < history.size()) {
                    selected.add(history.get(index));
                }
            } catch (NumberFormatException ignored) {}
        }

        if (selected.isEmpty()) {
            System.out.println("Ничего не выбрано для сохранения.");
            return;
        }

        File outputFile;

        if (userInput.isEmpty()) {
            outputFile = defaultHistoryFile;
        } else if (userInput.matches(".*\\.(txt|log|md)$")) {
            outputFile = new File(userInput);
        } else if (new File(userInput).isDirectory()) {
            outputFile = new File(userInput + File.separator + "log.log");
        } else {
            outputFile = new File(userInput);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : selected) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Сохранено в файл: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
