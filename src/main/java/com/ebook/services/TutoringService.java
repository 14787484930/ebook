package com.ebook.services;

import com.ebook.beans.book.BookQuery;
import com.ebook.beans.reportuser.ReportUser;
import com.ebook.beans.tutoring.Tutoring;
import com.ebook.beans.tutoring.TutoringQuery;
import com.ebook.beans.user.User;

import java.util.List;

public interface TutoringService {

    public List<Tutoring> getTutorings(TutoringQuery query);
    public List<Tutoring> getTutoringsByUser(TutoringQuery query);
    public void save(Tutoring tutoring);
    public Tutoring getById(String id);
    public void update(Tutoring tutoring);
    public void delete(TutoringQuery query);
    public void updateOrderUser(TutoringQuery query);
    public void deleteOrderUser(TutoringQuery query);
    public void updateUserScore(User user, ReportUser reportUser);
    public void updateWarning(TutoringQuery query); //系统标记预警信息
    public void updateDel(TutoringQuery query); //管理员处理预警信息
    public void updateViews(TutoringQuery query); //浏览量的统计
}
