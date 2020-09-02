package com.example.calculadoramanijas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner material,type;
    private TextView result;
    private EditText quantity;
    private RadioGroup rgDijes , rgMoney;
    private RadioButton hammer,anchor,cop,usd;
    private String[] optionsMaterial, optionsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        material = findViewById(R.id.spMaterial);
        type = findViewById(R.id.spType);
        result = findViewById(R.id.lblResult);
        quantity = findViewById(R.id.txtQuantity);
        hammer = findViewById(R.id.rbDijeHammer);
        anchor = findViewById(R.id.rbDijeAnchor);
        cop = findViewById(R.id.rbCop);
        usd = findViewById(R.id.rbDolar);

        optionsMaterial =getResources().getStringArray(R.array.materials);
        optionsType = getResources().getStringArray(R.array.types);

        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,optionsMaterial);
        ArrayAdapter<String> adapterType = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,optionsType);

        material.setAdapter(adapterMaterial);
        type.setAdapter(adapterType);
    }

    public void CalculatePrice(View v){
        int quanti, opcMaterial,opcType;
        double res,price =0;
        result.setText("");
        if(validate()){
            quanti = Integer.parseInt(String.valueOf(quantity.getText()));
            opcMaterial = material.getSelectedItemPosition();
            opcType = type.getSelectedItemPosition();
            switch (opcMaterial){
                case 1:
                    if(hammer.isChecked() == true) {
                        if(opcType == 1 || opcType == 2){
                            price = 100;
                        }else if(opcType == 3){
                            price = 80;
                        }else{
                            price = 70;
                        }
                    }else if(anchor.isChecked() == true){
                        if(opcType == 1 || opcType == 2){
                            price = 120;
                        }else if(opcType == 3){
                            price = 100;
                        }else{
                            price = 90;
                        }
                    }
                    break;
                case 2:
                    if(hammer.isChecked() == true) {
                        if(opcType == 1 || opcType == 2){
                            price = 90;
                        }else if(opcType == 3){
                            price = 70;
                        }else{
                            price = 50;
                        }
                    }else if(anchor.isChecked() == true){
                        if(opcType == 1 || opcType == 2){
                            price = 110;
                        }else if(opcType == 3){
                            price = 90;
                        }else{
                            price = 80;
                        }
                    }
                    break;
            }

            if(cop.isChecked() == true){
                res = (price * 3200) * quanti;
                result.setText(getString(R.string.result_cop)+res);

            }
            if(usd.isChecked() == true){
                res = price * quanti;
                result.setText(getString(R.string.result_usd)+res);
            }

        }
    }

    public boolean validate(){
        if(material.getSelectedItemPosition() == 0){
            Toast.makeText(this,R.string.validate_material, Toast.LENGTH_LONG).show();
            return false;
        }
        if(type.getSelectedItemPosition() == 0){
            Toast.makeText(this,R.string.validate_material, Toast.LENGTH_LONG).show();
            return false;
        }
        if(cop.isChecked() == false && usd.isChecked() == false){
            Toast.makeText(this, R.string.validate_money, Toast.LENGTH_LONG).show();
            return false;
        }
        if(hammer.isChecked() == false && anchor.isChecked() == false){
            Toast.makeText(this,R.string.validate_dije, Toast.LENGTH_LONG).show();
            return false;
        }

        if(quantity.getText().toString().isEmpty()){
            quantity.setError(getString(R.string.validate_quantity));
            quantity.requestFocus();
            return false;
        }
        if(Integer.parseInt(quantity.getText().toString()) == 0){
            quantity.setError(getString(R.string.validate_quantity));
            quantity.requestFocus();
            return false;
        }
        return true;
    }

    public void clear(View v){
        material.setSelection(0);
        type.setSelection(0);
        result.setText("");
        quantity.setText("");
        hammer.setChecked(false);
        anchor.setChecked(false);
        cop.setChecked(false);
        usd.setChecked(false);
    }
}