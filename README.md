# Welcome to the Rome 2 construction site

This is a temporary repository for initial discussions and development of the new version of [Rome](https://github.com/rometools/rome). It will be merged into the main repository once we're ready to ship an alpha version.

## Why exactly are we doing this?
See https://github.com/rometools/rome/issues/353 for the explanation and a rough plan.

## Progress

### Discussions
 - [x] Java 8 ([#2](https://github.com/rometools/rome2/issues/2), decided: support both 8 and 7/android)
 - [x] Support of old standards ([#3](https://github.com/rometools/rome2/issues/3), decided: :+1:) 
 - [x] Single artifact ([#4](https://github.com/rometools/rome2/issues/4), decided: :+1:)
 - [x] Zero dependencies ([#5](https://github.com/rometools/rome2/issues/5), decided: :+1: except JSON)
 - [x] Suggested API ([#6](https://github.com/rometools/rome2/issues/6), decided: :+1:)
 - [x] Feed fetching ([#7](https://github.com/rometools/rome2/issues/7), decided: :-1:)
 - [x] Code generation ([#8](https://github.com/rometools/rome2/issues/8), decided: :+1:)
 - [x] Absolute leniency ([#9](https://github.com/rometools/rome2/issues/9), decided: :+1:)

### Prototype
 - [x] Initial suggestion ([#10](https://github.com/rometools/rome2/issues/10))

### Try it out

See [RomeTest.java](throwaway-prototype/core/src/test/java/com/rometools/rome/RomeTest.java) for general overview of the functionality implemented so far.

Install locally:
```
git clone git@github.com:rometools/rome2.git
cd rome2/throwaway-prototype
mvn install
```

Add the dependency to your project:
```
<dependency>
  <groupId>com.rometools.rome2.prototype</groupId>
  <artifactId>core</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Use it:
```
byte[] source = ...
Feed feed = Rome.minimal().read(source);
System.out.println(feed.getTitle());
```
