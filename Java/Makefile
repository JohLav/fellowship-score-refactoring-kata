JAVA_IMAGE  = maven:3.9-eclipse-temurin-17
MVN         = docker run --rm -v $(PWD):/app -w /app -v $(HOME)/.m2:/root/.m2 $(JAVA_IMAGE) mvn

.PHONY: install test test-coverage

install:
	$(MVN) install -DskipTests

test:
	$(MVN) test

test-coverage:
	$(MVN) verify
