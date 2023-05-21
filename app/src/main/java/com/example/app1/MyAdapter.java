package com.example.app1;

import android.app.Activity;

import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;


public class MyAdapter extends
        RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {
    private ArrayList<Integer> mNumberList;
    private Activity mActivity;
    public MyAdapter(Activity activity,ArrayList<Integer> numberList)
    {
        mNumberList=numberList;
        mActivity=activity;
    }
    //wywoływane gdy tworzony jest nowy wiersz
    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        //ustawienie rodzica i attachToRoot ->
        //wiersze będą uwzględniały rozmiary listy
        View rowRootView=mActivity.getLayoutInflater().inflate(R.layout.list_row,parent,false);
        MyAdapterViewHolder myAdapterViewHolder= new MyAdapterViewHolder(rowRootView);
        return myAdapterViewHolder;
    }
    //wywoływany zawsze gdy ma być wyświetlony nowy wiersz
    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {
        int value = mNumberList.get(position);
        holder.mNumberEditText.setTag(position);
        holder.mNumberEditText.setText(Integer.toString(value));

        holder.rg1.setOnCheckedChangeListener(null);
        switch (value){
            case 2:
                holder.r1.setChecked(true);
                break;
            case 3:
                holder.r2.setChecked(true);
                break;
            case 4:
                holder.r3.setChecked(true);
                break;
            case 5:
                holder.r4.setChecked(true);
                break;
        }

        holder.rg1.setOnCheckedChangeListener((group, checkedID) -> {
            int selectedVal =0;
            switch (checkedID){
                case R.id.radio_1:
                    selectedVal =2;
                    break;
                case R.id.radio_2:
                    selectedVal =3;
                    break;
                case R.id.radio_3:
                    selectedVal =4;
                    break;
                case R.id.radio_4:
                    selectedVal =5;
                    break;
            }
            int index = (Integer) holder.mNumberEditText.getTag();
            mNumberList.set(index, selectedVal);
        });
    }
    @Override
    public int getItemCount() {
        return mNumberList.size();
    }
    //view holder zarządza pojedynczym wierszem listy
    //to dobre miejsce na zaimplementowanie słuchaczy
    class MyAdapterViewHolder extends RecyclerView.ViewHolder
    {

        EditText mNumberEditText;
        private RadioGroup rg1;
        private RadioButton r1;
        private RadioButton r2;
        private RadioButton r3;
        private RadioButton r4;
        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumberEditText = itemView.findViewById(R.id.numberEditText);
            rg1 = itemView.findViewById(R.id.radioGroup1);
            r1 = itemView.findViewById(R.id.radio_1);
            r2 = itemView.findViewById(R.id.radio_2);
            r3 = itemView.findViewById(R.id.radio_3);
            r4 = itemView.findViewById(R.id.radio_4);
        }}}