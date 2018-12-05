package com.ebook.daos;

import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;

import java.util.List;

public interface TutoringDao {

    public List<Tutoring> getTutoring();
    public void save(Tutoring tutoring);
    public Tutoring getById(TutoringQuery query);
    public void update(Tutoring tutoring);
    public void delete(TutoringQuery query);
}