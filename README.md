# Spring Batch
[![Spring Boot v3.3.5](https://img.shields.io/badge/Java-SpringBoot-green)](https://spring.io/)
[![License](http://img.shields.io/:license-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0.html)

This project created by `start.spring.io` contain [Spring Boot](https://spring.io/) version 3.3.5.


Code in this project based on the guide [Spring - Creating a Batch Service](https://spring.io/guides/gs/batch-processing)

Simple spring batch service to get data from csv file, convert the data and then store the converted data to database in this project MySQL database.
MySQL database automaticaly created by spring using docker compose.

![spring-batch](https://github.com/user-attachments/assets/ce9b7275-a246-423d-9530-515d8d8f8df9)

Sample data and schema sql are provided, sample data create using ChatGPT

## Development server

Run `mvn spring-boot:run` for a dev server. 

![image](https://github.com/user-attachments/assets/9ab9c256-5a0e-46c5-a1eb-f632fd064fb5)

![image](https://github.com/user-attachments/assets/a15e7ef0-d1ac-4d54-a611-00fc84b4336e)

## Help


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.0/maven-plugin/build-image.html)
* [Docker Compose Support](https://docs.spring.io/spring-boot/3.4.0/reference/features/dev-services.html#features.dev-services.docker-compose)
* [Spring Batch - Reference Documentation](https://docs.spring.io/spring-batch/docs/5.0.x/reference/html/index.html)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.

However, no services were found. As of now, the application won't start!

Please make sure to add at least one service in the `compose.yaml` file.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.