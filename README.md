csvw-validator
=======
csvw-validator is java implementation of W3C CSV on the Web validator.

## Requirements
Java - version 8 (higher versions should be compatible, but are not tested)

Maven - version 3.3.9+

## Installation
Run command:
```
mvn clean install
```
which will install the application with all 3 modules:

* ```csvw-validator-lib``` - module which contains the whole validator logic (including parsing).
* ```csvw-validator-cli-app``` - module with simple command line validator application
* ```csvw-validator-web-app``` - module, which contains Spring Boot application with REST web service and simple Vaadin Web UI.

## Web application

Web application is in module ```csvw-validator-web-app```. After project installation you should open the folder with this module and run command:

```
mvn spring-boot:run
```

or you can take generated JAR (eg. csvw-validator-web-app-0.0.1-SNAPSHOT.jar) and run command:

```
java -jar csvw-validator-web-app-0.0.1-SNAPSHOT.jar
```
Both commands will start the application server on port ```8080```. After startup open ```http://localhost:8080/``` in your browser.
If you want to specify the port, use these command instead:

```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port={port}
java -jar csvw-validator-web-app-0.0.1-SNAPSHOT.jar --server.port={port}
```
where instead ```{port}``` you can insert the port number.

## Web service

Web service is in module ```csvw-validator-web-app```. After project installation you should open the folder with this module and run command:

```
mvn spring-boot:run
```

or you can take generated JAR (eg. csvw-validator-web-app-0.0.1-SNAPSHOT.jar) and run command:

```
java -jar csvw-validator-web-app-0.0.1-SNAPSHOT.jar
```
Both commands will start the application server on port ```8080```. After startup you will have ready REST web service at ```http://localhost:8080/```.
If you want to specify the port, use these command instead:

```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port={port}
java -jar csvw-validator-web-app-0.0.1-SNAPSHOT.jar --server.port={port}
```
where instead ```{port}``` you can insert the port number.

Web service has two endpoints:

* ```/validate``` - for validating one tabular data file and/or metadata file.
* ```/validateBatch``` - for validating multiple files.
* ```/validationResult/{resultId}``` - for obtaining result of validation.

REST API documentation is included in RAML in file ```/resources/validator.raml```.

### Validate endpoint

Endpoint ```/validate``` validates one tabular data file or metadata file or both together.
It receives POST HTTP requests with content-type and accept set to ```application/json```.
So request body has to be JSON with format:
```
{
  "tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
  "metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json",
  "strictMode": false,
  "language": "en"
}
```
Where each property is optional, but at least one of ```tabularUrl``` or ```metadataUrl``` must be specified.

* ```TabularUrl``` is string property for URL of tabular data file.
* ```MetadataUrl``` is string property for URL of metadata file.
* ```StrictMode``` is boolean property, which disable/enable strict mode. By default is strict mode enabled.
In **strict mode** tabular files are validating against **RFC 4180** (CSV comma delimiter, line endings \r\n, UTF-8 encoding,...).
* ```Language``` is code of language in which all error messages should translate. Default is "en", validator also supports "cs".

Response looks like this:
```
{
  "id": "resultId",
  "validationStatus": "ERROR",
  "tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
  "metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json",
  "validationErrors": [
    {
      "severity": "ERROR",
      "message": "Cell (row 2 column 1) cannot be formatted as 'integer' dataype."
    }
  ],
  "warningCount": 0,
  "errorCount": 1,
  "fatalCount": 0,
  "totalErrorsCount": 1
}
```
```Id``` identifier of the result.
```ValidationStatus``` is result of validation - PASSED, WARNING or ERROR.
```TabularUrl``` and ```metadataUrl``` are URL of validated files.
```ValidationErrors``` contains errors created during validation. Each error has severity (WARNING, ERROR, FATAL).
Then there are number of all errors (```totalErrorsCount```), warning errors (```warningCount```), error errorss (```errorCount```) and fatal errors (```fatalCount```).

Example request in cURL:
```
curl -X POST \
  http://localhost:8080/validate \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cbfb628b-0444-48f4-8307-5f8a3d011644' \
  -H 'cache-control: no-cache' \
  -d '{
	"tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
	"metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json",
	"strictMode": false
}'
```
### ValidateBatch endpoint

Endpoint ```/validateBatch``` validates multiple tabular data files and/or metadata files.
It receives POST HTTP requests with content-type and accept set to ```application/json```.
So request body has to be JSON with format:

```
{
  "filesToProcess": [
    {
      "tabularUrl": "http://www.w3.org/2013/csvw/tests/test282.csv",
      "metadataUrl": "http://www.w3.org/2013/csvw/tests/test282-metadata.json"
    },
    {
      "tabularUrl": "http://www.w3.org/2013/csvw/tests/test283.csv"
    },
    {
      "metadataUrl": "http://www.w3.org/2013/csvw/tests/test284-metadata.json"
    },
    {
      "tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
      "metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json"
    }
  ],
  "strictMode": false
  "filesResults": false
}
```
Where ```stricMode``` and ```language``` are the same properties as in ```/validate``` endpoint - it enables strict mode and set language of messages. 
Property ```filesToProcess``` is array property, which contains object with ```tabularUrl``` and/or ```metadataUrl``` as in ```/validate``` endpoint.
Last property is boolean property ```filesResults```, which enables or disables results for each validated file in response.
If it is disabled, only result ID is sent back and not the whole result. By default is set to true.

Response can look like this JSON:

```
{
  "filesCount": 3 ,
  "passedFilesCount": 3 ,
  "warningFilesCount": 0 ,
  "errorFilesCount": 0 ,
  "filesResults": [
    {
      "id": "resultId1"
    },
    {
      "id": "resultId2"
    },
    {
      "id": "resultId3"
    },
    {
      "id": "resultId4"
    }
  ]
}
```

It has property ```filesCount```, which contains number of validated files. 
Then there are counters for passed (```passedFileCount```), warning (```warningFileCount```) and error (```errorFileCount```) files.
For each validated files, there is resultId and ValidationResponse (object from ```/validate``` endpoint) in ```filesResults```. 
In case when property ```filesResults``` was set to false in the request, there are just resultIds and not ValidationResponses.

In project there is Shell script ```batchValidate.sh``` which runs batchValidation with over 1000 files.

### ValidationResult endpoint

Endpoint ```/validationResult/{resultId}``` returns result in JSON format as in ```/validate``` endpoint.
If result with given ID doesn't exists, endpoint will return 404 response code.

## Command-line application

Command-line application is located in module ```csvw-validator-cli-app```.
Take generated JAR (eg. ```csvw-validator-cli-app-0.0.1-SNAPSHOT.jar```.) and start the application with command:
```
java -jar csvw-validator-cli-app-0.0.1-SNAPSHOT.jar [-f <FILE>] [-s <SCHEMA>]
[-o <OUTPUT>] [--strict] [-h] [--rdf] [--csv] 

 -f,--file <FILE>       The CSV file URL to be processed
 -s,--schema <SCHEMA>   The CSVW schema URL to be processed
 -o,--output <OUTPUT>   The name of output file without suffix
    --not-strict        Disables strict mode
    --csv               Validator will generate output also in CSV format.
    --rdf               Validator will generate output also in RDF format
 -h,--help              Show help    
```

At least FILE or SCHEMA must be specified.
Default file name (path) of output file is ```result```. Command-line application will print result of validating to output and also create
default text result file (eg. ```result.txt```). 
If --rdf or --csv arguments are specified, then validator will create extra files with these formats (eg. ```result.ttl``` and ```result.csv```).
If OUTPUT is specified, then it is used as output file instead ```result```.
Option ```--not-strict``` disables the strict mode.

Example: For validating by command line with these arguments:
```
java -jar validator.jar -f http://www.w3.org/2013/csvw/tests/test286.csv
 -s http://www.w3.org/2013/csvw/tests/test286-metadata.json --rdf --csv -o ../newResult
```

text result is:

```
Tabular URL: http://www.w3.org/2013/csvw/tests/test286.csv
Metadata URL: http://www.w3.org/2013/csvw/tests/test286-metadata.json
Result: ERROR
Strict mode: false
Total errors: 1
Warning errors: 0
Error errors: 1
Fatal errors: 0
Errors:
  ERROR: Cell (row 2 column 1) cannot be formatted as 'integer' datatype.
```

which will be printed to output and also it will be in file newResults.txt generated in parent folder.

## Use validator as a library

Module ```csvw-validator-lib``` can be used as validator. 
If you use Maven you can easily add dependency, but first you need to add ```maven-dependency-plugin```.

```
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-dependency-plugin</artifactId>
      <dependencies>
        <dependency>
          <groupId>org.apache.felix</groupId>
          <artifactId>maven-bundle-plugin</artifactId>
          <version>2.4.0</version>
          <type>maven-plugin</type>
        </dependency>
      </dependencies>
      <extensions>true</extensions>
    </plugin>
  </plugins>
</build>
```

then you have to add dependencies:

```
<dependencies>
  <dependency>
    <groupId>com.malyvoj3</groupId>
    <artifactId>csvw-validator-lib</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.0.8.RELEASE</version>
  </dependency>
</dependencies>
```

Than you can use classes from ```csvw-validator-lib```. But this module uses Spring framework for **dependency injection**, so you have to create
the whole dependencies between class instances or use Spring. If you want for example just class ```CsvwProcessor```, which is main class for validating,
you can get it with all its dependencies with this code (which is also used in command-line application):

```
ApplicationContext ctx = new AnnotationConfigApplicationContext(ProcessorConfig.class);
CsvwProcessor processor = ctx.getBean(CsvwProcessor.class);
// processor is the Spring bean with all dependencies
```

## Source code documentation

You can generate HTML with command:

```
mvn javadoc:javadoc
```

After that, each module will have HTML documentation in ```/target/site``` folder.

## License
[MIT](https://choosealicense.com/licenses/mit/) 