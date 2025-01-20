package com.example.tema3_dam.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tema3_dam.MainActivity;
import com.example.tema3_dam.R;


public class ProgressFragment extends Fragment {

    public static final String CORRECT_ANSWERS = "CORRECT_ANSWERS";
    private int correctAnswersCount;
    private ProgressBar progressBar;
    private TextView progress_tv;
    private TextView number_questions;

    public ProgressFragment() {
        // Required empty public constructor
    }

    public static ProgressFragment getInstance(int correctAnswersCount)
    {
        ProgressFragment progressFragment=new ProgressFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(CORRECT_ANSWERS,correctAnswersCount );
        progressFragment.setArguments(bundle);
        return progressFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correctAnswersCount=getArguments().getInt(CORRECT_ANSWERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_progress, container, false);
        progressBar=view.findViewById(R.id.dulica_denisa_progress_bar);
        progress_tv=view.findViewById(R.id.dulica_denisa_fragment_tv);
        number_questions=view.findViewById(R.id.dulica_denisa_fragment_tv_10);
        if(getContext()!=null)
        {
            progressBar.setProgress(correctAnswersCount);
            progress_tv.setText(String.valueOf(correctAnswersCount));
            if(getActivity() instanceof MainActivity)
            {
                int nr= ((MainActivity) getActivity()).number_question();
                number_questions.setText(String.valueOf(nr));
            }

        }
        return view;
    }
}