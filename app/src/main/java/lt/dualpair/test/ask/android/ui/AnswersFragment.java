package lt.dualpair.test.ask.android.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lt.dualpair.test.ask.android.R;

public class AnswersFragment extends BaseFragment {

    private static final String QUESTION_ID = "questionId";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_answers, null);
        return view;
    }

    public static AnswersFragment newInstance(Long questionId) {
        AnswersFragment fragment = new AnswersFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(QUESTION_ID, questionId);
        fragment.setArguments(bundle);
        return fragment;
    }
}
