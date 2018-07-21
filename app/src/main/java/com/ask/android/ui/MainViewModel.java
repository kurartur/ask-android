package com.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.ask.android.data.AppDatabase;
import com.ask.android.data.entity.Question;
import com.ask.android.data.repository.QuestionRepository;

import java.util.List;

import io.reactivex.Flowable;

class MainViewModel extends AndroidViewModel {

    private Flowable<List<Question>> questionsFlowable;

    public MainViewModel(@NonNull Application application) {
        super(application);
        QuestionRepository questionRepository = new QuestionRepository(AppDatabase.getDatabase(application).questionDao());
        questionsFlowable = questionRepository.getQuestions();
    }

    public Flowable<List<Question>> getQuestions() {
        return questionsFlowable;
    }
}
