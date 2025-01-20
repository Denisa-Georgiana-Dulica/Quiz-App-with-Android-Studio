package com.example.tema3_dam.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tema3_dam.MainActivity;
import com.example.tema3_dam.R;
import com.example.tema3_dam.domain.Question;
import com.example.tema3_dam.domain.QuestionAdapter;

import java.util.ArrayList;
import java.util.List;


public class QuizFragment extends Fragment {

    public static final String LIST_QUESTIONS = "LIST_QUESTIONS";
    private List<Question> list;
    private ListView listView;
    private Button see_answers_btn;
    private QuestionAdapter adapter;
    private int count=0;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment getInstance(List<Question> list)
    {
        QuizFragment quizFragment=new QuizFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList(LIST_QUESTIONS, (ArrayList<? extends Parcelable>) list);
        quizFragment.setArguments(bundle);
        return quizFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
        {
            list=getArguments().getParcelableArrayList(LIST_QUESTIONS);
        }

    }

    private void resetRadioBox()
    {
        for(Question q:list)
        {
            count=0;
            q.setSelectAnswerIndex(-1);
            q.setCorrect(null);
            if(getActivity() instanceof MainActivity)
            {
                ((MainActivity) getActivity()).sendProgress(count);
            }
        }
        notifyAdapter();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_quiz, container, false);
        if(getContext()!=null)
        {
            see_answers_btn=view.findViewById(R.id.dulica_denisa_see_answers_btn);
            listView=view.findViewById(R.id.dulica_denisa_quiz_lv);
            adapter=new QuestionAdapter(getContext().getApplicationContext(),R.layout.lv_row,list,getLayoutInflater());
            listView.setAdapter(adapter);
            resetRadioBox();

            see_answers_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validate();
                }
            });

        }
        return view;
    }

    private void validate()
    {
        boolean allAnswers=true;
        for(Question question:list)
        {
            if(question.getSelectAnswerIndex()==-1)
            {
                allAnswers=false;
                break;
            }
        }

        if(allAnswers==true)
        {
            count=0;
            for(Question question:list) {
                int index=question.getSelectAnswerIndex();
                String answer=question.getAnswers()[index];

                if(answer.equals(question.getCorrect_answer()))
                {
                    count++;
                    question.setCorrect(true);
                }
                else {
                    question.setCorrect(false);
                }
            }
            adapter.notifyDataSetChanged();

            if(getActivity() instanceof MainActivity)
            {
                ((MainActivity) getActivity()).sendProgress(count);
            }

        }

        if(allAnswers==false)
        {
            Toast.makeText(getContext().getApplicationContext(), R.string.you_should_choose_an_answer_for_all_this_questions_to_complite_the_quiz, Toast.LENGTH_SHORT).show();
        }
    }

    public void notifyAdapter()
    {
        QuestionAdapter adapter1= (QuestionAdapter) listView.getAdapter();
        adapter1.notifyDataSetChanged();
    }
}