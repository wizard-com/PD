package com.example.pdthird;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkoutProgramsData {

    private static HashMap<String, String[]> workoutProgramsData = new HashMap<String, String[]>();

    private static String[] liit_array = new String[]{"1. (No jump) squat",
    "Start by standing with feet hip-width apart and your hands at your sides. Slowly squat down, making sure to bend at your knees, not your waist. Aim to touch the floor at either side of you with your fingertips. Slowly come back to standing, reaching your hands to the sky and coming up onto your toes. Repeat for 60 seconds, then rest for 2 minutes.",
    "TIP: Don’t worry if you can’t quite touch the floor in your squat, just go as far as you can without feeling any pain.\n\n", "2. Side step and touch",
    "Start with your feet together. Step your left leg out to the side, and at the same time reach your right hand across your body and towards your left foot (twisting your waist). Step your leg back to the centre, and come back up to standing. Repeat, alternating sides each time, for 60 seconds, then rest.",
    "TIP: If you can’t reach your foot, aim for your ankle or shin instead. To make this harder, step your leg out further to the side.\n\n", "3.Three-point balance",
    "Stand tall with your hands on your hips. Transfer your weight to your right leg, and slowly lift your left foot off of the floor. Extend your raised leg out to the front, then to the side and finally behind you. Repeat for 30 seconds, keeping your leg raised. Swap legs and repeat for 30 seconds. Rest for 2 minutes.",
    "TIP: Challenge yourself by raising your leg higher and lifting your arms above your head.\n\n", "4. Slow burpee",
    "From standing, bend your knees and place your hands on the floor in front of you. Step your right leg back behind you. Step your left leg back to meet it so you’re in a press-up position. Step your right foot back to your hands, followed by your left leg. Stand up. Repeat for 60 seconds, alternating starting legs. Rest for 2 minutes.",
    "TIP: Too hard? Put your hands on a step or low stool instead of the floor.\n\n", "5. Assisted sit-ups", "Lay on a mat with your arms at your side and a slight bend in your knees. Slowly rise up to sitting, pressing your hands into the mat for extra support if needed. Slowly lower yourself back to the floor. Repeat for 60 seconds, then rest for 2 minutes.",
    "TIP: Make your core work harder by crossing your arms across your chest. Breathe out as you come up, keeping your chin lifted off  your chest so your airway stays open.", "#06FF45"};

    private static String[] miit_array = new String[]{"Circuit #1 (Tabata Circuit):"," Alternate between push-ups and mountain climbers.", "Intervals:  8 cycles of 20 seconds of work and 10 seconds of rest, for a total of 4 minutes.  Do once\n\n",
    "Circuit #2 (Insanity-Style Circuit):","Bodyweight Squats (or Jump Squats)", "Alternating Reverse Lunges (or Lunge Jumps)", "Burpees (go slow for medium intensity or go fast, add a push-up or a jump for higher intensity)",
    "Alternating Forward Kicks (or add a hop between kicks)", "Intervals:  4 cycles of 30 seconds of work in a row, followed by 30 seconds of rest after each circuit.  Do this circuit 3 times total.\n\n", "Circuit #3 (Abdominals):",
    "Plank (from knees, elbows or hands – your choice)", "Triceps Dips (they’re harder the further out your feet are from your body)", "Russian Twists (lift your feet off the floor and keep your arms straight for higher intensity)",
    "Inchworm (add a push-up at the end to make this tougher)", "Intervals:  4 cycles of 30 seconds of work in a row, followed by 30 seconds of rest after each circuit.  Do this circuit 3 times total.", "#FE9900"};


    private static String[] hiit_array = new String[]{"Circuit #1 (Quick warm-up):", "Running on the spot (2 minutes)", "Twists (2 minutes), Zig-zag runs (3 minutes)\n\n",
    "Circuit #2 (Heavy cardio)", "Sprint (2 minutes) after that rest for 1 minute", "Repeat 10 times\n\n", "Circuit #3 (The last circuit)", "Do 25-40 push ups and jumps.", "Cool down", "#ED1C24"};

    public static String[] getData(String key){

        workoutProgramsData.put("LIIT", liit_array);
        workoutProgramsData.put("MIIT", miit_array);
        workoutProgramsData.put("HIIT", hiit_array);

        String[] arrayFromHashMap = workoutProgramsData.get(key);
        int length = workoutProgramsData.get(key).length;
        String[] data_array = new String[length];

        for(int i = 0; i < arrayFromHashMap.length; i++){
            data_array[i] = arrayFromHashMap[i];
        }

        return data_array;
    }

}
