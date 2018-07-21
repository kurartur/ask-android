package com.ask.android.ui;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ask.android.data.entity.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    private SplashViewModel viewModel;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        subscribeUi();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }


    private void subscribeUi() {
        disposable.add(viewModel.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(2, TimeUnit.SECONDS)
                .firstOrError()
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        startActivity(MainActivity.createIntent(SplashActivity.this));
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        startActivity(LoginActivity.createIntent(SplashActivity.this));
                    }
                }));
    }

    public static Intent createIntent(Activity activity) {
        return new Intent(activity, SplashActivity.class);
    }
}
