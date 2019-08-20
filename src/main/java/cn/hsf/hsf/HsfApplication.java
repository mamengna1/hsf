package cn.hsf.hsf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.hsf.hsf.mapper")
@SpringBootApplication
public class HsfApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsfApplication.class, args);
    }

}
