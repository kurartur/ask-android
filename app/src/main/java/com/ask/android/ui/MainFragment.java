package com.ask.android.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ask.android.R;
import com.ask.android.data.entity.Question;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainFragment extends BaseFragment implements QuestionsRecyclerAdapter.OnItemClickListener {

    private RecyclerView questionsView;

    private MainViewModel viewModel;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_main, null);
        questionsView = view.findViewById(R.id.questions);
        questionsView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        FloatingActionButton add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddQuestionActivity.createActivity(getActivity()));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        subscribeUi();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void subscribeUi() {
        Disposable d = viewModel.getQuestions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Question>>() {
                    @Override
                    public void accept(List<Question> questions) throws Exception {
                        questionsView.setAdapter(new QuestionsRecyclerAdapter(questions, MainFragment.this));
                    }
                });
        disposable.add(d);
    }

    @Override
    public void onClick(Question question) {
        startActivity(QuestionActivity.createIntent(getActivity(), question.getId()));
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
