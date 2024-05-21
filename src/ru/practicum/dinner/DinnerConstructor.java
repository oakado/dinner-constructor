package ru.practicum.dinner;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> dishesByTypes; //хранение блюд по типам
    ArrayList<String> dishTypesForDishCombo;//список типов для генерирования комбинаций
    ArrayList<ArrayList<String>> dishCombos;//список комбинаций блюд
    Random random;


    DinnerConstructor(){
        dishesByTypes = new HashMap<>();
        dishTypesForDishCombo = new ArrayList<>();
        dishCombos = new ArrayList<>();
        random = new Random();
    }

    //Добавить блюдо
    // Проверку на дублирование имени вставить в main
    void addDish(String type, String name){
        if(checkType(type)){
            dishesByTypes.get(type).add(name);
            if(checkName(type, name)){
                return;//Перестраховка от дублирования имен. Check на дубли должен быть в main
            }
        }else {
            dishesByTypes.put(type, new ArrayList<String>());
            dishesByTypes.get(type).add(name);
        }
    }

    //получение готовой HashMap (УДАЛИТЬ ПОСЛЕ ТЕСТИРОВАНИЯ)
    void addDishesBulk(HashMap<String, ArrayList<String>> dishesByTypes){
        this.dishesByTypes = dishesByTypes;
    }

    void addDishTypeForDishCombo(String name){
        if(dishTypesForDishCombo.contains(name)){
            return;//Перестраховка от дублирования имен. Check на дубли должен быть в main
        }
        dishTypesForDishCombo.add(name);
    }
    //получение готового ArrayList (УДАЛИТЬ ПОСЛЕ ТЕСТИРОВАНИЯ)
    void addTypesForComboBulk(ArrayList<String> typesForCombo){
        dishTypesForDishCombo = typesForCombo;
    }


    void generateDishCombo(int numberOfCombo){
        ArrayList<String> dishes;//Для ссылки на лист в dishesByTypes
        ArrayList<String> currentCombo = new ArrayList<>();
        int comboCounter = 0;//засчитывает уникальные dishCombo
        int randomIndex;
        int testCounter = 0;
        while(comboCounter < numberOfCombo){
            for(String dishType: dishTypesForDishCombo){
                dishes = dishesByTypes.get(dishType);
                randomIndex = random.nextInt(dishes.size());
                currentCombo.add(dishes.get(randomIndex));
            }
            if(!dishCombos.isEmpty()){
                if(checkCombo(currentCombo)){
                    currentCombo.clear();
                    continue;//Набор не уникален (не допускается)
                }else {
                    dishCombos.add(currentCombo);
                    currentCombo.clear();//Уникальный набор блюд
                    comboCounter++;
                }
            }else {
                dishCombos.add(currentCombo);//Первый набор блюд
                currentCombo.clear();
                comboCounter++;
            }
            testCounter++;
            System.out.println("testCounter = " + testCounter);
            if(testCounter>2) break;
        }
    }

    //Проверка на НЕуникальность сгенерированной комбинации блюд
    boolean checkCombo(ArrayList<String> currentCombo){
        for (ArrayList<String> dishCombo : dishCombos) {
            System.out.println("dishCombo = " + dishCombo);
            System.out.println("currentCombo = " + currentCombo);
            if(dishCombo.equals(currentCombo)){
                return true;
            }
        }
        return false;
    }

    String dishComboToString(){
        String dishComboAsString = "";
        for (int i = 0; i < dishCombos.size(); i++) {
            dishComboAsString += String.format("Комбо %s%n[",i);
            for(String dish: dishCombos.get(i)){
                dishComboAsString += String.format("%s, ",dish);
            }
            dishComboAsString += "]\n";
        }
        return dishComboAsString;
    }


    boolean checkType(String type){
        return dishesByTypes.containsKey(type);
    }

    boolean checkName(String type, String name){
        return dishesByTypes.get(type).contains(name);
    }





}
