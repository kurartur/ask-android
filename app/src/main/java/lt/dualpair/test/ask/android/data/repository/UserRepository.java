package lt.dualpair.test.ask.android.data.repository;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import lt.dualpair.test.ask.android.data.dao.UserDao;
import lt.dualpair.test.ask.android.data.entity.User;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public Flowable<User> getUser(Long id) {
        return userDao.getUser(id);
    }

    public Completable saveUser(final User user) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                userDao.saveUser(user);
            }
        });
    }
}
