/**
 * JobCompletionListener.java
 * 30-Nov-2024
 */
package com.abimulia.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.abimulia.batch.model.Person;

import lombok.extern.slf4j.Slf4j;

/**
 * @author abimu
 *
 * @version 1.0 (30-Nov-2024)
 * @since 30-Nov-2024 10:40:08 AM
 * 
 * 
 * Copyright(c) 2024 Abi Mulia
 */

@Component
@Slf4j
public class JobCompletionListener implements JobExecutionListener {

	private final JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 */
	public JobCompletionListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * The JobCompletionNotificationListener listens for when a job is
	 * BatchStatus.COMPLETED and then uses JdbcTemplate to inspect the results.
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		log.debug("afterJob() jobExecution: " + jobExecution);
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			jdbcTemplate.query("SELECT name, email,dob,age FROM person", new DataClassRowMapper<>(Person.class))
					.forEach(person -> log.info("Found <{{}}> in the database.", person));
		}
	}

}
