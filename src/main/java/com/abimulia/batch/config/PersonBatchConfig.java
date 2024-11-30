/**
 * PersonJobConfig.java
 * 30-Nov-2024
 */
package com.abimulia.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.abimulia.batch.listener.JobCompletionListener;
import com.abimulia.batch.model.Person;
import com.abimulia.batch.model.PersonData;
import com.abimulia.batch.proc.PersonItemProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author abimu
 *
 * @version 1.0 (30-Nov-2024)
 * @since 30-Nov-2024 10:24:29 AM
 * 
 * 
 *        Copyright(c) 2024 Abi Mulia
 */
@Configuration
@Slf4j
public class PersonBatchConfig {

	/**
	 * reader() creates an ItemReader. It looks for a file called data.csv and
	 * parses each line item with enough information to turn it into a Person.
	 */
	@Bean
	public FlatFileItemReader<PersonData> reader() {
		log.info("reader() personItemReader");
		return new FlatFileItemReaderBuilder<PersonData>().name("personItemReader")
				.resource(new ClassPathResource("data.csv")).delimited().names("name", "email", "dob", "age")
				.targetType(PersonData.class).build();
	}

	/**
	 * processor() creates an instance of the PersonItemProcessor, meant to convert
	 * the name to upper case and email to lower case.
	 */
	@Bean
	public PersonItemProcessor processor() {
		log.info("processor()");
		return new PersonItemProcessor();
	}

	/**
	 * @param dataSource
	 * @return
	 * 
	 *         writer(DataSource) creates an ItemWriter. This one is aimed at a JDBC
	 *         destination and automatically gets a copy of the dataSource created
	 *         by Spring Boot. It includes the SQL statement needed to insert a
	 *         single Person, driven by Java record components.
	 */
	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
		log.info("writer() dataSource: " + dataSource);
		return new JdbcBatchItemWriterBuilder<Person>()
				.sql("INSERT INTO person (name, email, dob, age) VALUES (:name, :email, :dob, :age)")
				.dataSource(dataSource).beanMapped().build();

	}

	/**
	 * 
	 * This method defines the job.
	 * 
	 * @param jobRepository
	 * @param step1
	 * @param listener
	 * @return
	 */
	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionListener listener) {
		log.debug("importUserJob() jobRepository: " + jobRepository + " step: " + step1 + " listener: " + listener);
		return new JobBuilder("importUserJob", jobRepository).listener(listener).start(step1).build();
	}

	/**
	 * This method one defines a single step. Jobs are built from steps, where each
	 * step can involve a reader, a processor, and a writer.
	 * 
	 * @param jobRepository
	 * @param transactionManager
	 * @param reader
	 * @param processor
	 * @param writer
	 * @return
	 */
	@Bean
	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			FlatFileItemReader<PersonData> reader, PersonItemProcessor processor, JdbcBatchItemWriter<Person> writer) {
		log.debug("step1() jobRepository: " + jobRepository + "");
		return new StepBuilder("step1", jobRepository).<PersonData, Person>chunk(3, transactionManager).reader(reader)
				.processor(processor).writer(writer).build();
	}

}
