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

/**
 * Created by krris on 06.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class OpinionExtractor {

    public static final String POSITIVE_DATA = "training-data/positive/positive-data.csv";
    public static final String NEGATIVE_DATA = "training-data/negative/negative-data.csv";

    private List<String[]> positiveData = new ArrayList<>();
    private List<String[]> negativeData = new ArrayList<>();

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
            Document doc = Jsoup.parse(file.toFile(), "UTF-8", "http://www.ceneo.pl");

            Elements elements = doc.getElementsByClass("product-review-summary");
            Elements reviews = doc.getElementsByClass("product-review-body");

            assert elements.size() == reviews.size();

            for (int i = 0; i < elements.size(); i++) {
                int columns = 2;
                String[] dataContent = new String[columns];

                // sentiment
                dataContent[sentimentId] = elements.get(i).text();
                // text
                dataContent[textId] = reviews.get(i).text();

                if (dataContent[sentimentId].equals("Nie polecam")) {
                    negativeData.add(dataContent);
                } else {
                    positiveData.add(dataContent);
                }
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

//    public static void main(String[] args) throws IOException {
//
//        int columns = 2;
//        String[] dataContent = new String[columns];
//
//        CSVWriter positiveWriter = new CSVWriter(new FileWriter(POSITIVE_DATA), ',');
//        CSVWriter negativeWriter = new CSVWriter(new FileWriter(NEGATIVE_DATA), ',');
//
//        Path path = Paths.get(WebWithOpinionCrawler.WEBSITES_DIR_PATH);
//        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
//
//        int sentimentId = 0;
//        int textId = 1;
//
//        for (Path file : stream) {
//            Document doc = Jsoup.parse(file.toFile(), "UTF-8", "http://www.ceneo.pl");
//
//            Elements elements = doc.getElementsByClass("product-review-summary");
//            Elements reviews = doc.getElementsByClass("product-review-body");
//
//            assert elements.size() == reviews.size();
//
//            for (int i = 0; i < elements.size(); i++) {
//                // sentiment
//                dataContent[sentimentId] = elements.get(i).text();
//                // text
//                dataContent[textId] = reviews.get(i).text().replace(",", " ");
//
//                if (dataContent[sentimentId].equals("Polecam")) {
//                    positiveWriter.writeNext(dataContent);
//                } else {
//                    negativeWriter.write
//                }
//                writer.writeNext(dataContent);
//
////                dataContent += "\n" + sentiment + ", " + reviews.get(i).text().replace(",", " ");
////                System.out.println(elements.get(i).text());
////                System.out.println(reviews.get(i).text());
////                System.out.println("=============");
//            }
//        }
//    }
}
