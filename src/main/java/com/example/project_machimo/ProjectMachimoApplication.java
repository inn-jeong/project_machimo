package com.example.project_machimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@SpringBootApplication
//@Configuration
@EnableScheduling
public class ProjectMachimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMachimoApplication.class, args);
	}


//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource);
//		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
//		sqlSessionFactoryBean.setConfiguration(mybatisConfig()); //추가
//		return sqlSessionFactoryBean.getObject();
//	}

	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}

//	파일 업로드 관련
//	@Bean
//	public CommonsMultipartResolver  multipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		multipartResolver.setDefaultEncoding("UTF-8"); // 파일 인코딩 설정
//		multipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024); // 파일당 업로드 크기 제한 (5MB)
//		return multipartResolver;
//	}

	// StandardServletMultipartResolver를 새로운 빈으로 등록합니다.
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
