package lt.dualpair.test.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import lt.dualpair.test.ask.android.data.AppDatabase;
import lt.dualpair.test.ask.android.data.entity.Question;
import lt.dualpair.test.ask.android.data.repository.QuestionRepository;

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
