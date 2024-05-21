package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> dishesByTypes;
    ArrayList<String> dishTypesForDishCombo;
    ArrayList<ArrayList<String>> dishCombos;
    Random random;


    DinnerConstructor(){
        dishesByTypes = new HashMap<>();
        dishTypesForDishCombo = new ArrayList<>();
        dishCombos = new ArrayList<>();
        random = new Random();
    }

    //Добавить блюдо
    // Проверку на дублирвоание имени вставить в main
    void addDish(String type, String name){
        if(checkType(type)){
            dishesByTypes.get(type).add(name);
            if(checkName(type, name)){

            }
        }else {
            dishesByTypes.put(type, new ArrayList<String>());
            dishesByTypes.get(type).add(name);
        }
    }

    //получение готовой мапы (УДАЛИТЬ ПОСЛЕ ТЕСТИРОВАНИЯ)
    void addDishes(HashMap<String, ArrayList<String>> dishesByTypes){
        this.dishesByTypes = dishesByTypes;
    }

    void addDishTypeForDishCombo(String name){
        dishTypesForDishCombo.add(name);
    }

    void generateDishCombo(int numberOfCombo){
        ArrayList<String> dishes;
        ArrayList<String> currentCombo = new ArrayList<>();
        int comboCounter = 0;
        int randomIndex;
        while(comboCounter < numberOfCombo){
            for(String dishType: dishTypesForDishCombo){
                dishes = dishesByTypes.get(dishType);
                randomIndex = random.nextInt(dishes.size());
                currentCombo.add(dishes.get(randomIndex));
            }
            if(!dishCombos.isEmpty()){
                if(checkCombo(currentCombo)){
                    continue;
                }else {
                    dishCombos.add(currentCombo);
                }
            }else {
                dishCombos.add(currentCombo);
            }
            comboCounter++;
        }
    }

    boolean checkCombo(ArrayList<String> currentCombo){
        for (ArrayList<String> dishCombo : dishCombos) {
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
