package ru.practicum.dinner;

import java.util.*;

public class DinnerConstructor {
    private Map<String, ArrayList<String>> dishesByTypes; //хранение блюд по типам
    private List<String> dishTypesForDishCombo;//список типов для генерирования комбинаций
    private List<ArrayList<String>> dishCombos;//список комбинаций блюд
    private Random random;


    DinnerConstructor(){
        dishesByTypes = new HashMap<>();
        dishTypesForDishCombo = new ArrayList<>();
        dishCombos = new ArrayList<>();
        random = new Random();
    }

    public void addDish(String type, String name){
        if(checkType(type)){
            dishesByTypes.get(type).add(name);
        }else {
            dishesByTypes.put(type, new ArrayList<>());
            dishesByTypes.get(type).add(name);
        }
    }

    //Добавить тип блюда в List для генератора комбинаций блюд
    public void addDishTypeForDishCombo(String name){
        dishTypesForDishCombo.add(name);
    }


    /**
     * <b>Создаёт уникальные комбинации блюд</b>, путем создания случайной комбинации
     * и проверки её методом checkCombo() на предмет наличия.
     * Метод добавляет сгенерированные наборы в поле dishCombos
     */
    private void generateDishCombo(int numberOfCombo){
        dishCombos.clear();
        List<String> dishes;//Для ссылки на лист в dishesByTypes
        ArrayList<String> currentCombo = new ArrayList<>();
        int comboCounter = 0;//для подсчета числа уникальных наборов
        int randomIndex;
        while(comboCounter < numberOfCombo){
            for(String dishType: dishTypesForDishCombo){
                dishes = dishesByTypes.get(dishType);
                randomIndex = random.nextInt(dishes.size());
                String randomDish = dishes.get(randomIndex);
                currentCombo.add(randomDish);
            }
            if(!dishCombos.isEmpty()){
                if(checkCombo(currentCombo)){//Набор не уникален и в набор не добавлен
                    currentCombo.clear();
                }else {//Уникальный набор блюд будет добавлен клонированием ArrayList
                    dishCombos.add((ArrayList<String>) currentCombo.clone());
                    currentCombo.clear();
                    comboCounter++;
                }
            }else {
                dishCombos.add((ArrayList<String>) currentCombo.clone());//Первый набор блюд
                currentCombo.clear();
                comboCounter++;
            }
        }
        dishTypesForDishCombo.clear();//Очистка набора типов блюд
    }

    //Проверка на наличие сгенерированной комбинации блюд в наборе
    private boolean checkCombo(List<String> currentCombo){
        for (List<String> dishCombo : dishCombos) {
            if(dishCombo.equals(currentCombo)){
                return true;
            }
        }
        return false;
    }

    private String dishComboToString(){
        String dishComboAsString = "";
        for (int i = 0; i < dishCombos.size(); i++) {
            dishComboAsString += String.format("%nКомбо %s%n[",i+1);
            List<String> dishCombo = dishCombos.get(i);
            for (int j = 0; j < dishCombo.size(); j++) {
                if(j == dishCombo.size()-1){
                    dishComboAsString += String.format("%s]%n",dishCombo.get(j));
                }else{
                    dishComboAsString += String.format("%s, ",dishCombo.get(j));
                }
            }

        }
        return dishComboAsString;
    }

    public String getDishComboAsString(int numberOfCombo){
        generateDishCombo(numberOfCombo);
        return dishComboToString();
    }

    public int getMaxNumberOfCombos(){
        if(dishTypesForDishCombo.isEmpty()){
            return -1;
        }
        int maxNumberOfCombos = 1;
        for(String dishType: dishTypesForDishCombo){
            maxNumberOfCombos *= dishesByTypes.get(dishType).size();
        }
        return maxNumberOfCombos;
    }

    //Для вывода списка доступных типов блюд
    public String dishTypesToString(){
        String dishTypesAsString = "";
        for(String dishType: dishesByTypes.keySet()){
            dishTypesAsString += String.format("- %s%n", dishType);
        }
        return dishTypesAsString;
    }


    public boolean checkType(String type){
        return dishesByTypes.containsKey(type);
    }

    public boolean checkName(String type, String name){
        if (dishesByTypes.isEmpty()) {
            return false;
        }
        return dishesByTypes.get(type).contains(name);
    }

}
