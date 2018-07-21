package com.ask.android.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ask.android.R;

public class ChatFragment extends BaseFragment {

    private static final String QUESTION_ID = "questionId";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chat, null);
        return view;
    }

    public static ChatFragment newInstance(Long questionId) {
        ChatFragment fragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(QUESTION_ID, questionId);
        fragment.setArguments(bundle);
        return fragment;
    }
}
