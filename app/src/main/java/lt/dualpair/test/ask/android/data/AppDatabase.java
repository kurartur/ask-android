package lt.dualpair.test.ask.android.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import lt.dualpair.test.ask.android.data.dao.QuestionDao;
import lt.dualpair.test.ask.android.data.dao.UserDao;
import lt.dualpair.test.ask.android.data.entity.Question;
import lt.dualpair.test.ask.android.data.entity.User;

@Database(entities = {
        User.class, Question.class
}, version = 1, exportSchema = false)
@TypeConverters({
   DateTypeConverter.class
})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Ask";

    public abstract UserDao userDao();

    public abstract QuestionDao questionDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            //.fallbackToDestructiveMigration()
                            //.allowMainThreadQueries()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {

                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void reset() {
        INSTANCE.clearAllTables();
    }
}
