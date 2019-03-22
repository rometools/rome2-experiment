.PHONY: build test model

MAVEN = $(shell mvn --version >/dev/null 2>&1 && echo "mvn")

ifeq ($(MAVEN),)
  $(error "Please install maven")
endif

all: build

build:
	$(MAVEN) verify

test:
	$(MAVEN) test

generate:
	$(MAVEN) package -am -pl factory
