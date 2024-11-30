/**
 * SpringBatchApplication.java
 * 30-Nov-2024
 */
package com.abimulia.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author abimu
 *
 * @version 1.0 (30-Nov-2024)
 * @since 30-Nov-2024 10:40:08 AM
 * 
 * 
 * Copyright(c) 2024 Abi Mulia
 */
@SpringBootApplication
public class SpringBatchApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(SpringBatchApplication.class, args)));
	}

}
