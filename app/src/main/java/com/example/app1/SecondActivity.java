package com.example.app1;

import static android.util.Half.round;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    public final static String RESULT_KEY = "com.example.w4_two_activities_and.result";
    private static final String KEY_LABEL_TEXT = "com.example.event_handling.KEY_TEXT";

    private ArrayList<Integer> mNumberList;
    private ArrayList<Integer> nNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView tf1 = findViewById(R.id.textsecView1);

        Bundle bundle = getIntent().getExtras();
        int alelista = Integer.parseInt(bundle.getString(MainActivity.TEXT_KEY));
        if(savedInstanceState!=null){
            mNumberList = new ArrayList<>();
            mNumberList = savedInstanceState.getIntegerArrayList(KEY_LABEL_TEXT);
        }else {
            Random random = new Random();
            mNumberList = new ArrayList<>();
            for(int i=0;i<alelista;i++){
                mNumberList.add(random.nextInt(4)+2);
            }
        }
        Button averageButton=findViewById(R.id.averageButton);
        averageButton.setOnClickListener(
                view -> { computeAverageandBack(); });
        RecyclerView numberRecyclerView=(RecyclerView) findViewById(R.id.numberRecyclerView);
        MyAdapter myAdapter=new MyAdapter(this,mNumberList);
        numberRecyclerView.setAdapter(myAdapter);
        numberRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        }
        private void computeAverageandBack() {
            int sum=0;
            for (int number : mNumberList)
                sum+=number;
            double srednia = sum/(mNumberList.size()*1.0);
            srednia += 0.00001;
            Toast.makeText(this,"Average: "+ srednia,Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            String ss = srednia+"";
            intent.putExtra(RESULT_KEY, ss);
            setResult(RESULT_OK,intent);
            finish();
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList listazapas = mNumberList;
        outState.putIntegerArrayList(KEY_LABEL_TEXT,listazapas);

    }
}
