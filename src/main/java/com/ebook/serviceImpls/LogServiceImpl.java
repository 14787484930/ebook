package com.ebook.serviceImpls;

import com.ebook.beans.log.Log;
import com.ebook.beans.log.LogQuery;
import com.ebook.daos.LogDao;
import com.ebook.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;

    @Override
    public List<Log> getLog() {
        return null;
    }

    @Override
    public void save(Log log) {

    }

    @Override
    public Log getById(LogQuery query) {
        return null;
    }

    @Override
    public void update(Log other) {

    }

    @Override
    public void delete(LogQuery query) {

    }
}
