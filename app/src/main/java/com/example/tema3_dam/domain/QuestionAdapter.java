package com.example.tema3_dam.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tema3_dam.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {

    private Context context;
    private int resource;
    private List<Question> list=new ArrayList<>();
    private LayoutInflater inflater;

    public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects,LayoutInflater inflater) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.list=objects;
        this.inflater=inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=inflater.inflate(resource,parent,false);
        Question question=list.get(position);

        if(question==null)
        {
            return view;
        }

        TextView tv_question=view.findViewById(R.id.dulica_denisa_lv_row_question);
        tv_question.setText(question.getQuestion_sentence());
        RadioGroup rg=view.findViewById(R.id.dulica_denisa_lv_row_rg1);

        for(int i=0;i<rg.getChildCount();i++) {
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            rb.setText(question.getAnswers()[i]);
        }

            if(question.getSelectAnswerIndex()!=-1)
            {
                RadioButton rb = (RadioButton) rg.getChildAt(question.getSelectAnswerIndex());
                rb.setChecked(true);
            }


        for(int i=0;i<rg.getChildCount();i++) {
            RadioButton rb = (RadioButton) rg.getChildAt(i);
            if(question.isCorrect()!=null) {
                if (question.isCorrect() == true && i==question.getSelectAnswerIndex()) {
                    rb.setBackgroundColor(getContext().getResources().getColor(R.color.green));
                } else if (question.isCorrect() == false && i==question.getSelectAnswerIndex()) {
                    rb.setBackgroundColor(getContext().getResources().getColor(R.color.red));
                } else {
                    rb.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
                }
            }
        }


            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    for(int i=0;i<rg.getChildCount();i++) {
                        RadioButton rb = (RadioButton) rg.getChildAt(i);
                        if(rb.getId()==checkedId)
                        {
                            question.setSelectAnswerIndex(i);
                            break;
                        }
                    }
                }
            });

        return view;
    }
}
