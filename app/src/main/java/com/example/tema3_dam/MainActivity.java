package com.example.tema3_dam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tema3_dam.domain.Question;
import com.example.tema3_dam.fragment.LearnFragment;
import com.example.tema3_dam.fragment.ProgressFragment;
import com.example.tema3_dam.fragment.QuizFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tema3_dam.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

//https://drive.google.com/file/d/1LkU_Ql3lmsWpjS-Vdi7E5J5fvrx0gTSx/view?usp=drive_link
public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private List<Question> questions=new ArrayList<>();
    private Fragment currentFragment;
   private int correctAnswers=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getQuestions();
        configNavig();
        navigationView=findViewById(R.id.dulica_denisa_nav_view);
        navigationView.setNavigationItemSelectedListener(getItemSelected());

        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),getCallback());

    }

    private ActivityResultCallback<ActivityResult> getCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK && result.getData()!=null)
                {
                    Question question=result.getData().getParcelableExtra(AddQuestionActivity.QUESTION_KEY);
                    questions.add(question);
                    if(currentFragment instanceof QuizFragment)
                    {
                        ((QuizFragment) currentFragment).notifyAdapter();
                    }
                }

            }
        };
    }

    private NavigationView.OnNavigationItemSelectedListener getItemSelected() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.dulica_denisa_nav_quiz)
                {
                    currentFragment=QuizFragment.getInstance(questions);
                }else  if(item.getItemId()==R.id.dulica_denisa_nav_progress)
                {
                   currentFragment=ProgressFragment.getInstance(correctAnswers);
                }
                else  if(item.getItemId()==R.id.dulica_denisa_nav_learn)
                {
                    currentFragment= LearnFragment.getInstance(questions);
                }
                openFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    public void sendProgress(int count)
    {
        correctAnswers=count;
    }
    public int number_question()
    {
        return questions.size();
    }

    private void openFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.dulica_denisa_content_main_frameL,currentFragment).commit();
    }

    private void configNavig() {
        Toolbar toolbar=findViewById(R.id.dulica_denisa_main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.dulica_denisa_main_drawer);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();
    }

    private void getQuestions()
    {
        String[] questions_array=getApplicationContext().getResources().getStringArray(R.array.questions);
        String[] answers_array=getApplicationContext().getResources().getStringArray(R.array.answers);
        String[] correct_answers_array=getApplicationContext().getResources().getStringArray(R.array.correct_answer);
        String[] difficulties_array=getApplicationContext().getResources().getStringArray(R.array.difficulty);

        for(int i=0;i<questions_array.length;i++)
        {
            String[] answers=answers_array[i].trim().split("\\s*,\\s*");
            questions.add(new Question(questions_array[i],answers,Integer.parseInt(difficulties_array[i]),correct_answers_array[i]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.dulica_denisa_main_add_quetion)
        {
            for(Question q:questions)
            {
                int count=0;
                q.setSelectAnswerIndex(-1);
                q.setCorrect(null);
                Bundle bundle=new Bundle();
                sendProgress(count);
            }
            Intent intent=new Intent(getApplicationContext(),AddQuestionActivity.class);
            launcher.launch(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}