package lt.dualpair.test.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import lt.dualpair.test.ask.android.data.AppDatabase;
import lt.dualpair.test.ask.android.data.entity.User;
import lt.dualpair.test.ask.android.data.repository.UserRepository;

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
