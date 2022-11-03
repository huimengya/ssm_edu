import com.wyf.domain.Test;
import com.wyf.service.TestService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-service.xml"})
public class test {

    @Autowired
    private TestService testService;

    @org.junit.Test
    public void TestService(){
        List<Test> all = testService.findAll();
        for (Test test : all) {
            System.out.println(test);
        }
    }

}
