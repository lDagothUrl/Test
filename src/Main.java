import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<MonthData> monthData = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            char count = ' ';
            String target = "0";
            while (true) {
                printMenu();
                count = scanner.next().charAt(0);
                switch (count) {
                    case '1':
                        while (true) {
                            System.out.print("Введите количества шагов в день: ");
                            try {
                                target = scanner.next();
                                if (Integer.parseInt(target) <= 0) {
                                    System.out.println("Количество шагов не может быть отрицательным или равно 0.");
                                } else if (Integer.parseInt(target) > 0) {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.printf("Это не целое число %S.\n", target);
                            }
                        }
                        break;
                    case '2':
                        monthData.add(new MonthData(scanner));
                        break;
                    case '3':
                        String yearNumber = "";
                        String monthNumber = "";
                        int numberTarget = 0;
                        double allStepMonth = 0;
                        while (true) {
                            try {
                                System.out.println("Введите год: ");
                                yearNumber = scanner.next();
                                break;
                            } catch (Exception e) {
                                System.out.println("Введено не целое число.");
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите месяц от 1 до 12: ");
                                monthNumber = scanner.next();
                                if (Integer.parseInt(monthNumber) > 12 || Integer.parseInt(monthNumber) < 1) {
                                    System.out.println("Месяцев всего от 1 до 12.");
                                }
                                else {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("Введено не целое число.");
                            }
                        }
                        for(MonthData date : monthData){
                            if(date.getYear() == Integer.parseInt(yearNumber) && date.getMonth() == Integer.parseInt(monthNumber)){
                                System.out.println(date.getLocalDate() + ": прошли шагов " + date.getStep() +
                                        ", потратели килокалорий " + ((double) date.getStep())*0.05 +
                                        " и прошли дистанцию " + ((double) date.getStep())*75/100_000 + " км.");
                                allStepMonth += date.getStep();
                                if (date.getStep() >= Integer.parseInt(target)){
                                    numberTarget++;
                                }
                            }
                        }
                        System.out.println("В этот месяц вы достигли цели: " + numberTarget +
                                " раз и потратели килокалорий " + allStepMonth*0.05 +
                                ", пройдена дистанция " + allStepMonth*75/100_000 + " км.");
                        break;
                    case '4':
                        if (target.equals("")) {
                            System.out.println("Вы еще не задали цель на день.");
                        } else {
                            System.out.printf("Ваша цель на день: %S.\n", target);
                        }
                        break;
                    case '5':
                        while (true) {
                            System.out.print("Введите количества шагов: ");
                            String flag = "";
                            double parsFlag;
                            try {
                                flag = scanner.next();
                                parsFlag = Integer.parseInt(flag);
                                if (parsFlag < 0) {
                                    System.out.println("Количество шагов не может быть отрицательным.");
                                } else if (parsFlag >= 0) {
                                    System.out.printf("За %S шагов: потратите %S килокалорий и пройдете дистанцию %S км.\n"
                                            , flag, parsFlag * 0.05, (parsFlag * 75) / 100_000);
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.printf("Это не целое число %S.\n", flag);
                            }
                        }
                        break;
                    case '0':
                        System.out.println("Выход.");
                        return;
                    default:
                        System.out.println("Вы ввели не число.");
                        break;
                }
            }
        }
    }

    static void printMenu() {
        System.out.print("\nВведите 1 для: постановки цели по количества шагов в день.\n" +
                "Введите 2 для: ввода пройденного количества шагов за день.\n" +
                "Введите 3 для: просмотра статистики за определенный месяц.\n" +
                "Введите 4 для: просмотра цели на день.\n" +
                "Введите 5 для: перевода шагов в калории.\n" +
                "Введите 0 для: выхода из программы.\n");
    }
}
