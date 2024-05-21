package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;

public class TestMain {
    public static void main(String[] args) {
        DinnerConstructor dinnerConstructor = new DinnerConstructor();
        HashMap<String, ArrayList<String>> dishes = new HashMap<>();
        ArrayList<String> dishTypeForDishCombo = new ArrayList<>();
        dishes.put("dish1", new ArrayList<String>());
        dishes.put("dish2", new ArrayList<String>());
        dishes.put("dish3", new ArrayList<String>());

        dishes.get("dish1").add("d1-1");
        dishes.get("dish1").add("d1-2");
        dishes.get("dish1").add("d1-3");
        dishes.get("dish1").add("d1-4");
        dishes.get("dish2").add("d2-1");
        dishes.get("dish2").add("d2-2");
        dishes.get("dish2").add("d2-3");
        dishes.get("dish2").add("d2-4");
        dishes.get("dish3").add("d3-1");
        dishes.get("dish3").add("d3-2");
        dishes.get("dish3").add("d3-3");
        dishes.get("dish3").add("d3-4");

        dishTypeForDishCombo.add("dish1");
        dishTypeForDishCombo.add("dish3");

        dinnerConstructor.addDishesBulk(dishes);
        dinnerConstructor.addTypesForComboBulk(dishTypeForDishCombo);



        dinnerConstructor.generateDishCombo(2);
        String combos = dinnerConstructor.dishComboToString();
        System.out.println(combos);





    }
}
