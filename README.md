# nrdy loader

*We do what we must because we can, for the good of all of us, except the ones who are dead.* - GLaDOS (Portal) (Portal is a trademark of Valve Corporation)

Java implementation of the `nrdy` human-readable serialization format.

## Contents

- [Features](#features)
- [Quick showcase](#quick-showcase)
- [Installation](#installation)
    - [Gradle](#gradle)
    - [Maven](#maven)
- [Documentation](#documentation)
- [Disclaimer](#disclaimer)

## Features

- Easily convert objects to human-readable strings and vice-versa with minimal performance impact

## Quick showcase

```java
// Create Nrdy instance
Nrdy nrdy = new Nrdy();

// Some object to convert
TestObject testObject = new TestObject();
testObject.fillData();

// Convert object to Nrdy string
String nrdyString = nrdy.convertObjectToNrdyString(testObject);

// Convert Nrdy string back to object
TestObject testObject2 = nrdy.convertNrdyStringToObject(nrdyString, TestObject.class);

// Profit!
```

### Example of Nrdy string

```
This is some string;42;442;true;3.14;This is a nested string;SOME_OTHER_ENUM_VALUE;
```

## Installation
- Java >= 8

### Gradle

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'dev.mayuna:nrdy-loader:0.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>dev.mayuna</groupId>
    <artifactId>nrdy-loader</artifactId>
    <version>0.0.1</version>
</dependency>
```

**You can find the latest version [here](https://mvnrepository.com/artifact/dev.mayuna/console-parallax).**

## Disclaimer
> [!IMPORTANT]  
> Currently not supported types:
>   - Arrays
>   - Collections
>   - Maps
