package com.ebook.serviceImpls;

import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;
import com.ebook.daos.TutoringDao;
import com.ebook.services.TutoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutoringServiceImpl implements TutoringService {
    @Autowired
    TutoringDao tutoringDao;

    @Override
    public List<Tutoring> getTutoring() {
        return null;
    }

    @Override
    public void save(Tutoring tutoring) {

    }

    @Override
    public Tutoring getById(TutoringQuery query) {
        return null;
    }

    @Override
    public void update(Tutoring tutoring) {

    }

    @Override
    public void delete(TutoringQuery query) {

    }
}
