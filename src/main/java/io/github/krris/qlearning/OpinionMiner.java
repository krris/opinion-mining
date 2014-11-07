package io.github.krris.qlearning;

import io.github.krris.qlearning.classifier.SentimentClassifier;
import io.github.krris.qlearning.crawler.WebWithOpinionCrawlerController;
import io.github.krris.qlearning.opinion.OpinionExtractor;

/**
 * Created by krris on 07.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class OpinionMiner {
    public static final String TRAINING_DATA_DIR = "training-data/";

    public static void main(String[] args) {
//        runWebCrawler();
//        runOpinionExtractor();

        SentimentClassifier classifier = new SentimentClassifier();
        classifier.train(TRAINING_DATA_DIR);
        String decision = classifier.classify("Beznadziejna bajka o niczym trzeba ...");
        System.out.println(decision);
    }

    private static void runOpinionExtractor() {
        OpinionExtractor opinionExtractor = new OpinionExtractor();
    }

    private static void runWebCrawler() {
        String[] args = {"tmp/", "7"};
        try {
            WebWithOpinionCrawlerController.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
