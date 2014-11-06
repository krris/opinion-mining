package io.github.krris.qlearning.opinion;

import io.github.krris.qlearning.crawler.WebWithOpinionCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by krris on 06.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class OpinionExtractor {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(WebWithOpinionCrawler.WEBSITES_DIR_PATH);
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);
        for (Path file : stream) {
            Document doc = Jsoup.parse(file.toFile(), "UTF-8", "http://www.ceneo.pl");

            Elements elements = doc.getElementsByClass("product-review-summary");
            Elements reviews = doc.getElementsByClass("product-review-body");
            for (Element element : elements) {
                System.out.println(element);
            }
            for (Element element : reviews) {
                System.out.println(element);
            }
        }
    }
}
