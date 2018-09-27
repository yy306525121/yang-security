package cn.codeyang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"cn.codeyang.web", "cn.codeyang.demo", "cn.codeyang.core", "cn.codeyang.app"})
@RestController
@EnableSwagger2
public class YangSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YangSecurityDemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello (){
	    return "hello";
    }
}
