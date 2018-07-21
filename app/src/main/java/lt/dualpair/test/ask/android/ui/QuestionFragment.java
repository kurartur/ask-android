package lt.dualpair.test.ask.android.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lt.dualpair.test.ask.android.R;
import lt.dualpair.test.ask.android.data.entity.Question;

public class QuestionFragment extends BaseFragment {

    private static final String QUESTION_ID = "questionId";

    private TextView subject;
    private TextView text;

    private QuestionViewModel viewModel;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_question, null);
        subject = view.findViewById(R.id.subject);
        text = view.findViewById(R.id.text);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new QuestionViewModel.Factory(getActivity().getApplication(), getArguments().getLong(QUESTION_ID))).get(QuestionViewModel.class);
        subscribeUi();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void subscribeUi() {
        Disposable d = viewModel.getQuestion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Question>() {
                    @Override
                    public void accept(Question question) throws Exception {
                        render(question);
                    }
                });
        disposable.add(d);
    }

    private void render(Question question) {
        subject.setText(question.getSubject());
        text.setText(question.getText());
    }

    public static QuestionFragment newInstance(Long questionId) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(QUESTION_ID, questionId);
        fragment.setArguments(bundle);
        return fragment;
    }
}
