.PHONY: build test model

MAVEN = $(shell mvn --version >/dev/null 2>&1 && echo "mvn")
CURRENT_DIRECTORY = $(shell pwd)

ifeq ($(MAVEN),)
  $(error "Please install maven")
endif

all: build

build:
	$(MAVEN) verify

test:
	$(MAVEN) test

model:
	$(MAVEN) compile exec:java -pl model -Dexec.args="$(CURRENT_DIRECTORY)" -Dexec.mainClass="com.rometools.rome.model.Main"
