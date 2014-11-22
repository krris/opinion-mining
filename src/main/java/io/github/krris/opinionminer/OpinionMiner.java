package io.github.krris.opinionminer;

import io.github.krris.opinionminer.classifier.SentimentClassifier;
import io.github.krris.opinionminer.crawler.WebWithOpinionCrawlerController;
import io.github.krris.opinionminer.opinion.OpinionExtractor;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by krris on 07.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class OpinionMiner {
    private SentimentClassifier classifier = new SentimentClassifier();;

    public static final String DEFAULT_TRAINING_DATA_DIR = "training-data/";
    private static final String TEMPORARY_DIR_FOR_WEB_CRAWLER = "tmp/";
    private static final int NUMBER_OF_CRAWLERS = 40;

    private static final String COLLECT_DATA = "collectData";
    private static final String USE_TRAINING_DATA = "useTrainingData";
    private static final String TRAIN_CLASSIFIER = "train";
    private static final String TEXT_TO_CLASSIFY = "classify";

    private static Logger logger = LoggerFactory.getLogger(OpinionMiner.class);

    public static void main(String[] args) {
        OpinionMiner opinionMiner = new OpinionMiner();

        // create Options object
        Options options = new Options();
        Option dataCollection = new Option(COLLECT_DATA, "run web crawler to collect data");
        Option useTrainingData = new Option(USE_TRAINING_DATA, true, "use training data from given directory");
        Option trainClassifier = new Option(TRAIN_CLASSIFIER, "train classifier");
        Option textToClassify = new Option(TEXT_TO_CLASSIFY, true, "text to classify");

        options.addOption(dataCollection);
        options.addOption(useTrainingData);
        options.addOption(trainClassifier);
        options.addOption(textToClassify);

        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        logger.debug(COLLECT_DATA + ": " + cmd.hasOption(COLLECT_DATA));
        logger.debug(USE_TRAINING_DATA + ": " + cmd.getOptionValue(USE_TRAINING_DATA));
        logger.debug(TRAIN_CLASSIFIER + ": " + cmd.hasOption(TRAIN_CLASSIFIER));
        logger.debug(TEXT_TO_CLASSIFY + ": " + cmd.getOptionValue(TEXT_TO_CLASSIFY));

        if (cmd.hasOption(COLLECT_DATA)) {
            opinionMiner.collectData();
        }

        String trainingDataDir = DEFAULT_TRAINING_DATA_DIR;
        if (cmd.hasOption(USE_TRAINING_DATA)) {
            trainingDataDir = cmd.getOptionValue(USE_TRAINING_DATA);
        }

        if (cmd.hasOption(TRAIN_CLASSIFIER)) {
            opinionMiner.trainClassifier(trainingDataDir);
        }

        if (cmd.hasOption(TEXT_TO_CLASSIFY)) {
            String text = cmd.getOptionValue(TEXT_TO_CLASSIFY);
            String result = opinionMiner.classify(text);
            System.out.println("Sentiment: " + result);
        }

    }

    /**
     * Collects data - customer's opinions from websites.
     */
    public void collectData() {
        runWebCrawler();
        runOpinionExtractor();
    }

    private static void runOpinionExtractor() {
        OpinionExtractor opinionExtractor = new OpinionExtractor();
        String[] args = {};
        opinionExtractor.main(args);
    }

    private static void runWebCrawler() {
        String[] args = {TEMPORARY_DIR_FOR_WEB_CRAWLER, String.valueOf(NUMBER_OF_CRAWLERS)};
        try {
            WebWithOpinionCrawlerController.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void trainClassifier(String trainingDataDir) {
        classifier.train(trainingDataDir);
    }

    public String classify(String text) {
        return this.classifier.classify(text);
    }
}
