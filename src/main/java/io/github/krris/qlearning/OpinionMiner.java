package io.github.krris.qlearning;

import io.github.krris.qlearning.classifier.SentimentClassifier;
import io.github.krris.qlearning.crawler.WebWithOpinionCrawlerController;
import io.github.krris.qlearning.opinion.OpinionExtractor;

/**
 * Created by krris on 07.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class OpinionMiner {
    private SentimentClassifier classifier;

    public static final String TRAINING_DATA_DIR = "training-data/";
    private static final String TEMPORARY_DIR_FOR_WEB_CRAWLER = "tmp/";
    private static final int NUMBER_OF_CRAWLERS = 40;

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

    public void trainClassifier() {
        this.classifier = new SentimentClassifier();
        classifier.train(TRAINING_DATA_DIR);
    }

    public String classify(String text) {
        return this.classifier.classify(text);
    }
}
