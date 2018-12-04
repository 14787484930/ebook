package com.ebook.services;

import com.ebook.beans.log.Log;
import com.ebook.beans.log.LogQuery;

import java.util.List;

public interface LogService {
    public List<Log> getLog();
    public void save(Log log);
    public Log getById(LogQuery query);
    public void update(Log other);
    public void delete(LogQuery query);
}
