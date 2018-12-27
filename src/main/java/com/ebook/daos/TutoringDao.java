package com.ebook.daos;

import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;

import java.util.List;

public interface TutoringDao {

    public List<Tutoring> getTutorings(TutoringQuery query);
    public void save(Tutoring tutoring);
    public Tutoring getById(String id);
    public void update(Tutoring tutoring);
    public void delete(TutoringQuery query);
    public void updateOrderUser(TutoringQuery query);
    public void deleteOrderUser(TutoringQuery query);
    public void updateUserScore(TutoringQuery tutoringQuery);
}
