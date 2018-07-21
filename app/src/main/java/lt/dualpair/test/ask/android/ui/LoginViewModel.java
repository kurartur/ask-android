package lt.dualpair.test.ask.android.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.reactivex.Completable;
import lt.dualpair.test.ask.android.data.AppDatabase;
import lt.dualpair.test.ask.android.data.entity.User;
import lt.dualpair.test.ask.android.data.repository.UserRepository;

public class LoginViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(AppDatabase.getDatabase(application).userDao());
    }

    public Completable login(String name) {
        User user = new User();
        user.setId(1L);
        user.setName(name);
        return userRepository.saveUser(user);
    }
}
