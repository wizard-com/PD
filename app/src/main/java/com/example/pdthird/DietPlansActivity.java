package com.example.pdthird;

import android.os.Bundle;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DietPlansActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    DietListAdapter expandableListAdapter;
    ArrayList<DietTitleItem> expandableListTitle;

    List<List<DietNestedItem>>[] dietArray = new List[5];

    List<List<DietNestedItem>> proteinDiets = new ArrayList<>();
    List<List<DietNestedItem>> fibreDiets = new ArrayList<>();
    List<List<DietNestedItem>> lowFatDiets = new ArrayList<>();
    List<List<DietNestedItem>> lowCarbDiets = new ArrayList<>();
    List<List<DietNestedItem>> balancedDiets = new ArrayList<>();


    List<DietNestedItem> protein = new ArrayList<DietNestedItem>();

    List<DietNestedItem> fibre = new ArrayList<DietNestedItem>();

    List<DietNestedItem> lowFat = new ArrayList<DietNestedItem>();

    List<DietNestedItem> lowCarb = new ArrayList<DietNestedItem>();

    List<DietNestedItem> balanced = new ArrayList<DietNestedItem>();

    LinkedHashMap<String, List<DietNestedItem>> thirdLevelProtein = new LinkedHashMap<>();
    LinkedHashMap<String, List<DietNestedItem>> thirdLevelFibre = new LinkedHashMap<>();
    LinkedHashMap<String, List<DietNestedItem>> thirdLevelLowFat = new LinkedHashMap<>();
    LinkedHashMap<String, List<DietNestedItem>> thirdLevelLowCarb = new LinkedHashMap<>();
    LinkedHashMap<String, List<DietNestedItem>> thirdLevelBalanced = new LinkedHashMap<>();

    List<List<DietNestedItem>> secondLevel = new ArrayList<>();
    List<LinkedHashMap<String, List<DietNestedItem>>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plans);
        setupAdapter();

    }

    private void setupAdapter(){

        String[] labels = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        String[] colorCodes = new String[]{"#594287", "#84B83C", "#F9EC00", "#FB8A20", "#87D3E7"};

        List<DietNestedItem>[] arrayOfList = new List[5];

        arrayOfList[0] = protein;
        arrayOfList[1] = fibre;
        arrayOfList[2] = lowFat;
        arrayOfList[3] = lowCarb;
        arrayOfList[4] = balanced;

        for(int i = 0; i < arrayOfList.length; i++){

            for(int j = 0; j < labels.length; j++){

                arrayOfList[i].add(new DietNestedItem(labels[j], colorCodes[i]));
            }

        }

        secondLevel.add(protein);
        secondLevel.add(fibre);
        secondLevel.add(lowFat);
        secondLevel.add(lowCarb);
        secondLevel.add(balanced);

        String[][][] dietMenu = buildDietMenu();

        dietArray[0] = proteinDiets;
        dietArray[1] = fibreDiets;
        dietArray[2] = lowFatDiets;
        dietArray[3] = lowCarbDiets;
        dietArray[4] = balancedDiets;

        for (int i = 0; i < dietArray.length; i++){

            for (int j = 0; j < dietMenu[i].length; j++){

                List<DietNestedItem> dietNestedItems = new ArrayList<>();

                for (int k = 0; k < dietMenu[i][j].length; k++){

                    dietNestedItems.add(new DietNestedItem(dietMenu[i][j][k], colorCodes[i]));
                }
                dietArray[i].add(dietNestedItems);
            }
        }

//        List<LinkedHashMap<String, List<DietNestedItem>>> tempList = new ArrayList<>();
//
//        tempList.add(thirdLevelProtein);
//        tempList.add(thirdLevelFibre);
//        tempList.add(thirdLevelLowFat);
//        tempList.add(thirdLevelLowCarb);
//        tempList.add(thirdLevelBalanced);
//
//        for (int i = 0; i < tempList.size(); i++){
//
//            LinkedHashMap<String, List<DietNestedItem>> tempItem = tempList.get(i);
//
//            for (int j = 0; j < secondLevel.size(); j++){
//
//                tempItem.put(secondLevel.get(i).get(j).getLabel(), proteinDiets.get(j));
//            }
//        }
        thirdLevelProtein.put(secondLevel.get(0).get(0).getLabel(), proteinDiets.get(0));
        thirdLevelProtein.put(secondLevel.get(0).get(1).getLabel(), proteinDiets.get(1));
        thirdLevelProtein.put(secondLevel.get(0).get(2).getLabel(), proteinDiets.get(2));
        thirdLevelProtein.put(secondLevel.get(0).get(3).getLabel(), proteinDiets.get(3));
        thirdLevelProtein.put(secondLevel.get(0).get(4).getLabel(), proteinDiets.get(4));
        thirdLevelProtein.put(secondLevel.get(0).get(5).getLabel(), proteinDiets.get(5));
        thirdLevelProtein.put(secondLevel.get(0).get(6).getLabel(), proteinDiets.get(6));

        thirdLevelFibre.put(secondLevel.get(1).get(0).getLabel(), fibreDiets.get(0));
        thirdLevelFibre.put(secondLevel.get(1).get(1).getLabel(), fibreDiets.get(1));
        thirdLevelFibre.put(secondLevel.get(1).get(2).getLabel(), fibreDiets.get(2));
        thirdLevelFibre.put(secondLevel.get(1).get(3).getLabel(), fibreDiets.get(3));
        thirdLevelFibre.put(secondLevel.get(1).get(4).getLabel(), fibreDiets.get(4));
        thirdLevelFibre.put(secondLevel.get(1).get(5).getLabel(), fibreDiets.get(5));
        thirdLevelFibre.put(secondLevel.get(1).get(6).getLabel(), fibreDiets.get(6));

        thirdLevelLowFat.put(secondLevel.get(2).get(0).getLabel(), lowFatDiets.get(0));
        thirdLevelLowFat.put(secondLevel.get(2).get(1).getLabel(), lowFatDiets.get(1));
        thirdLevelLowFat.put(secondLevel.get(2).get(2).getLabel(), lowFatDiets.get(2));
        thirdLevelLowFat.put(secondLevel.get(2).get(3).getLabel(), lowFatDiets.get(3));
        thirdLevelLowFat.put(secondLevel.get(2).get(4).getLabel(), lowFatDiets.get(4));
        thirdLevelLowFat.put(secondLevel.get(2).get(5).getLabel(), lowFatDiets.get(5));
        thirdLevelLowFat.put(secondLevel.get(2).get(6).getLabel(), lowFatDiets.get(6));

        thirdLevelLowCarb.put(secondLevel.get(3).get(0).getLabel(), lowCarbDiets.get(0));
        thirdLevelLowCarb.put(secondLevel.get(3).get(1).getLabel(), lowCarbDiets.get(1));
        thirdLevelLowCarb.put(secondLevel.get(3).get(2).getLabel(), lowCarbDiets.get(2));
        thirdLevelLowCarb.put(secondLevel.get(3).get(3).getLabel(), lowCarbDiets.get(3));
        thirdLevelLowCarb.put(secondLevel.get(3).get(4).getLabel(), lowCarbDiets.get(4));
        thirdLevelLowCarb.put(secondLevel.get(3).get(5).getLabel(), lowCarbDiets.get(5));
        thirdLevelLowCarb.put(secondLevel.get(3).get(6).getLabel(), lowCarbDiets.get(6));

        thirdLevelBalanced.put(secondLevel.get(4).get(0).getLabel(), balancedDiets.get(0));
        thirdLevelBalanced.put(secondLevel.get(4).get(1).getLabel(), balancedDiets.get(1));
        thirdLevelBalanced.put(secondLevel.get(4).get(2).getLabel(), balancedDiets.get(2));
        thirdLevelBalanced.put(secondLevel.get(4).get(3).getLabel(), balancedDiets.get(3));
        thirdLevelBalanced.put(secondLevel.get(4).get(4).getLabel(), balancedDiets.get(4));
        thirdLevelBalanced.put(secondLevel.get(4).get(5).getLabel(), balancedDiets.get(5));
        thirdLevelBalanced.put(secondLevel.get(4).get(6).getLabel(), balancedDiets.get(6));


        data.add(thirdLevelProtein);
        data.add(thirdLevelFibre);
        data.add(thirdLevelLowFat);
        data.add(thirdLevelLowCarb);
        data.add(thirdLevelBalanced);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableDietList);
        expandableListTitle = new ArrayList<DietTitleItem>();
        expandableListTitle.add(new DietTitleItem("#594287", "High-protein diet", R.drawable.protein));
        expandableListTitle.add(new DietTitleItem("#84B83C", "High-fibre diet", R.drawable.vegetables));
        expandableListTitle.add(new DietTitleItem("#F9EC00", "Low-fat diet", R.drawable.lowfat));
        expandableListTitle.add(new DietTitleItem("#FB8A20", "Low-carb diet", R.drawable.lowcarb));
        expandableListTitle.add(new DietTitleItem("#87D3E7", "Balanced diet", R.drawable.balanced_diet));
        //passing three level of information to constructor
        DietListAdapter expandableListAdapter = new DietListAdapter(this, expandableListTitle, secondLevel, data);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListAdapter.notifyDataSetChanged();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


    }

    private String[][][] buildDietMenu(){
        String[][][] dietMenu = new String[5][7][7];

        String[][] labelsDietProtein = new String[][]{
                {"Breakfast:", "3 eggs, 1 slice whole grain toast with 1 tablespoon almond butter and a pear", "Lunch:", "Fresh Avocado and Cottage Cheese Salad and an orange.", "Dinner:","6 ounces (170 g) steak, sweet potato and grilled zucchini.", ""},
                {"Breakfast:", "Smoothie made with 1 scoop protein powder, 1 cup coconut milk and strawberries.", "Lunch:", "4 ounces (114 g) canned salmon, mixed greens, olive oil and vinegar and an apple.", "Dinner:", "4 ounces (114 g) grilled chicken with quinoa and Brussels sprouts.", ""},
                {"Breakfast:", "Oatmeal and one cup plain Greek yogurt with 1/4 cup chopped pecans.", "Lunch:", "4 ounces (114 g) chicken mixed with avocado and red bell pepper and a peach.", "Dinner:", "All Meat Veggie Chili and brown rice.", ""},
                {"Breakfast:", "Spanish omelet made with 3 eggs, 1 ounce cheese, chili peppers, black olives and salsa and an orange.", "Lunch:", "Leftover All Meat Veggie Chili and brown rice.", "Dinner:", "4 ounces (114 g) halibut, lentils and broccoli.", ""},
                {"Breakfast:", "One cup cottage cheese with 1/4 cup chopped walnuts, diced apples and cinnamon.", "Lunch:", "4 ounces (114 g) canned salmon mixed with healthy mayo on sprouted grain bread and carrot sticks.", "Dinner:" , "Chicken Meatballs with Marinara Sauce, spaghetti squash and raspberries.", ""},
                {"Breakfast:", "Frittata made with 3 eggs, 1 ounce cheese and 1/2 cup diced potatoes.", "Lunch:", "Leftover Chicken Meatballs with Marinara Sauce and spaghetti squash with an apple.", "Dinner:" , " 3 ounces (85 g) shrimp fajitas with grilled onions and bell peppers, guacamole, 1 cup black beans on a corn tortilla.", ""},
                {"Breakfast:", "Protein Pumpkin Pancakes topped with 1/4 cup chopped pecans.", "Lunch:", "One cup plain Greek yogurt mixed with 1/4 cup chopped mixed nuts and pineapple.", "Dinner:" , "6 ounces (170 g) grilled salmon, potatoes and sautéed spinach.", ""},
        };

        String[][] labelsDietFibre = new String[][]{
                {"Breakfast:", "30g bran flakes, 3 dried apricots, and a medium banana served with 150ml skimmed milk.", "Lunch:", "Baked salmon with a bed of baby spinach (50g) alongside a medium baked potato (200 g).", "Dinner:","Wholemeal bread 2 slices with chicken breast(150 g), 2 tsp mayo and a tomato. 10 Strawberries.", ""},
                {"Breakfast:", "2 boiled eggs with 2 slices of wholemeal toast and 200ml fresh orange juice.", "Lunch:", "Cook 60g wholemeal pasta. 2 chopped spring onions in 1 tsp olive oil along with 100g chopped asparagus and 1 tbsp Parmesan.", "Dinner:", "Make up a salad with 1/2 avocado, 80g lettuce, 30g cucumber and 30g red onion with 150g prawn sprinkled with seafood sauce and lime juice", ""},
                {"Breakfast:", "30g bran cereal with 200ml semi skimmed milk, served with a banana.", "Lunch:", "600g carton of fresh lentil soup and a wholemeal roll.", "Dinner:", "All Meat Veggie Chili and brown rice.", ""},
                {"Breakfast:", "40g porridge with 200ml semi skimmed milk and 4 prunes", "Lunch:", "1 large baked potato with 1/2 can baked beans and 30g cheese. Served with a mixed leaf salad and a bowl of strawberries for dessert.", "Dinner:", "A grilled lemon sole fillet with steamed vegetables (80g broccoli, 1 carrot, 40g mange tout), served with 100g boiled new potatoes. Home made fruit salad (140g) for dessert.", ""},
                {"Breakfast:", "1/2 Orange & 1/2 grapefruit cut into segments. 1 egg scrambled with 1 tbsp semi skimmed milk on 1 slice of wholemeal toast and a thin spread of butter.", "Lunch:", "1 1/4 cupsCowboy Beef & Bean Chili with 1 medium Orange", "Dinner:" , "100g chicken breast with 100g boiled potatoes, 80g steamed broccoli. 100g peach slices with a pot of yoghurt (125g) with 20g chopped almonds for dessert.", ""},
                {"Breakfast:", "Frittata made with 3 eggs, 1 ounce cheese and 1/2 cup diced potatoes.", "Lunch:", "Leftover Chicken Meatballs with Marinara Sauce and spaghetti squash with an apple.", "Dinner:" , " 3 ounces (85 g) shrimp fajitas with grilled onions and bell peppers, guacamole, 1 cup black beans on a corn tortilla.", ""},
                {"Breakfast:", "Protein Pumpkin Pancakes topped with 1/4 cup chopped pecans.", "Lunch:", "One cup plain Greek yogurt mixed with 1/4 cup chopped mixed nuts and pineapple.", "Dinner:" , "6 ounces (170 g) grilled salmon, potatoes and sautéed spinach.", ""},
        };

        String[][] labelsDietLowFat = new String[][]{
                {"Breakfast:", "3 eggs, 1 slice whole grain toast with 1 tablespoon almond butter and a pear", "Lunch:", "Fresh Avocado and Cottage Cheese Salad and an orange.", "Dinner:","6 ounces (170 g) steak, sweet potato and grilled zucchini.", ""},
                {"Breakfast:", "Smoothie made with 1 scoop protein powder, 1 cup coconut milk and strawberries.", "Lunch:", "4 ounces (114 g) canned salmon, mixed greens, olive oil and vinegar and an apple.", "Dinner:", "4 ounces (114 g) grilled chicken with quinoa and Brussels sprouts.", ""},
                {"Breakfast:", "Oatmeal and one cup plain Greek yogurt with 1/4 cup chopped pecans.", "Lunch:", "4 ounces (114 g) chicken mixed with avocado and red bell pepper and a peach.", "Dinner:", "All Meat Veggie Chili and brown rice.", ""},
                {"Breakfast:", "Spanish omelet made with 3 eggs, 1 ounce cheese, chili peppers, black olives and salsa and an orange.", "Lunch:", "Leftover All Meat Veggie Chili and brown rice.", "Dinner:", "4 ounces (114 g) halibut, lentils and broccoli.", ""},
                {"Breakfast:", "One cup cottage cheese with 1/4 cup chopped walnuts, diced apples and cinnamon.", "Lunch:", "4 ounces (114 g) canned salmon mixed with healthy mayo on sprouted grain bread and carrot sticks.", "Dinner:  " , "Chicken Meatballs with Marinara Sauce, spaghetti squash and raspberries.", ""},
                {"Breakfast:", "Frittata made with 3 eggs, 1 ounce cheese and 1/2 cup diced potatoes.", "Lunch:", "Leftover Chicken Meatballs with Marinara Sauce and spaghetti squash with an apple.", "Dinner:" , " 3 ounces (85 g) shrimp fajitas with grilled onions and bell peppers, guacamole, 1 cup black beans on a corn tortilla.", ""},
                {"Breakfast:", "Protein Pumpkin Pancakes topped with 1/4 cup chopped pecans.", "Lunch:", "One cup plain Greek yogurt mixed with 1/4 cup chopped mixed nuts and pineapple.", "Dinner:" , "6 ounces (170 g) grilled salmon, potatoes and sautéed spinach.", ""},
        };

        String[][] labelsDietLowCarb = new String[][]{
                {"Breakfast:", "3 eggs, 1 slice whole grain toast with 1 tablespoon almond butter and a pear", "Lunch:", "Fresh Avocado and Cottage Cheese Salad and an orange.", "Dinner:  ","6 ounces (170 g) steak, sweet potato and grilled zucchini.", ""},
                {"Breakfast:", "Smoothie made with 1 scoop protein powder, 1 cup coconut milk and strawberries.", "Lunch:", "4 ounces (114 g) canned salmon, mixed greens, olive oil and vinegar and an apple.", "Dinner:", "4 ounces (114 g) grilled chicken with quinoa and Brussels sprouts.", ""},
                {"Breakfast:", "Oatmeal and one cup plain Greek yogurt with 1/4 cup chopped pecans.", "Lunch:", "4 ounces (114 g) chicken mixed with avocado and red bell pepper and a peach.", "Dinner:", "All Meat Veggie Chili and brown rice.", ""},
                {"Breakfast:", "Spanish omelet made with 3 eggs, 1 ounce cheese, chili peppers, black olives and salsa and an orange.", "Lunch:", "Leftover All Meat Veggie Chili and brown rice.", "Dinner:", "4 ounces (114 g) halibut, lentils and broccoli.", ""},
                {"Breakfast:", "One cup cottage cheese with 1/4 cup chopped walnuts, diced apples and cinnamon.", "Lunch:", "4 ounces (114 g) canned salmon mixed with healthy mayo on sprouted grain bread and carrot sticks.", "Dinner:" , "Chicken Meatballs with Marinara Sauce, spaghetti squash and raspberries.", ""},
                {"Breakfast:", "Frittata made with 3 eggs, 1 ounce cheese and 1/2 cup diced potatoes.", "Lunch:", "Leftover Chicken Meatballs with Marinara Sauce and spaghetti squash with an apple.", "Dinner:" , " 3 ounces (85 g) shrimp fajitas with grilled onions and bell peppers, guacamole, 1 cup black beans on a corn tortilla.", ""},
                {"Breakfast:", "Protein Pumpkin Pancakes topped with 1/4 cup chopped pecans.", "Lunch:", "One cup plain Greek yogurt mixed with 1/4 cup chopped mixed nuts and pineapple.", "Dinner:" , "6 ounces (170 g) grilled salmon, potatoes and sautéed spinach.", ""},
        };

        String[][] labelsDietBalanced = new String[][]{
                {"Breakfast:", "3 eggs, 1 slice whole grain toast with 1 tablespoon almond butter and a pear", "Lunch:", "Fresh Avocado and Cottage Cheese Salad and an orange.", "Dinner:","6 ounces (170 g) steak, sweet potato and grilled zucchini.", ""},
                {"Breakfast:", "Smoothie made with 1 scoop protein powder, 1 cup coconut milk and strawberries.", "Lunch:", "4 ounces (114 g) canned salmon, mixed greens, olive oil and vinegar and an apple.", "Dinner:", "4 ounces (114 g) grilled chicken with quinoa and Brussels sprouts.", ""},
                {"Breakfast:", "Oatmeal and one cup plain Greek yogurt with 1/4 cup chopped pecans.", "Lunch:", "4 ounces (114 g) chicken mixed with avocado and red bell pepper and a peach.", "Dinner:", "All Meat Veggie Chili and brown rice.", ""},
                {"Breakfast:", "Spanish omelet made with 3 eggs, 1 ounce cheese, chili peppers, black olives and salsa and an orange.", "Lunch:", "Leftover All Meat Veggie Chili and brown rice.", "Dinner:", "4 ounces (114 g) halibut, lentils and broccoli.", ""},
                {"Breakfast:", "One cup cottage cheese with 1/4 cup chopped walnuts, diced apples and cinnamon.", "Lunch:", "4 ounces (114 g) canned salmon mixed with healthy mayo on sprouted grain bread and carrot sticks.", "Dinner:" , "Chicken Meatballs with Marinara Sauce, spaghetti squash and raspberries.", ""},
                {"Breakfast:", "Frittata made with 3 eggs, 1 ounce cheese and 1/2 cup diced potatoes.", "Lunch:", "Leftover Chicken Meatballs with Marinara Sauce and spaghetti squash with an apple.", "Dinner:" , " 3 ounces (85 g) shrimp fajitas with grilled onions and bell peppers, guacamole, 1 cup black beans on a corn tortilla.", ""},
                {"Breakfast:", "Protein Pumpkin Pancakes topped with 1/4 cup chopped pecans.", "Lunch:", "One cup plain Greek yogurt mixed with 1/4 cup chopped mixed nuts and pineapple.", "Dinner:" , "6 ounces (170 g) grilled salmon, potatoes and sautéed spinach.", ""},
        };

        dietMenu[0] = labelsDietProtein;
        dietMenu[1] = labelsDietFibre;
        dietMenu[2] = labelsDietLowFat;
        dietMenu[3] = labelsDietLowCarb;
        dietMenu[4] = labelsDietBalanced;

        return dietMenu;
    }
}
