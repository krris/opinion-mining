package io.github.krris.qlearning.classifier;

import com.aliasi.classify.*;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;

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
        ConditionalClassification classification = this.lmClassifier.classify(text);
        return classification.bestCategory();
    }
}
