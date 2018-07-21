package com.ask.android.ui;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ask.android.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    private LoginViewModel viewModel;

    private EditText name;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        name = findViewById(R.id.name);
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable disposable = viewModel.login(name.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action() {
                        @Override
                        public void run() throws Exception {
                            startActivity(SplashActivity.createIntent(LoginActivity.this));
                        }
                    });
                LoginActivity.this.disposable.add(disposable);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }


    public static Intent createIntent(Activity activity) {
        return new Intent(activity, LoginActivity.class);
    }
}
