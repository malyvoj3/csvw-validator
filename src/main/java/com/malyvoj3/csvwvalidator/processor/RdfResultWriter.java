package com.malyvoj3.csvwvalidator.processor;

import com.malyvoj3.csvwvalidator.validation.Severity;
import com.malyvoj3.csvwvalidator.validation.ValidationError;
import com.malyvoj3.csvwvalidator.validation.ValidationStatus;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.vocabulary.DOAP;
import org.apache.jena.sparql.vocabulary.EARL;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.RDF;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RdfResultWriter implements ResultWriter {

    private static final String HOME_PAGE = "https://github.com/malyvoj3";
    private static final String VALIDATOR = "https://github.com/malyvoj3/csvw-validator";
    private static final String VALIDATOR_ISSUES = "https://github.com/malyvoj3/csvw-validator/issues";
    private static final String MIT_LICENSE = "https://choosealicense.com/licenses/mit/";
    private static final String W3C_CSVW_MODEL = "http://www.w3.org/TR/tabular-data-model/";
    private static final String W3C_CSVW_METADATA = "http://www.w3.org/TR/tabular-metadata/";
    private static final String WARNING = "https://github.com/malyvoj3/csvw-validator#warning";
    private static final String STRICT_WARNING = "https://github.com/malyvoj3/csvw-validator#strict-warning";
    private static final String FATAL = "https://github.com/malyvoj3/csvw-validator#fatal";
    private static final String DOAP_IMPLEMENTS = "http://usefulinc.com/ns/doap#implements";
    private static final String STRICT_MODE = "https://github.com/malyvoj3/csvw-validator#strict-mode";

    @Override
    public byte[] writeResult(ProcessingResult processingResult) {
        Model model = createResultModel(processingResult);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        RDFDataMgr.write(outputStream, model, RDFFormat.TURTLE_PRETTY);
        return outputStream.toByteArray();
    }

    private Model createResultModel(ProcessingResult processingResult) {
        Model model = ModelFactory.createDefaultModel();
        createPrefixes(model);
        Resource person = createPerson(model);
        Resource passed = EARL.passed;
        Resource warning = createWarning(model);
        Resource strictWarning = createStrictWarning(model);
        Resource error = EARL.failed;
        Resource fatal = createFatal(model);
        Map<String, Resource> outcomeMap = new HashMap<>();
        outcomeMap.put(ValidationStatus.PASSED.name(), passed);
        outcomeMap.put(Severity.STRICT_WARNING.name(), strictWarning);
        outcomeMap.put(Severity.WARNING.name(), warning);
        outcomeMap.put(Severity.ERROR.name(), error);
        outcomeMap.put(Severity.FATAL.name(), fatal);
        Resource validator = createValidator(model, person);
        createAssertion(processingResult, model, validator, outcomeMap);
        return model;
    }

    private void createAssertion(ProcessingResult result, Model model, Resource validator, Map<String, Resource> outcomeMap) {
        Resource strictMode = model.createResource(STRICT_MODE)
                .addProperty(RDF.type, EARL.TestMode)
                .addProperty(DC.title, model.createLiteral("Strict mode", "en"))
                .addProperty(DC.description, model.createLiteral("This mode enables STRICT_WARNING," +
                        " which validates format of CSV againt RFC 4180", "en"));

        Resource assertion = model.createResource()
                .addProperty(EARL.assertedBy, validator)
                .addProperty(EARL.subject, validator);
        if (result.getTabularUrl() != null) {
            assertion.addProperty(EARL.test, model.createResource(result.getTabularUrl()));
        }
        if (result.getMetadataUrl() != null) {
            assertion.addProperty(EARL.test, model.createResource(result.getMetadataUrl()));
        }
        Resource mainResult = model.createResource()
                .addProperty(RDF.type, EARL.TestResult)
                .addProperty(DC.title, model.createLiteral("Validation result", "en"))
                .addProperty(DC.description, model.createLiteral("Summary result of validation by csvw-validator.", "en"))
                .addProperty(DC.date, model.createTypedLiteral(Calendar.getInstance()))
                .addProperty(EARL.mode, EARL.automatic)
                .addProperty(EARL.outcome, outcomeMap.get(result.getValidationStatus().name()));
        if (result.getSettings().isStrictMode()) {
            mainResult.addProperty(EARL.mode, strictMode);
        }
        assertion.addProperty(EARL.result, mainResult);

        for (ValidationError error : result.getErrors()) {
            Resource errorResult = model.createResource()
                    .addProperty(RDF.type, EARL.TestResult)
                    .addProperty(DC.date, model.createTypedLiteral(Calendar.getInstance()))
                    .addProperty(EARL.mode, EARL.automatic)
                    .addProperty(EARL.info, model.createLiteral(error.getFormattedMessage(), "en"))
                    .addProperty(EARL.outcome, outcomeMap.get(error.getSeverity().name()));
            if (result.getSettings().isStrictMode()) {
                errorResult.addProperty(EARL.mode, strictMode);
            }
            assertion.addProperty(EARL.result, errorResult);
        }
    }

    private void createPrefixes(Model model) {
        model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
        model.setNsPrefix("earl", "http://www.w3.org/ns/earl#");
        model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
        model.setNsPrefix("doap", "http://usefulinc.com/ns/doap#");
        model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
    }

    private Resource createPerson(Model model) {
        Resource person = model.createResource(HOME_PAGE);
        person.addProperty(RDF.type, FOAF.Person)
                .addLiteral(FOAF.name, "Vojtech Maly")
                .addProperty(FOAF.title, model.createLiteral("Implementor", "en"))
                .addProperty(FOAF.homepage, person);
        return person;
    }

    private Resource createWarning(Model model) {
        return model.createResource(WARNING)
                .addProperty(RDF.type, EARL.Pass)
                .addProperty(DC.description, model.createLiteral("Warning", "en"))
                .addProperty(DC.title, model.createLiteral("Warning", "en"));
    }

    private Resource createStrictWarning(Model model) {
        return model.createResource(STRICT_WARNING)
                .addProperty(RDF.type, EARL.Pass)
                .addProperty(DC.description, model.createLiteral("Warning in strict mode", "en"))
                .addProperty(DC.title, model.createLiteral("Strict warning", "en"));
    }

    private Resource createFatal(Model model) {
        return model.createResource(FATAL)
                .addProperty(RDF.type, EARL.Fail)
                .addProperty(DC.description, model.createLiteral("Failed which caused stopping of validating", "en"))
                .addProperty(DC.title, model.createLiteral("Fatal", "en"));
    }

    private Resource createValidator(Model model, Resource person) {
        Resource validator = model.createResource(VALIDATOR);
        validator.addProperty(RDF.type, DOAP.Project)
                .addProperty(RDF.type, EARL.Software)
                .addProperty(RDF.type, EARL.TestSubject)
                .addProperty(RDF.type, EARL.Assertor)
                .addProperty(DC.title, model.createLiteral("csvw-validator", "en"))
                .addProperty(DC.description, model.createLiteral("Java implementation of the W3C CSV on the Web validator.", "en"))
                .addProperty(DOAP.name, model.createLiteral("csvw-validator", "en"))
                .addProperty(DOAP.description, model.createLiteral("Java implementation of the W3C CSV on the Web validator.", "en"))
                .addProperty(DOAP.license, model.createResource(MIT_LICENSE))
                .addProperty(DOAP.developer, person)
                .addProperty(DOAP.documenter, person)
                .addProperty(DOAP.maintainer, person)
                .addProperty(DC.creator, person)
                .addProperty(DOAP.download_page, validator)
                .addProperty(DOAP.homepage, validator)
                .addProperty(DOAP.bug_database, model.createResource(VALIDATOR_ISSUES))
                .addProperty(model.createProperty(DOAP_IMPLEMENTS), model.createResource(W3C_CSVW_MODEL))
                .addProperty(model.createProperty(DOAP_IMPLEMENTS), model.createResource(W3C_CSVW_METADATA))
                .addProperty(FOAF.maker, person);
        return validator;
    }
}
