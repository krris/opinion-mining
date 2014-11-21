package io.github.krris.qlearning.opinion;

import io.github.krris.qlearning.crawler.WebWithOpinionCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by krris on 06.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class OpinionExtractor {

    private List<String[]> positiveData = new ArrayList<>();
    private List<String[]> negativeData = new ArrayList<>();

    private static final double REVIEW_SCORE_TRESHOLD = 3.00;

    public static final String POSITIVE_DATA = "training-data/positive/positive-data.csv";
    public static final String NEGATIVE_DATA = "training-data/negative/negative-data.csv";

    public static final String NEGATIVE = "negative";
    public static final String POSITIVE = "positive";

    private static final String REVIEW_HTML_TAG = "product-review-body";
    private static final String REVIEW_SCORE_HTML_TAG = "product-review-score";

    private static final String WEBSITE = "http://www.ceneo.pl";

    public static void main(String[] args) {
        OpinionExtractor opinionExtractor = new OpinionExtractor();

        try {
            opinionExtractor.parseWebsites();
            opinionExtractor.writeToFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseWebsites() throws IOException {
        Path path = Paths.get(WebWithOpinionCrawler.WEBSITES_DIR_PATH);
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);

        int sentimentId = 0;
        int textId = 1;

        for (Path file : stream) {
            Document doc = Jsoup.parse(file.toFile(), "UTF-8", WEBSITE);

            Elements reviewScores = doc.getElementsByClass(REVIEW_SCORE_HTML_TAG);
            Elements reviews = doc.getElementsByClass(REVIEW_HTML_TAG);

            assert reviewScores.size() == reviews.size();

            for (int i = 0; i < reviewScores.size(); i++) {
                int columns = 2;
                String[] dataContent = new String[columns];

                // sentiment
                String reviewScoreElement = reviewScores.get(i).text();
                dataContent[sentimentId] = isReviewScorePositive(reviewScoreElement);
                // text
                dataContent[textId] = reviews.get(i).text();

                if (dataContent[sentimentId].equals(NEGATIVE)) {
                    negativeData.add(dataContent);
                } else {
                    positiveData.add(dataContent);
                }

                System.out.println(dataContent[0] + ": " + dataContent[1]);
            }
        }
    }

    private void writeToFiles() throws IOException {
        String negative = "";
        for (String[] data : negativeData) {
            negative += "\"" + data[0] + "\", \"" + data[1] + "\"\n";
        }
        Files.write(Paths.get(NEGATIVE_DATA), negative.getBytes());

        String positive = "";
        for (String[] data : positiveData) {
            positive += "\"" + data[0] + "\", \"" + data[1] + "\"\n";
        }
        Files.write(Paths.get(POSITIVE_DATA), positive.getBytes());
    }

    private String isReviewScorePositive(String reviewScoreHtml) {
        if (extractReviewScore(reviewScoreHtml) >= REVIEW_SCORE_TRESHOLD) {
            return POSITIVE;
        }
        return NEGATIVE;
    }

    /**
     * Extract review score from following html:
     *
    "<dl class=\"product-review-score\">\n" +
                "\t<dt class=\"screen-reader-text\">Ocena:</dt>\n" +
                "\t<dd>\n" +
                "\t\t<span class=\"score-container js_score-container\">\n" +
                "\t<span class=\"score-marker\" style=\"width: 100.00%;\"></span>\n" +
                "</span>  \n" +
                "\n" +
                "        5,00\n" +
                "\t</dd>\n" +
                "</dl>";
     */
    private double extractReviewScore(String string) {
        String pattern = "(.*)([0-9],[0-9]{2})(.*)";
        String number = string.replaceAll("\\s+","").replaceAll(pattern, "$2");
        number = number.replaceAll(",", ".");
        return Double.parseDouble(number);
    }
}
