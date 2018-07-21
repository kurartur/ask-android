package lt.dualpair.test.ask.android.data.repository;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import lt.dualpair.test.ask.android.data.dao.QuestionDao;
import lt.dualpair.test.ask.android.data.entity.Question;

public class QuestionRepository {

    private QuestionDao questionDao;

    public QuestionRepository(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public Flowable<List<Question>> getQuestions() {
        return questionDao.getAllFlowable()
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        if (questionDao.getAll().isEmpty()) {
                            insertFakeQuestions();
                        }
                    }
                });
    }

    private void insertFakeQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(createQuestion("Где библиотека?", "3 часа ночи, че т почитать захотелось. А где библиотечка то, не пойму. Кто нить знает, а???"));
        questions.add(createQuestion("Why TI18 battle pass sucks ass?", "Am I the only one who thinks that this year battle pass is piece of shit?"));
        questions.add(createQuestion("Nothing to do", "Yo, who's up for some chatting?"));
        questions.add(createQuestion("Reikalingas smartphone už 100 Eu", "Ieškau telefono, gal kas pastaruoju metu domėjosi. Biudžetas - 100 Eu. Patarkitas ką."));
        questions.add(createQuestion("Nothing to do", "Yo, who's up for some chatting?"));
        questions.add(createQuestion("Кто поломал?", "Упал - отрубился. Очнулся - гипс. Кто меня поломал вчера на кутузовском?"));
        questions.add(createQuestion("Щелезубы", "Сколько всего существует видов щелезубов?"));
        questions.add(createQuestion("Šiauliai - Kuršienai", "Ar į Kuršienus važiuoja kas nors? KAS DAR Į KURŠIENUS???"));
        questionDao.insertAll(questions);
    }

    private Question createQuestion(String subject, String text) {
        Question question = new Question();
        question.setSubject(subject);
        question.setText(text);
        question.setDate(new Date());
        return question;
    }

    public Completable addQuestion(final String subject, final String text) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                questionDao.save(createQuestion(subject, text));
            }
        });
    }

    public Flowable<Question> getQuestion(Long questionId) {
        return questionDao.getQuestionFlowable(questionId);
    }
}
