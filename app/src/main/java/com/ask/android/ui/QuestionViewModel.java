package com.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ask.android.data.AppDatabase;
import com.ask.android.data.entity.Question;
import com.ask.android.data.repository.QuestionRepository;

import io.reactivex.Flowable;

public class QuestionViewModel extends ViewModel {

    private Flowable<Question> questionFlowable;

    public QuestionViewModel(@NonNull Application application, Long questionId) {
        QuestionRepository questionRepository = new QuestionRepository(AppDatabase.getDatabase(application).questionDao());
        questionFlowable = questionRepository.getQuestion(questionId);
    }

    public Flowable<Question> getQuestion() {
        return questionFlowable;
    }

    public static class Factory extends ViewModelProvider.AndroidViewModelFactory {

        private Application application;
        private Long questionId;

        public Factory(@NonNull Application application, Long questionId) {
            super(application);
            this.application = application;
            this.questionId = questionId;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new QuestionViewModel(application, questionId);
        }
    }
}
