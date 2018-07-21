package lt.dualpair.test.ask.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_FRAGMENT = "MainFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(MAIN_FRAGMENT) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(android.R.id.content, MainFragment.newInstance(), MAIN_FRAGMENT);
            ft.commit();
        }
    }

    public static Intent createIntent(Activity activity) {
        return new Intent(activity, MainActivity.class);
    }
}
