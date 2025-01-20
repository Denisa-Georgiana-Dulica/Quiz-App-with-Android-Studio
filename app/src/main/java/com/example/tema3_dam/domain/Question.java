package com.example.tema3_dam.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

public class Question implements Parcelable {

    private String question_sentence;
    private String[] answers;
    private String correct_answer;
    private int level_difficulty;
    private int selectAnswerIndex=-1;
    private Boolean isCorrect=null;

    public Question(String question_sentence, String[] answers, int level_difficulty, String correct_answer) {
        this.question_sentence = question_sentence;
        this.answers = answers;
        this.level_difficulty = level_difficulty;
        this.correct_answer = correct_answer;
    }

    protected Question(Parcel in) {
        question_sentence = in.readString();
        answers = in.createStringArray();
        correct_answer = in.readString();
        level_difficulty = in.readInt();
        selectAnswerIndex=in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question_sentence);
        dest.writeStringArray(answers);
        dest.writeString(correct_answer);
        dest.writeInt(level_difficulty);
        dest.writeInt(selectAnswerIndex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public int getSelectAnswerIndex() {
        return selectAnswerIndex;
    }

    public void setSelectAnswerIndex(int selectAnswerIndex) {
        this.selectAnswerIndex = selectAnswerIndex;
    }

    public String getQuestion_sentence() {
        return question_sentence;
    }

    public void setQuestion_sentence(String question_sentence) {
        this.question_sentence = question_sentence;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public int getLevel_difficulty() {
        return level_difficulty;
    }

    public void setLevel_difficulty(int level_difficulty) {
        this.level_difficulty = level_difficulty;
    }

    @Override
    public String toString() {
        return level_difficulty+" "+question_sentence + "\nCorrect answer= " + correct_answer ;
    }


}


