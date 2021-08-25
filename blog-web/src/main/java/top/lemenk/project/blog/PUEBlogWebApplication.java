package top.lemenk.project.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lemenk@163.com
 * @date 2021/8/24 14:55
 * @className PUEBlogWebApplication
 * @desc
 */
@SpringBootApplication
@EnableSwagger2
public class PUEBlogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(PUEBlogWebApplication.class, args);
    }
}
