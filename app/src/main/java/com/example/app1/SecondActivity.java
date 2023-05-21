package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    public final static String RESULT_KEY = "com.example.w4_two_activities_and.result";
    private ArrayList<Integer> mNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView tf1 = findViewById(R.id.textsecView1);

        Bundle bundle = getIntent().getExtras();
        tf1.setText(bundle.getString(MainActivity.TEXT_KEY));
        Random random = new Random();
        mNumberList = new ArrayList<>();
        for(int i=0;i<10;i++){
            mNumberList.add(random.nextInt(4)+2);
        }
        Button averageButton=findViewById(R.id.averageButton);
        averageButton.setOnClickListener(
                view -> { computeAverage(); });
        RecyclerView numberRecyclerView=(RecyclerView) findViewById(R.id.numberRecyclerView);
        MyAdapter myAdapter=new MyAdapter(this,mNumberList);
        numberRecyclerView.setAdapter(myAdapter);
        numberRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        }
        private void computeAverage() {
            int sum=0;
            for (int number : mNumberList)
                sum+=number;
            Toast.makeText(this,"Average: "+
                    (sum/(mNumberList.size()*1.0)),Toast.LENGTH_LONG).show();
    }
}
