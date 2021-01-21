package br.com.neppo.kbase.knowledgebase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class KnowledgeBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeBaseApplication.class, args);
	}

}
