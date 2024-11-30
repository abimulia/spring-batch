/**
 * PersonItemProcessor.java
 * 30-Nov-2024
 */
package com.abimulia.batch.proc;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.abimulia.batch.model.Person;
import com.abimulia.batch.model.PersonData;

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
@Slf4j
public class PersonItemProcessor implements ItemProcessor<PersonData, Person> {

	@Override
	public Person process(final PersonData personData) throws Exception {
		log.debug("process() person: " + personData);
		final String name = personData.name().toUpperCase();
		final String email = personData.email().toLowerCase();
		final String dob = personData.dob();
		final int age = personData.age();
		final Person transormedPerson = new Person(name, email, dob, age);
		log.info("Converting {} into {}", personData, transormedPerson);
		return transormedPerson;
	}

}
