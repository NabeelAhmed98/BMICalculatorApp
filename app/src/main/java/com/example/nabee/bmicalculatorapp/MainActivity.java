package com.example.nabee.bmicalculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText heightInput;
    private EditText weightInput;
    private RadioGroup unitsChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitsChoice = findViewById(R.id.radioUnit);
        heightInput = findViewById(R.id.userHeight);
        weightInput = findViewById(R.id.userWeight);
        Button calculateButton = findViewById(R.id.calculate);

        heightInput.setText("0.0");
        weightInput.setText("0.0");

        //set the listeners for the app
        unitsChoice.setOnCheckedChangeListener(unitsChoiceListener);
        calculateButton.setOnClickListener(calculateButtonListener);
    }

    //Code for the radio button. If "English" is clicked, the units are changed to inches and pounds
    //If "metric" is clicked, the units are changed to kilograms and meters.
    private final RadioGroup.OnCheckedChangeListener unitsChoiceListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton unitButton = findViewById(checkedId);
            TextView hUnit = findViewById(R.id.heightUnit);
            TextView wUnit = findViewById(R.id.weightUnit);
            EditText heightInput = findViewById(R.id.userHeight);
            EditText weightInput = findViewById(R.id.userWeight);

            if (unitButton.getText().equals("English")){
                hUnit.setText(R.string.inches);
                wUnit.setText(R.string.pounds);
            }
            else if(unitButton.getText().equals("Metric")){
                hUnit.setText(R.string.meters);
                wUnit.setText(R.string.kilograms);
            }
        }
    };

    //Code for the calculate button. Once clicked, it checks for what radio button is clicked
    //in the radio group, and calls the appropriate calculate function.
    private final View.OnClickListener calculateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int selectedId = unitsChoice.getCheckedRadioButtonId();
            RadioButton unitButton = findViewById(selectedId);
            if (unitButton.getText().equals("English")){
                calculateEn(Double.parseDouble(heightInput.getEditableText().toString()),
                        Double.parseDouble(weightInput.getEditableText().toString()));
            }
            else if(unitButton.getText().equals("Metric")){
                calculateM(Double.parseDouble(heightInput.getEditableText().toString()),
                        Double.parseDouble(weightInput.getEditableText().toString()));
            }
        }
    };

    //calculates the bmi using the english units.
    private void calculateEn(double height, double weight){
        TextView bmi = findViewById(R.id.bmiCalculation);
        double bmiCalculation = ((weight*703)/(height*height));
        bmi.setText(Double.toString(bmiCalculation));
    }

    //calculates the bmi using the metric units.
    private void calculateM(double height, double weight){
        TextView bmi = findViewById(R.id.bmiCalculation);
        double bmiCalculation = ((weight)/(height*height));
        bmi.setText(Double.toString(bmiCalculation));

    }
}
