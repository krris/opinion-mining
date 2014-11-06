/**
 * Created by krris on 06.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
package io.github.krris.qlearning.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class WebWithOpinionCrawler extends WebCrawler {

    /**
     * Directory where all crawled websites are placed.
     */
    public static final String WEBSITES_DIR_PATH = "websites/";

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ceneo.pl/");
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        int docId = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();

        System.out.println("DocId: " + docId);
        System.out.println("URL: " + url);
        System.out.println("Domain: '" + domain + "'");
        System.out.println("Sub-domain: '" + subDomain + "'");
        System.out.println("Path: '" + path + "'");
        System.out.println("Parent page: " + parentUrl);
        System.out.println("Anchor text: " + anchor);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            saveToFile(html, Integer.toString(docId));
        }

        System.out.println("=============");
    }

    private void saveToFile(String html, String filename) {
        createDirIfDoesNotExist(WEBSITES_DIR_PATH);
        try {
            Files.write(Paths.get(WEBSITES_DIR_PATH + filename + ".html"), html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirIfDoesNotExist(String websitesDirPath) {
        Path directoryPath = Paths.get(websitesDirPath);

        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectory(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}