package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.processor.*;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class CsvwValidatorConsoleApplication implements CommandLineRunner {

    private static final String HEADER = "Validate CSV file, W3C CSV on the Web schema or both.";
    private static final String FOOTER = "Please report issues at http://github.com/malyvoj3/csvw-validator/issues";
    private static final String RESULT_FILE_NAME_DEFAULT = "result";

    private final ApplicationContext appContext;
    private final CsvwProcessor csvwProcessor;
    private final CsvResultWriter csvWriter;
    private final RdfResultWriter rdfWriter;
    private final TextResultWriter textWriter;

    @Autowired
    public CsvwValidatorConsoleApplication(ApplicationContext appContext,
                                           CsvwProcessor csvwProcessor,
                                           CsvResultWriter csvWriter,
                                           RdfResultWriter rdfWriter,
                                           TextResultWriter textWriter) {
        this.appContext = appContext;
        this.csvwProcessor = csvwProcessor;
        this.csvWriter = csvWriter;
        this.rdfWriter = rdfWriter;
        this.textWriter = textWriter;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            CommandLineParser parser = new DefaultParser();
            HelpFormatter formatter = new HelpFormatter();
            Options options = createOptions();
            try {
                CommandLine line = parser.parse(options, args);
                if (line.hasOption('h')) {
                    formatter.printHelp("java -jar validator.jar", HEADER, options, FOOTER, true);
                }
                ProcessingResult result = validate(line.getOptionValue('f'), line.getOptionValue('s'),
                        line.hasOption("strict"));
                String fileName = line.hasOption('o') ? line.getOptionValue('o') : RESULT_FILE_NAME_DEFAULT;
                if (line.hasOption("rdf")) {
                    byte[] rdfResult = rdfWriter.writeResult(result);
                    FileUtils.writeByteArrayToFile(new File(fileName + ".ttl"), rdfResult);
                }
                if (line.hasOption("csv")) {
                    byte[] csvResult = csvWriter.writeResult(result);
                    FileUtils.writeByteArrayToFile(new File(fileName + ".csv"), csvResult);
                }
                byte[] textResult = textWriter.writeResult(result);
                FileUtils.writeByteArrayToFile(new File(fileName + ".txt"), textResult);
                System.out.println(new String(textResult));
            } catch (ParseException ex) {
                System.err.println("Invalid program arguments. Use -h or --help.");
            }
            SpringApplication.exit(appContext, () -> 0);
        }
    }

    private ProcessingResult validate(String fileUrl, String schemaUrl, boolean isStrict) {
        ProcessingResult processingResult;
        String fileAbsoluteUrl = getAbsoluteUrl(fileUrl);
        String schemaAbsoluteUrl = getAbsoluteUrl(schemaUrl);
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(isStrict);
        if (fileAbsoluteUrl != null && schemaAbsoluteUrl != null) {
            processingResult = csvwProcessor.processTabularData(settings, fileAbsoluteUrl, schemaAbsoluteUrl);
        } else if (fileAbsoluteUrl != null) {
            processingResult = csvwProcessor.processTabularData(settings, fileAbsoluteUrl);
        } else if (schemaAbsoluteUrl != null) {
            processingResult = csvwProcessor.processMetadata(settings, schemaAbsoluteUrl);
        } else {
            throw new IllegalStateException();
        }
        return processingResult;
    }

    private String getAbsoluteUrl(String url) {
        String absoluteUrl = null;
        if (url != null) {
            if (UriUtils.isValidUri(url)) {
                absoluteUrl = url;
            } else {
                File file = new File(url);
                if (file.exists()) {
                    absoluteUrl = file.toURI().toString();
                }
            }
        }
        return absoluteUrl;
    }

    private Options createOptions() {
        Options options = new Options();
        options.addOption(Option.builder("f")
                .longOpt("file")
                .desc("The CSV file URL to be processed")
                .argName("FILE")
                .hasArg()
                .required(false)
                .build());
        options.addOption(Option.builder("s")
                .longOpt("schema")
                .desc("The CSVW schema URL to be processed")
                .hasArg()
                .argName("SCHEMA")
                .required(false)
                .build());
        options.addOption(Option.builder("o")
                .longOpt("output")
                .desc("The name of output file without suffix")
                .hasArg()
                .argName("OUTPUT")
                .required(false)
                .build());
        options.addOption(Option.builder()
                .longOpt("strict")
                .desc("Enables strict mode")
                .required(false)
                .build());
        options.addOption(Option.builder()
                .longOpt("rdf")
                .desc("Output is in RDF format")
                .required(false)
                .build());
        options.addOption(Option.builder()
                .longOpt("csv")
                .desc("Output is in CSV format")
                .required(false)
                .build());
        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("Show help")
                .build());
        return options;
    }

}
