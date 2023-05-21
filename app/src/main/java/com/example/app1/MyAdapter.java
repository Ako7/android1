package com.example.app1;

import android.app.Activity;

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
        int value=mNumberList.get(position);
        //najpierw ustawiamy etykietę bo setText wywoła zdarzenie
        holder.mNumberEditText.setTag(position);
        holder.mNumberEditText.setText(Integer.toString(value));

        
    }
    @Override
    public int getItemCount() {
        return mNumberList.size();
    }
    //view holder zarządza pojedynczym wierszem listy
    //to dobre miejsce na zaimplementowanie słuchaczy
    class MyAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, TextWatcher
    {
        Button mPlusButton;
        EditText mNumberEditText;
        private RadioGroup rg1;
        private RadioButton r1;
        private RadioButton r2;
        private RadioButton r3;
        private RadioButton r4;
        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            r1 = itemView.findViewById(R.id.radio_1);
            r2 = itemView.findViewById(R.id.radio_2);
            if(r2.isChecked()){
                mPlusButton = itemView.findViewById(R.id.plusButton);
                mPlusButton.setOnClickListener(this);
                mNumberEditText = itemView.findViewById(R.id.numberEditText);
                mNumberEditText.addTextChangedListener(this);
            }

        }
        @Override
        public void onClick(View v) {
            int number=0;
            try {
                number=Integer.parseInt(
                        mNumberEditText.getText().toString());
                number++;
            } catch (NumberFormatException ignored) { }
            mNumberEditText.setText(Integer.toString(number));
        }
        @Override
        public void beforeTextChanged(CharSequence s,
                                      int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s,
                                  int start, int before, int count) { }
        @Override
        public void afterTextChanged(Editable s) {
            int number=0;
            try {
                number=Integer.parseInt(
                        mNumberEditText.getText().toString());
            } catch (NumberFormatException e) { }
            int index=(Integer) mNumberEditText.getTag();
            mNumberList.set(index,number);
        }}}