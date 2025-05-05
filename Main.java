import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static final int MAX_RECORDS = 50;
    static LocalDateTime[] datesTimes = new LocalDateTime[MAX_RECORDS];
    static char[][] texts = new char[MAX_RECORDS][1000];
    static int recordCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Щоденник котика ПЕРЕРОБЛЕНО.");

        while (running) {
            System.out.println("\nОберіть дію яку зроблять котики :");
            System.out.println("1 - Котик додасть запис");
            System.out.println("2 - Котик видалить запис");
            System.out.println("3 - Переглянути всі записи котиків");
            System.out.println("0 - Вийти");
            System.out.print("Оберіть дію котика: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEntry(scanner);
                    break;
                case "2":
                    deleteEntry(scanner);
                    break;
                case "3":
                    showEntries();
                    break;
                case "0":
                    running = false;
                    System.out.println("До побачення! Нехай ваші спогади залишаються теплими, як пухнастий котик на колінах.");
                    break;
                default:
                    System.out.println("Котик не знає що робити, таких команд ми ще не знаємо(.");
            }
        }

        scanner.close();
    }

    static void addEntry(Scanner scanner) {
        if (recordCount < MAX_RECORDS) {
            int year = 0;
            int month = 0;
            int day = 0;
            int hour = 0;
            int minute = 0;
            int second = 0;
            boolean validInput;

            do {
                validInput = true;
                System.out.print("Введіть рік (РРРР): ");
                if (scanner.hasNextInt()) {
                    year = scanner.nextInt();
                } else {
                    System.out.println("Некоректний формат введення року. Спробуйте ще раз, вводячи ціле число.");
                    scanner.next();
                    validInput = false;
                }
                System.out.print("Введіть місяць (ММ): ");
                if (scanner.hasNextInt()) {
                    month = scanner.nextInt();
                } else {
                    System.out.println("Некоректний формат введення місяця. Спробуйте ще раз, вводячи ціле число.");
                    scanner.next();
                    validInput = false;
                }
                System.out.print("Введіть день (ДД): ");
                if (scanner.hasNextInt()) {
                    day = scanner.nextInt();
                } else {
                    System.out.println("Некоректний формат введення дня. Спробуйте ще раз, вводячи ціле число.");
                    scanner.next();
                    validInput = false;
                }
                System.out.print("Введіть годину (гг): ");
                if (scanner.hasNextInt()) {
                    hour = scanner.nextInt();
                } else {
                    System.out.println("Некоректний формат введення години. Спробуйте ще раз, вводячи ціле число.");
                    scanner.next();
                    validInput = false;
                }
                System.out.print("Введіть хвилини (хх): ");
                if (scanner.hasNextInt()) {
                    minute = scanner.nextInt();
                } else {
                    System.out.println("Некоректний формат введення хвилин. Спробуйте ще раз, вводячи ціле число.");
                    scanner.next();
                    validInput = false;
                }
                System.out.print("Введіть секунди (сс): ");
                if (scanner.hasNextInt()) {
                    second = scanner.nextInt();
                } else {
                    System.out.println("Некоректний формат введення секунд. Спробуйте ще раз, вводячи ціле число.");
                    scanner.next();
                    validInput = false;
                }
                scanner.nextLine();
                if (validInput && (year < 1900 || year > 2100 || month < 1 || month > 12 || day < 1 || day > 31 || hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59)) {
                    System.out.println("Введені значення дати або часу є некоректними. Будь ласка, перевірте їх.");
                    validInput = false;
                }
            } while (!validInput);

            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second);

            System.out.println("Введіть текст запису аби котик записав ващі думки (для завершення натисніть Enter на порожньому рядку):");
            String line;
            int charIndex = 0;
            while (true) {
                line = scanner.nextLine();
                if (line.isEmpty()) break;
                for (int i = 0; i < line.length(); i++) {
                    if (charIndex < 1000 - 1) {
                        texts[recordCount][charIndex++] = line.charAt(i);
                    } else {
                        System.out.println("Запис занадто довгий. Котик не може все запам'ятати.");
                        return;
                    }
                }
                if (charIndex < 1000 - 1) {
                    texts[recordCount][charIndex++] = '\n';
                }
            }
            texts[recordCount][charIndex] = '\0';

            datesTimes[recordCount] = dateTime;
            recordCount++;
            System.out.println("Запис успішно додано. Це як залишити теплий слід лапки в пам’яті.");
        } else {
            System.out.println("Котик заповнив щоденник на максимум. Видаліть запис, щоб додати новий, котик прийняв забагато інформації.");
        }
    }

    static void deleteEntry(Scanner scanner) {
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        boolean validInput;

        do {
            validInput = true;
            System.out.print("Введіть рік запису для видалення (РРРР): ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
            } else {
                System.out.println("Некоректний формат введення року. Спробуйте ще раз, вводячи ціле число.");
                scanner.next();
                validInput = false;
            }
            System.out.print("Введіть місяць (ММ): ");
            if (scanner.hasNextInt()) {
                month = scanner.nextInt();
            } else {
                System.out.println("Некоректний формат введення місяця. Спробуйте ще раз, вводячи ціле число.");
                scanner.next();
                validInput = false;
            }
            System.out.print("Введіть день (ДД): ");
            if (scanner.hasNextInt()) {
                day = scanner.nextInt();
            } else {
                System.out.println("Некоректний формат введення дня. Спробуйте ще раз, вводячи ціле число.");
                scanner.next();
                validInput = false;
            }
            System.out.print("Введіть годину (гг): ");
            if (scanner.hasNextInt()) {
                hour = scanner.nextInt();
            } else {
                System.out.println("Некоректний формат введення години. Спробуйте ще раз, вводячи ціле число.");
                scanner.next();
                validInput = false;
            }
            System.out.print("Введіть хвилини (хх): ");
            if (scanner.hasNextInt()) {
                minute = scanner.nextInt();
            } else {
                System.out.println("Некоректний формат введення хвилин. Спробуйте ще раз, вводячи ціле число.");
                scanner.next();
                validInput = false;
            }
            System.out.print("Введіть секунди (сс): ");
            if (scanner.hasNextInt()) {
                second = scanner.nextInt();
            } else {
                System.out.println("Некоректний формат введення секунд. Спробуйте ще раз, вводячи ціле число.");
                scanner.next();
                validInput = false;
            }
            scanner.nextLine();
            if (validInput && (year < 1900 || year > 2100 || month < 1 || month > 12 || day < 1 || day > 31 || hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59)) {
                System.out.println("Введені значення дати або часу є некоректними. Будь ласка, перевірте їх.");
                validInput = false;
            }
        } while (!validInput);

        LocalDateTime dateTimeToDelete = LocalDateTime.of(year, month, day, hour, minute, second);

        for (int i = 0; i < recordCount; i++) {
            if (dateTimeToDelete.equals(datesTimes[i])) {
                for (int j = i; j < recordCount - 1; j++) {
                    datesTimes[j] = datesTimes[j + 1];
                    System.arraycopy(texts[j + 1], 0, texts[j], 0, texts[j + 1].length);
                }
                datesTimes[recordCount - 1] = null;
                texts[recordCount - 1] = new char[1000];
                recordCount--;
                System.out.println("Запис видалено. Іноді потрібно залишити місце для нових муркотінь.");
                return;
            }
        }

        System.out.println("Котик не знайшов запис за вказаною датою та часом.");
    }

    static void showEntries() {
        boolean empty = true;
        System.out.println("Ваші записи:");

        for (int i = 0; i < recordCount; i++) {
            if (datesTimes[i] != null) {
                System.out.println("Дата та час: " + datesTimes[i]);
                System.out.println(new String(texts[i]).trim());
                empty = false;
            }
        }

        if (empty) {
            System.out.println("Записів поки що немає. Але котики вірять у вас.");
        }
    }
}
