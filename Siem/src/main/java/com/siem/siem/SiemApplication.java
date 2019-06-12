package com.siem.siem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

@SpringBootApplication
public class SiemApplication extends SpringBootServletInitializer {
	
	private static Logger log = LoggerFactory.getLogger(SiemApplication.class);
	
	public static Map<String, KieSession> allSessions = new HashMap<String, KieSession>();
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SiemApplication.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		StringBuilder sb = new StringBuilder("Application beans:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		log.info(sb.toString());
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SiemApplication.class);
	}
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("siem.rule.engine", "siem-center", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}
}
