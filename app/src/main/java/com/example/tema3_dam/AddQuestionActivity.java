package com.example.tema3_dam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tema3_dam.domain.Question;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    public static final String QUESTION_KEY = "QUESTION_KEY";
    private TextInputEditText tie_question;
    private TextInputEditText tie_answers;
    private TextInputEditText tie_correct_answer;
    private Button save_btn;
    private Spinner spinner;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialization();
        intent=getIntent();
    }

    private void initialization() {
        tie_question=findViewById(R.id.dulica_denisa_add_tie_question);
        tie_answers=findViewById(R.id.dulica_denisa_add_tie_answers);
        tie_correct_answer=findViewById(R.id.dulica_denisa_add_tie_correct_answer);
        save_btn=findViewById(R.id.dulica_denisa_add_save);
        spinner=findViewById(R.id.dulica_denisa_spinner);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                {
                    String correct_answer=tie_correct_answer.getText().toString();
                    String question_s=tie_question.getText().toString();
                    String answers=tie_answers.getText().toString().trim();
                    String[] array_answers=answers.split("\\s*,\\s*");
                    int difficulty=Integer.parseInt(spinner.getSelectedItem().toString());
                    Question question=new Question(question_s,array_answers,difficulty,correct_answer);
                    Log.i("Add",""+question.toString());
                    intent.putExtra(QUESTION_KEY,question);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private boolean isValid()
    {
        String answers=tie_answers.getText().toString().trim();
        String[] array_answers=answers.split("\\s*,\\s*");


        if(tie_question.getText()==null || tie_question.getText().toString().trim().length()<6)
        {
            Toast.makeText(getApplicationContext(), R.string.write_a_valid_question, Toast.LENGTH_SHORT).show();
            return false;
        } else  if(tie_answers.getText()==null || array_answers.length!=3)
        {
            Toast.makeText(getApplicationContext(), R.string.you_should_introduce_3_answers_between_comma, Toast.LENGTH_SHORT).show();
            return false;
        }else if(tie_correct_answer.getText()==null || tie_correct_answer.getText().toString().trim().length()<3)
        {
            Toast.makeText(getApplicationContext(), R.string.write_a_valid_answer, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}