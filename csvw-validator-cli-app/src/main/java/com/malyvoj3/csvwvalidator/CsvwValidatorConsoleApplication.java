package com.malyvoj3.csvwvalidator;

import com.malyvoj3.csvwvalidator.config.ProcessorConfig;
import com.malyvoj3.csvwvalidator.processor.CsvwProcessor;
import com.malyvoj3.csvwvalidator.processor.ProcessingSettings;
import com.malyvoj3.csvwvalidator.processor.result.CsvResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.ProcessingResult;
import com.malyvoj3.csvwvalidator.processor.result.RdfResultWriter;
import com.malyvoj3.csvwvalidator.processor.result.TextResultWriter;
import com.malyvoj3.csvwvalidator.utils.UriUtils;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class CsvwValidatorConsoleApplication {

    private static final String HEADER = "Validate CSV file, W3C CSV on the Web schema or both.";
    private static final String FOOTER = "Please report issues at http://github.com/malyvoj3/csvw-validator/issues";
    private static final String RESULT_FILE_NAME_DEFAULT = "result";

    private final CsvwProcessor csvwProcessor;
    private final CsvResultWriter csvWriter;
    private final RdfResultWriter rdfWriter;
    private final TextResultWriter textWriter;

    public CsvwValidatorConsoleApplication(CsvwProcessor csvwProcessor,
                                           CsvResultWriter csvWriter,
                                           RdfResultWriter rdfWriter,
                                           TextResultWriter textWriter) {
        this.csvwProcessor = csvwProcessor;
        this.csvWriter = csvWriter;
        this.rdfWriter = rdfWriter;
        this.textWriter = textWriter;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ProcessorConfig.class);
        CsvwProcessor processor = ctx.getBean(CsvwProcessor.class);
        CsvResultWriter csvWriter = ctx.getBean(CsvResultWriter.class);
        RdfResultWriter rdfWriter = ctx.getBean(RdfResultWriter.class);
        TextResultWriter textWriter = ctx.getBean(TextResultWriter.class);
        CsvwValidatorConsoleApplication application =
                new CsvwValidatorConsoleApplication(processor, csvWriter, rdfWriter, textWriter);
        application.validate(args);
    }

    public void validate(String[] args) {
        if (args.length > 0) {
            CommandLineParser parser = new DefaultParser();
            HelpFormatter formatter = new HelpFormatter();
            Options options = createOptions();
            try {
                CommandLine line = parser.parse(options, args);
                if (line.hasOption('h')) {
                    formatter.printHelp("java -jar validator.jar", HEADER, options, FOOTER, true);
                } else {
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
                }
            } catch (ParseException ex) {
                System.err.println("Invalid program arguments. Use -h or --help.");
            } catch (Exception ex) {
                System.err.println("Invalid usage of program. Use -h or --help. At least file or schema must be specified.");
            }
        }
    }

    private ProcessingResult validate(String fileUrl, String schemaUrl, boolean isStrict) {
        ProcessingResult processingResult;
        String fileAbsoluteUrl = getAbsoluteUrl(fileUrl);
        String schemaAbsoluteUrl = getAbsoluteUrl(schemaUrl);
        ProcessingSettings settings = new ProcessingSettings();
        settings.setStrictMode(isStrict);
        if (fileAbsoluteUrl != null && schemaAbsoluteUrl != null) {
            processingResult = csvwProcessor.process(settings, fileAbsoluteUrl, schemaAbsoluteUrl);
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
