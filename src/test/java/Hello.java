import com.ebook.beans.book.Book;
import com.ebook.daos.BookDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Hello {

    @Autowired
    BookDao bookDao;

    @Test
    public void test(){

        List<Book> list = bookDao.getBooks();
        for(Book book:list){
            System.out.println(book.toString());
        }

    }
}
