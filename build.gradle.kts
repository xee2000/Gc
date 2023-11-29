plugins {
	java
	war
	id("org.springframework.boot") version "2.4.3"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.assembble"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4") // Use a compatible version
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("org.apache.tomcat.embed:tomcat-embed-jasper")
	implementation("org.glassfish.jaxb:jaxb-runtime:2.3.2")
	implementation("javax.servlet:jstl:1.2")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("com.googlecode.json-simple:json-simple:1.1.1")

	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.projectlombok:lombok:1.18.20")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:2.2.0") // Use a compatible version
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder = "paketobuildpacks/builder-jammy-base:latest"
}
