package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;

public class DishesByTypes {
    private HashMap<String, ArrayList<String>> dishesByTypes;

    DishesByTypes(){
        dishesByTypes = new HashMap<>();
        dishesByTypes.put("typeList",null);
    }

    DishesByTypes(HashMap<String, ArrayList<String>> subDishesByTypes, ArrayList<String> dishesForDishCombo){
        dishesByTypes = subDishesByTypes;
        dishesByTypes.put("typeList",dishesForDishCombo);
    }

    void addNewDishType(String dishType){
        if(dishType.equals("typeList")){
            return;
        }
        dishesByTypes.put(dishType, new ArrayList<String>());
    }

    void addNewDish(String dishType, String dishName){
        dishesByTypes.get(dishType).add(dishName);
    }

    boolean containsType(String dishType){
        if(dishType.equals("typeList")){
            return false;
        }
        return dishesByTypes.containsKey(dishType);
    }

    boolean containsDish(String dishType,String dishName){
        if(dishType.equals("typeList")){
            return false;
        }
        return dishesByTypes.get(dishType).contains(dishName);
    }

    String getDishByIndex(String dishType, int dishIndex){
        if(dishType.equals("typeList")){
            return null;
        }
        return dishesByTypes.get(dishType).get(dishIndex);
    }
    int getCountOfDishes(String dishType){
        if(dishType.equals("typeList")){
            return -1;
        }
        return dishesByTypes.get(dishType).size();
    }

    int getCountOfTypes(){
        return dishesByTypes.size()-1;
    }

    String getTypeAsStringByIndex(int index){
        if(index>=getCountOfTypes() || index<0){
            return null;
        }
        return dishesByTypes.get("typeList").get(index+1);
    }

    ArrayList<String> typeList(){
        return dishesByTypes.get("typeList");
    }



    DishesByTypes getDishByTypesForDishCombo(ArrayList<String> dishesForDishCombo){
        HashMap<String, ArrayList<String>> subDishesByTypes = new HashMap<>();
        for(String dish: dishesForDishCombo){
            subDishesByTypes.put(dish, (ArrayList<String>) dishesByTypes.get(dish).clone());
        }
        DishesByTypes dishByTypesForDishCombo = new DishesByTypes(subDishesByTypes, dishesForDishCombo);
        return dishByTypesForDishCombo;
    }
}
