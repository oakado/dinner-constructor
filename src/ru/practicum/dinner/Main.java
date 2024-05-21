package ru.practicum.dinner;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

            //Проверка имени блюда на дубликат
            if(dc.checkType(dishType) && dc.checkName(dishType, dishName)){
                System.out.printf("Блюдо с названием %s уже существует, введите другое название%n", dishName);
            }else {
                dc.addDish(dishType, dishName);
                System.out.printf("Блюдо %s добавлено%n", dishName);
            }
        }


    private static void generateDishCombo() {

        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");

        int numberOfCombos = Integer.parseInt(scanner.nextLine());
        if(numberOfCombos<=0){
            System.out.printf("Число комбинаций не может быть меньше нуля или равно нулю, а у Вас: %s%n"
                    ,numberOfCombos);
            return;
        }

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). " +
                "Для завершения ввода введите пустую строку");
        System.out.print("Тип блюда: ");
        String nextItem = scanner.nextLine();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            //Есть ли вводимый для генерации комбинаций тип в перечне типов блюд
            if(!dc.checkType(nextItem)){
                System.out.printf("Типа %s нет перечне, введите один из имеющихся типов блюд: %n",nextItem);
                System.out.println(dc.dishTypesToString());
            }else{
                dc.addDishTypeForDishCombo(nextItem);
            }
            System.out.print("Тип блюда: ");
            nextItem = scanner.nextLine();
        }
        System.out.println();

        //Проверим, не превышает ли заданное число наборов, максимальное число уникальных комбинаций
        //Если да, сообщим об этом пользователю и выведем, максимальное число уникальных комбинаций
        int maxNumberOfCombos = dc.getMaxNumberOfCombos();
        boolean outOfBounds = maxNumberOfCombos < numberOfCombos;
        if(outOfBounds){
            System.out.printf("Запрашиваемое число комбинаций (%1$d) превышаем возможное число уникальных комбинаций" +
                    "блюд (%2$d)...%nБудет выведено максимальное число уникальных комбинаций (%2$d):%n"
                    ,numberOfCombos, maxNumberOfCombos);
            System.out.println(dc.getDishComboAsString(maxNumberOfCombos));
        }else{
            System.out.println(dc.getDishComboAsString(numberOfCombos));
        }
    }
}
