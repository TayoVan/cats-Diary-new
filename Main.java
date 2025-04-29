import java.util.Scanner;

public class Main {
    static final int MAX_RECORDS = 50;
    static String[] dates = new String[MAX_RECORDS];
    static String[] times = new String[MAX_RECORDS];
    static char[][] texts = new char[MAX_RECORDS][1000];
    static int recordCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Щоденник котика .");

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
            System.out.print("Введіть дату запису аби котик зрозумів що робити (у форматі РРРР-ММ-ДД): ");
            String date = scanner.nextLine();

            if (!isValidDate(date)) {
                System.out.println("Некоректний формат дати. Котик збентежений. Спробуйте ще раз.");
                return;
            }

            System.out.print("Введіть час запису (у форматі HH:MM:SS): ");
            String time = scanner.nextLine();

            if (!isValidTime(time)) {
                System.out.println("Некоректний формат часу. Котик розгубився. Спробуйте ще раз.");
                return;
            }

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

            dates[recordCount] = date;
            times[recordCount] = time;
            recordCount++;
            System.out.println("Запис успішно додано. Це як залишити теплий слід лапки в пам’яті.");
        } else {
            System.out.println("Котик заповнив щоденник на максимум. Видаліть запис, щоб додати новий, котик прийняв забагато інформації.");
        }
    }

    static void deleteEntry(Scanner scanner) {
        System.out.print("Введіть дату запису, аби котив знав що потрібно видалити: ");
        String dateToDelete = scanner.nextLine();
        System.out.print("Введіть час запису, аби котив знав що потрібно видалити: ");
        String timeToDelete = scanner.nextLine();
        for (int i = 0; i < recordCount; i++) {
            if (dateToDelete.equals(dates[i]) && timeToDelete.equals(times[i])) {
                for (int j = i; j < recordCount - 1; j++) {
                    dates[j] = dates[j + 1];
                    times[j] = times[j + 1];
                    System.arraycopy(texts[j + 1], 0, texts[j], 0, texts[j + 1].length);
                }
                dates[recordCount - 1] = null;
                times[recordCount - 1] = null;
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
            if (dates[i] != null) {
                System.out.println("Дата: " + dates[i] + " Час: " + times[i]);
                System.out.println(new String(texts[i]).trim());
                empty = false;
            }
        }

        if (empty) {
            System.out.println("Записів поки що немає. Але котики вірять у вас.");
        }
    }

    static boolean isValidDate(String date) {
        if (date.length() != 10) return false;
        if (date.charAt(4) != '-' || date.charAt(7) != '-') return false;

        for (int i = 0; i < date.length(); i++) {
            if (i == 4 || i == 7) continue;
            if (date.charAt(i) < '0' || date.charAt(i) > '9') return false;
        }
        return true;
    }

    static boolean isValidTime(String time) {
        if (time.length() != 8) return false;
        if (time.charAt(2) != ':' || time.charAt(5) != ':') return false;

        for (int i = 0; i < time.length(); i++) {
            if (i == 2 || i == 5) continue;
            if (time.charAt(i) < '0' || time.charAt(i) > '9') return false;
        }
        return true;
    }
}