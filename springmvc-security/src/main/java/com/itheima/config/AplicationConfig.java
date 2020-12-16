package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//applicationContext.xml
@Configuration
@ComponentScan(basePackages = "com.itheima", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class AplicationConfig {

}
