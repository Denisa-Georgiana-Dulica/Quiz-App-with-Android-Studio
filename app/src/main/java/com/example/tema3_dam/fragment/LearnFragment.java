package com.example.tema3_dam.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.tema3_dam.R;
import com.example.tema3_dam.domain.Question;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class LearnFragment extends Fragment {


    public static final String SORTED_LIST = "SORTED_LIST";
    private List<Question> list=new ArrayList<>();
    private CheckBox checkBox;
    private Spinner spinner;

    public LearnFragment() {
        // Required empty public constructor
    }

    public static LearnFragment getInstance(List<Question> list)
    {
        LearnFragment learnFragment=new LearnFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList(SORTED_LIST, (ArrayList<? extends Parcelable>) list);
        learnFragment.setArguments(bundle);
        return learnFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list=getArguments().getParcelableArrayList(SORTED_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_learn, container, false);

       if(getContext()!=null)
       {
           ArrayAdapter<Question> adapter;
           checkBox=view.findViewById(R.id.dulica_denisa_learn_checkbox);
           spinner=view.findViewById(R.id.dulica_denisa_learn_spinner);
           list.sort(Comparator.comparing(Question::getLevel_difficulty));
           checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(isChecked)
                   {
                       list.sort(Comparator.comparing(Question::getLevel_difficulty).reversed());
                   }
                   else
                   {
                       list.sort(Comparator.comparing(Question::getLevel_difficulty));
                   }
               }
           });

               adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,list);
               spinner.setAdapter(adapter);

               adapter.notifyDataSetChanged();

       }
       return view;
    }
}