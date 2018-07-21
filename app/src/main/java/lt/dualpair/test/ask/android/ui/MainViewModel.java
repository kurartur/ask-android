package lt.dualpair.test.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;
import lt.dualpair.test.ask.android.data.AppDatabase;
import lt.dualpair.test.ask.android.data.entity.Question;
import lt.dualpair.test.ask.android.data.repository.QuestionRepository;

public class MainViewModel extends AndroidViewModel {

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
