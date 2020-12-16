package com.itheima.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.itheima.config.AplicationConfig;
import com.itheima.config.WebConfig;

public class SpringApplicationInit extends AbstractAnnotationConfigDispatcherServletInitializer {
	// 相当于加载application.xml
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AplicationConfig.class };
	}

	// 相当于加载springmvc.xml
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	// url-mapping
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

}
