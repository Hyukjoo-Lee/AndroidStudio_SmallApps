package com.example.caloriecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup pattyRG;
    private CheckBox prosciuttoCBX;
    private RadioGroup cheeseRG;
    private SeekBar sauceSBR;
    private TextView caloriesTV;

    private Burger burger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        burger = new Burger();
        initialize();

        registerChangeListener();
    }

    private void initialize() {

        pattyRG = findViewById(R.id.radioGroupPatty);
        prosciuttoCBX = findViewById(R.id.checkBoxProsciutto);
        cheeseRG = findViewById(R.id.radioGroupCheese);
        sauceSBR = findViewById(R.id.seekBarSauce);
        caloriesTV = findViewById(R.id.textViewCalories);

        displayCalories();
    }


    private void registerChangeListener() {
        pattyRG.setOnCheckedChangeListener(foodListener);
        prosciuttoCBX.setOnClickListener(baconListener);
        cheeseRG.setOnCheckedChangeListener(foodListener);
        sauceSBR.setOnSeekBarChangeListener(sauceListener);
    }

    private RadioGroup.OnCheckedChangeListener foodListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

            switch (checkedId) {
                case R.id.radioBeef:
                    burger.setPattyCalories(Burger.BEEF);
                    break;
                case R.id.radioLamb:
                    burger.setPattyCalories(Burger.LAMB);
                    break;
                case R.id.radioOstrich:
                    burger.setPattyCalories(Burger.OSTRICH);
                    break;
                case R.id.radioAsiagoCheese:
                    burger.setCheeseCalories(Burger.ASIAGO);
                    break;
                case R.id.radioCremeFraiche:
                    burger.setCheeseCalories(Burger.CREME_FRAICHE);
                    break;
            }
            displayCalories();
        }
    };


    private View.OnClickListener baconListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (((CheckBox) view).isChecked()) {
                burger.setProsciuttoCalories(Burger.PROSCIUTTO);
            } else {
                burger.clearProsciuttoCalories();
            }
            displayCalories();
        }
    };

    private void displayCalories() {
        String caloriesText = "Calories: " + burger.getTotalCalories();
        caloriesTV.setText(caloriesText);
    }

    private SeekBar.OnSeekBarChangeListener sauceListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            burger.setSauceCalories(seekBar.getProgress());
            displayCalories();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}









