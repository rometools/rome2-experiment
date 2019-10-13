# OBSOLETE

&#x26a0;&#xfe0f; The project is abandoned, see [#25](https://github.com/rometools/rome2-experiment/issues/25) for the details &#x26a0;&#xfe0f; 

This is a temporary repository for initial discussions and development of the new version of [Rome](https://github.com/rometools/rome). It will be merged into the main repository once we're ready to ship an alpha version.

## Why exactly are we doing this?
See https://github.com/rometools/rome/issues/353 for the explanation and a rough plan.

## Progress

### Discussions
 - [x] Java 8 ([#2](https://github.com/rometools/rome2-experiment/issues/2), decided: support both 8 and 7/android)
 - [x] Support of old standards ([#3](https://github.com/rometools/rome2-experiment/issues/3), decided: :+1:) 
 - [x] Single artifact ([#4](https://github.com/rometools/rome2-experiment/issues/4), decided: :+1:)
 - [x] Zero dependencies ([#5](https://github.com/rometools/rome2-experiment/issues/5), decided: :+1: except JSON)
 - [x] Suggested API ([#6](https://github.com/rometools/rome2-experiment/issues/6), decided: :+1:)
 - [x] Feed fetching ([#7](https://github.com/rometools/rome2-experiment/issues/7), decided: :-1:)
 - [x] Code generation ([#8](https://github.com/rometools/rome2-experiment/issues/8), decided: :+1:)
 - [x] Absolute leniency ([#9](https://github.com/rometools/rome2-experiment/issues/9), decided: :+1:)

### Prototype

See https://github.com/rometools/rome2-experiment/projects/1

 - [x] Initial suggestion ([#10](https://github.com/rometools/rome2-experiment/issues/10))
 - [x] Code generation for main entities ([#12](https://github.com/rometools/rome2-experiment/issues/12))
 - [x] Code generation for XML parser ([#13](https://github.com/rometools/rome2-experiment/issues/13))
 - [ ] Json Feed parser ([#14](https://github.com/rometools/rome2-experiment/issues/14))
 - [x] More fields of different types ([#15](https://github.com/rometools/rome2-experiment/issues/15))
 - [ ] RSS writer ([#16](https://github.com/rometools/rome2-experiment/issues/16))
 - [ ] Json Feed writer ([#17](https://github.com/rometools/rome2-experiment/issues/17))
 - [ ] Support parsing one of older versions ([#18](https://github.com/rometools/rome2-experiment/issues/18))
 - [ ] Extensions ([#19](https://github.com/rometools/rome2-experiment/issues/19))
 - [ ] Features ([#20](https://github.com/rometools/rome2-experiment/issues/20))
 - [ ] Rome 1 converter ([#21](https://github.com/rometools/rome2-experiment/issues/21))
 - [ ] Separate artifact for Android ([#22](https://github.com/rometools/rome2-experiment/issues/22))
 - [x] Atom parser ([#24](https://github.com/rometools/rome2-experiment/issues/24))

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
