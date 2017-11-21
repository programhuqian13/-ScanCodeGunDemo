package org.tony.scan.code.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0.0
 * @Description 启动类
 * @Date 2017/11/21
 * @Author xuanyi
 * @pageageName is org.tony.scan.code.core
 * @projectName is ScanCodeGunDemo
 */
@SpringBootApplication(scanBasePackages = "org.tony.scan.code")
public class ScanCodeGunServerLunch {

    public static void main(String[] args) {
        SpringApplication.run(ScanCodeGunServerLunch.class,args);
    }

}
