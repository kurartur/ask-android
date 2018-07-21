package com.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.ask.android.data.AppDatabase;
import com.ask.android.data.entity.User;
import com.ask.android.data.repository.UserRepository;

import io.reactivex.Flowable;

public class SplashViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private static final Long principalId = 1L;
    private final Flowable<User> userFlowable;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(AppDatabase.getDatabase(application).userDao());
        userFlowable = userRepository.getUser(principalId);
    }

    public Flowable<User> getUser() {
        return userFlowable;
    }

}
