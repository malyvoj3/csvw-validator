package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
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

    private final ApplicationContext appContext;
    private final CsvwProcessor csvwProcessor;

    @Autowired
    public CsvwValidatorConsoleApplication(ApplicationContext appContext, CsvwProcessor csvwProcessor) {
        this.appContext = appContext;
        this.csvwProcessor = csvwProcessor;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            CommandLineParser parser = new DefaultParser();
            HelpFormatter formatter = new HelpFormatter();
            Options options = createOptions();
            try {
                formatter.printHelp("java -jar validator.jar", HEADER, options, FOOTER, true);
                CommandLine line = parser.parse(options, args);
                ProcessingResult result = validate(line.getOptionValue('f'), line.getOptionValue('s'));
                System.out.println(result);
            } catch (ParseException ex) {
                System.err.println("Invalid program arguments. Use -h or --help.");
            }
            SpringApplication.exit(appContext, () -> 0);
        }
    }

    private ProcessingResult validate(String fileUrl, String schemaUrl) {
        ProcessingResult processingResult;
        String fileAbsoluteUrl = getAbsoluteUrl(fileUrl);
        String schemaAbsoluteUrl = getAbsoluteUrl(schemaUrl);
        ProcessingSettings settings = new ProcessingSettings(); // TODO
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
