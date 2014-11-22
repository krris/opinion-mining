package io.github.krris.opinionminer.classifier;

import com.aliasi.classify.*;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by krris on 07.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class SentimentClassifier {
    private String[] categories;
    private LMClassifier lmClassifier;

    public static final String SAVED_CLASSIFIER_PATH = "tmp/classifier.dat";

    private Logger logger = LoggerFactory.getLogger(SentimentClassifier.class);

    public void loadClassifier() {
        File savedClassifier = new File(SAVED_CLASSIFIER_PATH);
        this.lmClassifier = loadClassifier(savedClassifier);
        this.categories = this.lmClassifier.categories();
    }

    private LMClassifier loadClassifier(File savedClassifier) {
        LMClassifier classifier = null;
        try {
            classifier = (LMClassifier) AbstractExternalizable.readObject(savedClassifier);
        } catch (IOException e) {
            String message = "Classifier has to be trained before first use. Please provide training data and add " +
                    "\"-train\" or call OpinionMiner.trainClassifier().";
            logger.error(message, e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classifier;
    }

    public void train(String trainingDataDirPath) {
        int nGram = 4;
        File trainingDataDir = new File(trainingDataDirPath);
        String[] categories = trainingDataDir.list();
        LMClassifier lmClassifier = DynamicLMClassifier.createNGramProcess(categories, nGram);

        for (int i = 0; i < categories.length; ++i) {
            String category = categories[i];
            Classification classification = new Classification(category);
            File file = new File(trainingDataDir, categories[i]);
            File[] trainFiles = file.listFiles();
            for (int j = 0; j < trainFiles.length; ++j) {
                File trainFile = trainFiles[j];
                String review = this.readFile(trainFile);
                Classified classified = new Classified(review, classification);
                ((ObjectHandler) lmClassifier).handle(classified);
            }
        }
        this.lmClassifier = lmClassifier;
        this.saveClassifier();
    }

    private String readFile(File file) {
        String content = null;
        try {
            content = Files.readFromFile(file, "ISO-8859-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private void saveClassifier() {
        try {
            AbstractExternalizable.compileTo((Compilable) this.lmClassifier, new File(SAVED_CLASSIFIER_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String classify(String text) {
        if (this.lmClassifier == null) {
            this.loadClassifier();
        }

        ConditionalClassification classification = this.lmClassifier.classify(text);
        return classification.bestCategory();
    }
}
