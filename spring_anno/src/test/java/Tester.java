import com.bjpowernode.service.AccountService;
import com.bjpowernode.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Tester {
    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Test
    public void test01() throws SQLException {
        List list = productService.queryList();
        System.out.println(list);
    }

    @Test
    public void test02() {
        accountService.transfer("tom", "jack", 100);
    }
}
