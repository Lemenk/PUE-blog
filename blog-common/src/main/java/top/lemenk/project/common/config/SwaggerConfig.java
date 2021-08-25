package top.lemenk.project.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.net.Inet4Address;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Author lemenk@163.com
 * @Created Date: 2021/1/25 16:32
 * @ClassName SwaggerConfig
 * @Remark
 */
@Configuration
public class SwaggerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Lemenk")
                //是否启动Swagger环境
                .enable(flag)
                .select()
                /**
                 * RequestHandlerSelectors：配置要扫描接口的方式
                 *      basePackage：指定要扫描的包
                 *      any()：扫描全部
                 *      none()：全部不扫描
                 *      withClassAnnotation：扫描类上的注解
                 *      withMethodAnnotation：扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.basePackage("top.lemenk.project.yyx.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //配置Swagger信息：apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact contact = new Contact("Lemenk", "https://www.lemenk.top", "Lemenk@163.com");

        return new ApiInfo(
                "Lemenk的SwaggerAPI文档",
                "这是第一个swaggerAPI示例",
                "v1.0",
                "https://www.lemenk.top",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            //获取IP
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            //获取端口号
            int port = event.getWebServer().getPort();
            //获取应用名
            String applicationName = event.getApplicationContext().getApplicationName();
            logger.info("项目启动启动成功！接口文档地址: http://"+hostAddress+":"+event.getWebServer().getPort()+applicationName+"/swagger-ui.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
