# TestRail-API Plugin
--------------------------

Based of [TestRail SDK](http://docs.gurock.com/testrail-api2/start).

[![Sonatype Maven Repository](https://maven-badges.herokuapp.com/maven-central/io.github.artur3280/TestRail-Api/badge.svg)](https://search.maven.org/artifact/io.github.artur3280/TestRail-Api)
![TestRail v4.1](https://img.shields.io/badge/TestRail-v4.1-blue.svg)
[![TestRail v4.1](https://img.shields.io/badge/TestRail%20API-v2-orange.svg)](http://docs.gurock.com/testrail-api2/start)

[Old API (aka Mini API)](http://docs.gurock.com/testrail-api/start) is not supported. Please note that you may not be able to use some API features supported by this library depending on the TestRail version you use. Similarly, since this is not an official library, API updates in future versions of TestRail may not be supported immediately with the release of new version or may need an incompatible major version change.

## More info about, how to use it 
--------------
### Apache Maven
```xml
<dependency>
<groupId>io.github.artur3280</groupId>
<artifactId>TestRail-Api</artifactId>
<version>${stable.version.shown.above}</version>
</dependency>
```
### Gradle Groovy DSL
```java
implementation 'io.github.artur3280:TestRail-Api:0.5.0'
```


### Example Usage
```java
// create a TestRail instance with properties 
TestRail testRail = new TestRail(new Credentials("./tr.properties"));

// create a TestRail instance with request logger
TestRail testRail = new TestRail(new Credentials("./tr.properties"), Level.DEBUG);

// create a TestRail instance with the Cred object
Credentials credentials = new Credentials();
credentials.setUsername("");
credentials.setPassword("");
credentials.setBaseUrl("");
credentials.setAppName("");
TestRail testRail = new TestRail(credentials);

// You can: Add(), Update(), Delete(), Get(single), List()
testRail.sections().list(pId, sId).execute();
testRail.cases().list(pId, sId, customCaseFields).execute()
testRail.runs().list(pId).queryParam("is_completed", 1).execute()
testRail.cases().add(sectionId, new Case()..., customCaseFields).execute()
List<CaseField> customCaseFields = testRail.caseFields().list().execute();
List<ResultField> customResultFields = testRail.resultFields().list().execute();

// Also you can map response to your object(as you need)
List<Result> t = testRail.results()
    .addForCases(run.getId(), resultsList, customResultFields)
    .execAs().as(new TypeReference<List<Result>>() {});

t.forEach(r->{
  System.out.println(r.getId());
});
```

#### At the moment, requests have been made to:_Cases,Projects,Results,Runs,Sections, Suites_ 

### Version 0.6.1
In the new version of this plugin, you can backup data to local storage. This should be useful 
for generating artifacts in docker to manually start sending data to the server.

```java
Backup backup = new Backup(resultList);
// Save by default path './artifact/data_backup.json'
backup.saveToLocal();

// Save by new path
backup.saveToLocal("./new_path");

// Save by new path and with new name
backup.saveToLocal("./new_path", "new_file");

///Also you can convert the json from file to the Object
List<Result> test = backup.asObject(new TypeReference<List<Result>>() {});
Result test = backup.asObject(Result.class);

```

