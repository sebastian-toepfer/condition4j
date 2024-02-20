[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=sebastian-toepfer_condition4j)

# Condition4J
is a small Java library. It aims to improve the way we write preconditions in Java.

It is strongly inspired by hamcrest. I decided to write my own code because I like hamcrest for my tests and I want to
avoid possible conflicts between production and test code.

## How to use

### maven-dependency
api
```xml
<dependency>
    <groupId>io.github.sebastian-toepfer.common</groupId>
    <artifactId>condition4j-api</artifactId>
    <version>0.1.0</version>
</dependency>
```

core
```xml
<dependency>
    <groupId>io.github.sebastian-toepfer.common</groupId>
    <artifactId>condition4j-core</artifactId>
    <version>0.1.0</version>
</dependency>
```

### samples

as a small wrapper around `java.util.function.Predicate`
```java
assertThat(new PredicateCondition<String>(s -> s.contains("test")).isFulfilledBy("atestb"), is(true));
```
instead of test, we use the method `isFulfilledBy`.

allow every thing
```java
new NoCondition().isFulfulledBy("everyThing");
```

to negate
```java
new Not(new NoCondition());
```

is now the same as
```java
new UnfulfillableCondition();
```

to check elements in a collection/iterable if they fulfill a condition
```
assertThat(
    new ContainsOnlyItemsWhichFulfilThe<String>(new PredicateCondition<>(s -> s.contains("a")))
        .isFulfilledBy(List.of("athlete", "whale", "football")),
    is(true)
);
```

to combine multipe conditions via and
```java
assertThat(
    new AllOf<String>(
        new PredicateCondition<>(s -> s.contains("a")),
        new PredicateCondition<>(s -> s.contains("c"))
    )
        .isFulfilledBy("abc"),
    is(true)
);
```

to combine multipe conditions via or
```java
assertThat(
    new AnyOf<String>(
        new PredicateCondition<>(s -> s.contains("z")),
        new PredicateCondition<>(s -> s.contains("c"))
    )
        .isFulfilledBy("abc"),
    is(true)
);
```

to map a value first and then check
```
assertThat(
    new MappedToFullfillThe<Class<?>, String>(
        new PredicateCondition<String>(a -> a.equals("List")),
        cls -> cls.getSimpleName()
    )
        .isFulfilledBy(List.class),
    is(true)
);
```

### json
#### maven dependency
```
<dependency>
    <groupId>io.github.sebastian-toepfer.common</groupId>
    <artifactId>condition4j-json-p</artifactId>
    <version>0.1.0</version>
</dependency>
```

check type of a value
```java
assertThat(
  new JsonValueOfType(JsonValue.ValueType.STRING).isFulfilledBy(Json.createValue("test")),
  is(true)
);
```

check a property
```java
assertThat(
    new JsonPropertyWhichFulfilThe("name", new JsonValueOfType(JsonValue.ValueType.STRING))
        .isFulfilledBy(Json.createObjectBuilder().add("name", "jane").build()),
    is(true)
);
```