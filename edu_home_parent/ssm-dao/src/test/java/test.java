import com.wyf.dao.CourseMapper;
import com.wyf.dao.TestDao;
import com.wyf.domain.Course;
import com.wyf.domain.CourseVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml"})
public class test {

    @Autowired
    private TestDao testDao;

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void TestDao(){
       // List<com.wyf.domain.Test> all = testDao.findAll();
    /*    for (com.wyf.domain.Test test : all) {
            System.out.println(test);
    }*/
     //   CourseVO courseById = courseMapper.findCourseById(7);
      //  System.out.println(courseById);

        // 课程状态测试
        Course course = new Course();
        course.setId(7);
        course.setStatus(0);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }


    public void testService(){

    }


}
