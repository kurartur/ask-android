package com.ask.android.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ask.android.data.entity.Question;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface QuestionDao {

    @Insert
    void save(Question question);

    @Query("SELECT * FROM questions ORDER BY date DESC")
    List<Question> getAll();

    @Query("SELECT * FROM questions ORDER BY date DESC")
    Flowable<List<Question>> getAllFlowable();

    @Insert
    void insertAll(List<Question> questions);

    @Query("SELECT * FROM questions WHERE id=:questionId")
    Flowable<Question> getQuestionFlowable(Long questionId);
}
