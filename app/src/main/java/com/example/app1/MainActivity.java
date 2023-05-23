package com.example.app1;


import static java.lang.Math.round;

import java.math.RoundingMode;
import java.text.BreakIterator;
import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_LABEL_TEXT = "com.example.event_handling.KEY_TEXT";
    private static final String KEY_LABEL_TEXTa = "com.example.event_handling.KEY_TEXT";
    private static final String KEY_LABEL_TEXTb = "com.example.event_handling.KEY_TEXT";
    private static final String KEY_LABEL_TEXT1 = "com.example.event_handling.KEY_TEXT";
    private static final String KEY_LABEL_TEXT2 = "com.example.event_handling.KEY_TEXT";
    private static final String KEY_LABEL_TEXT3 = "com.example.event_handling.KEY_TEXT";
    EditText eu1,eu2,eu3;
    TextView resultTV1;
    Button b2,b1;
    Boolean f1 = false,f2 = false,f3 = false;
    String buttonstate1,buttonstate2;
    private ActivityResultLauncher<Intent> mActivityResultLauncher;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV1 = findViewById(R.id.textView4);
        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent resultIntent = result.getData();
                            double srednia = Double.valueOf(resultIntent.getStringExtra(SecondActivity.RESULT_KEY));
                            String sr = resultIntent.getStringExtra(SecondActivity.RESULT_KEY);
                            resultTV1.setText("oto twoja srednia -> " +sr.substring(0,4));
                            b1.setVisibility(View.VISIBLE);
                            buttonstate2 = "1";
                            if(srednia<3.0){
                                b1.setText(R.string.nietym);
                                b1.setOnClickListener(view -> zakoncz(getString(R.string.niezal)));
                            }else{
                                b1.setText(R.string.sup);
                                b1.setOnClickListener(view -> zakoncz(getString(R.string.zal)));
                            }
                        }
                    }
                }
        );
        b1 = findViewById(R.id.button1);
        b1.setVisibility(View.INVISIBLE);
        b2 = findViewById(R.id.button2);

        //
        b2.setOnClickListener(v -> startSecondActivity());
        eu1 = findViewById(R.id.editText);
        eu2 = findViewById(R.id.editText2);
        eu3 = findViewById(R.id.editText3);
        if(eu1.getText().toString().matches("")){
            b2.setVisibility(View.INVISIBLE);
        }
        if(savedInstanceState!=null){
            eu1.setText(savedInstanceState.getString(KEY_LABEL_TEXT));
            eu2.setText(savedInstanceState.getString(KEY_LABEL_TEXTa));
            eu3.setText(savedInstanceState.getString(KEY_LABEL_TEXTb));
            String  war = savedInstanceState.getString(KEY_LABEL_TEXT1);
            if(war!=""){
                b2.setVisibility(View.VISIBLE);
            }
            resultTV1.setText(savedInstanceState.getString(KEY_LABEL_TEXT2));

            /*

            String  war2 = savedInstanceState.getString(KEY_LABEL_TEXT3);
            if(war2!=""){
                b1.setVisibility(View.VISIBLE);
            }

             */
        }
        buttonstate1 ="1";
        buttonstate2 ="0";
        eu1.setOnFocusChangeListener((view, b) -> {
            if(!b && eu1.getText().toString().matches("")){
                eu1.setError("Bledne Imie");
                Toast.makeText(getApplicationContext(),"Wpisz Imie",Toast.LENGTH_SHORT).show();
                f1 = false;
                b2.setVisibility(View.INVISIBLE);
                buttonstate1 = "0";
            }else{
                f1 = true;
                if(f3 && f2){
                    b2.setVisibility(View.VISIBLE);
                    buttonstate1 = "1";
                }
            }
        });
        eu2.setOnFocusChangeListener((view, b) -> {
            if(!b && eu2.getText().toString().matches("")){
                eu2.setError("Bledne Nazwisko");
                Toast.makeText(getApplicationContext(),"Wpisz Nazwisko",Toast.LENGTH_SHORT).show();
                f2 = false;
                b2.setVisibility(View.INVISIBLE);
                buttonstate1 = "0";
            }else{
                f2 = true;
                if(f1 && f3){
                    b2.setVisibility(View.VISIBLE);
                    buttonstate1 = "1";
                }
            }
        });
        eu3.setOnFocusChangeListener((view, b) -> {
            if(!b && eu3.getText().toString().matches("")){
                eu3.setError("Nie wprowadziles liczby");
                Toast.makeText(getApplicationContext(),"Wprowadz liczbe oczen!",Toast.LENGTH_SHORT).show();
                f3 = false;
                b2.setVisibility(View.INVISIBLE);
                buttonstate1 = "0";
            }else if(b){}
            else{
                int n1 = 0;
                if(eu3.getText().toString().matches("") || !TextUtils.isDigitsOnly(eu3.getText().toString())){
                    eu3.setError("Wprowadziles bledny znak");
                    n1 = 0;
                    f3 = false;
                    b2.setVisibility(View.INVISIBLE);
                    buttonstate1 = "0";
                }else{
                    n1 = Integer.parseInt(eu3.getText().toString());
                }
                if(n1 < 5 || n1 > 15){
                    eu3.setError("Wprowadziles bledna liczbe");
                    Toast.makeText(getApplicationContext(),"Podales bledna liczbe ocen!",Toast.LENGTH_SHORT).show();
                    f3 = false;
                    b2.setVisibility(View.INVISIBLE);
                    buttonstate1 = "0";
                }else{
                    f3 = true;
                    if(f1 && f2){
                        b2.setVisibility(View.VISIBLE);
                        buttonstate1 = "1";
                    }
                }
            }
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


        String ls1= eu1.getText().toString();
        String ls2= eu2.getText().toString();
        String ls3= eu3.getText().toString();
        String tv4= resultTV1.getText().toString();
        outState.putString(KEY_LABEL_TEXT,ls1);
        outState.putString(KEY_LABEL_TEXTa,ls2);
        outState.putString(KEY_LABEL_TEXTb,ls3);
        outState.putString(KEY_LABEL_TEXT1,buttonstate1);
        outState.putString(KEY_LABEL_TEXT3,buttonstate2);
        outState.putString(KEY_LABEL_TEXT2,tv4);
        buttonstate1 ="";
        buttonstate2="";
    }
    public static final String TEXT_KEY = "com.example.w4_two_activities_and.text";
    private void startSecondActivity(){
        EditText et = findViewById(R.id.editText3);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(TEXT_KEY,et.getText().toString());
        //startActivity(intent);
        mActivityResultLauncher.launch(intent);
    }
    private void zakoncz(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        System.exit(0);
    }
}
