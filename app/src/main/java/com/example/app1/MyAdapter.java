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
import android.widget.TextView;

import androidx.annotation.NonNull;


public class MyAdapter extends
        RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {
    private ArrayList<Integer> mNumberList;
    private Activity mActivity;
    private int counter = 1;
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
        switch (counter){
            case 1:
                holder.przedmiotTV.setText(R.string.przedmiot1);
                break;
            case 2:
                holder.przedmiotTV.setText(R.string.przedmiot2);
                break;
            case 3:
                holder.przedmiotTV.setText(R.string.przedmiot3);
                break;
            case 4:
                holder.przedmiotTV.setText(R.string.przedmiot4);
                break;
            case 5:
                holder.przedmiotTV.setText(R.string.przedmiot5);
                break;
            case 6:
                holder.przedmiotTV.setText(R.string.przedmiot6);
                break;
            case 7:
                holder.przedmiotTV.setText(R.string.przedmiot7);
                break;
            case 8:
                holder.przedmiotTV.setText(R.string.przedmiot8);
                break;
            case 9:
                holder.przedmiotTV.setText(R.string.przedmiot9);
                break;
            case 10:
                holder.przedmiotTV.setText(R.string.przedmiot10);
                break;
            case 11:
                holder.przedmiotTV.setText(R.string.przedmiot11);
                break;
            case 12:
                holder.przedmiotTV.setText(R.string.przedmiot12);
                break;
            case 13:
                holder.przedmiotTV.setText(R.string.przedmiot13);
                break;
            case 14:
                holder.przedmiotTV.setText(R.string.przedmiot14);
                break;
            case 15:
                holder.przedmiotTV.setText(R.string.przedmiot15);
                break;
        }
        counter++;

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
        TextView przedmiotTV;
        private RadioGroup rg1;
        private RadioButton r1;
        private RadioButton r2;
        private RadioButton r3;
        private RadioButton r4;
        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            przedmiotTV = itemView.findViewById(R.id.label);
            mNumberEditText = itemView.findViewById(R.id.numberEditText);
            rg1 = itemView.findViewById(R.id.radioGroup1);
            r1 = itemView.findViewById(R.id.radio_1);
            r2 = itemView.findViewById(R.id.radio_2);
            r3 = itemView.findViewById(R.id.radio_3);
            r4 = itemView.findViewById(R.id.radio_4);
        }}}