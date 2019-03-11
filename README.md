Csvw-validator
=======
Implementation of CSV on the Web validator.

## Requirements
Java - version 8

Maven - version 3.3.9+

## Installation
Run command:
```
mvn clean install
```
which will install the application.

Then start the application with command:
```
mvn spring-boot:run
```

After success application startup, open ```http://localhost:8080/``` in our browser or use ```http://localhost:8080/validate``` endpoint.

## Example

Example of using REST endpoint ```localhost:8080/validate```.
Request body has to be JSON with format:
```
{
    "tabularFileUrl": "urlToTabularDataFile",
    "metadataFilesUrl": ["urlToMetadataFile1", "urlToMetadataFile2"]
}
```


Example request in cURL:
```
curl -X POST \
  http://localhost:8080/validate \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"tabularFileUrl": "test",
	"metadataFilesUrl": ["http://www.w3.org/2013/csvw/tests/test040-metadata.json"]
}'
```

## License
[MIT](https://choosealicense.com/licenses/mit/)