package com.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.ask.android.data.AppDatabase;
import com.ask.android.data.repository.QuestionRepository;

import io.reactivex.Completable;

public class AddQuestionViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;

    public AddQuestionViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(AppDatabase.getDatabase(application).questionDao());
    }

    public Completable addQuestion(String subject, String text) {
        return questionRepository.addQuestion(subject, text);
    }
}
