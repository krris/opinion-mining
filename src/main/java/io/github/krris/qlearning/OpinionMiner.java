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

    public static final String[] POSITIVE_TEST_DATA = {
            "Najlepszy z najlepszych jak na razie. Poprawiona temperatura oraz osiągi względem 4770k. Polecam !",
            "Potężny, mocarny procesor - czego chcieć więcej? Póki co nawet nie ma potrzeby podkręcania, bo daje sobie radę ze wszystkim czego potrzebuję! Jak dorzucę mu GTX970 to zobaczymy na co go stać w grach, bo póki co tylko benchmarki. :D",
            "Zdecydowanie nie zamieniłbym tego aparatu na inny! Potrzebowałem aparatu uniwersalnego no i jest. ma wszystko co potrzebne - szybki AF, niezłą jakość zdjęć, no i oczywiście duży zoom! Spisuje się świetnie jak potrzebuję zrobić zdjęcie naprawdę oddalonemu ode mnie obiektowi. jest ekstra! Ostatnio nawet bawiłem się w zdjęcia księżyca :D Trzeba mieć statyw no, ale można się nieźle nakręcić na fotografię z takim aparatem. Czekam teraz na przejrzyste mroźne powietrze i zdjęcia w górach :D Będzie niezła zabawa! Także polecam wszystkim jeszcze niezdecydowanym ;)",
            "Bardzo fajny aparacik, a do tego solidnie wykonany. Posiada wbudowany celownik ramkowy. Ma bardzo przyjemny zoom, bo aż 50x. Nie długo lecę w alpy więc zobaczę jak się sprawdza w ekstr warunkach :) Fajny, lekki uważam, że to dobrze wydane pieniądze."
    };

    public static final String[] NEGATIVE_TEST_DATA = {
            "Słaby film, nie wiem jak można nakręcić tak słabe kino i jeszcze promować je w takiej cenie, nieporozumienie. Odradzam, szkoda kasy!",
            "Hm... Właściwie wystarczy powiedzieć, że to film z Jean Claudem i już wszystko jest jasne. Wiadomo, że akcja będzie kręcić się wokół walk bohaterów i wymierzania sobie ciosów plus kopania się po odwłoku. Tutaj jest nie inaczej: Van Damme gra żołnierza w przyszłości, więc walczy i to często. Całość oprawiona jest w jakąś historię, żeby nie było, ale i tak główny akcent kładzie się na scenach walk. Mimo to film ogląda się całkiem nieźle, nikomu nie będę go odradzać."
    };


    public static void main(String[] args) {
//        runWebCrawler();
//        runOpinionExtractor();

        SentimentClassifier classifier = new SentimentClassifier();
        classifier.train(TRAINING_DATA_DIR);
        checkSentiment(classifier);
    }

    private static void runOpinionExtractor() {
        OpinionExtractor opinionExtractor = new OpinionExtractor();
        String[] args = {};
        opinionExtractor.main(args);
    }

    private static void runWebCrawler() {
        String[] args = {"tmp/", "40"};
        try {
            WebWithOpinionCrawlerController.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkSentiment(SentimentClassifier classifier) {
        System.out.println("Positive test data:");
        for (String data : POSITIVE_TEST_DATA) {
            System.out.println(classifier.classify(data));
        }

        System.out.println("=================================");
        System.out.println("Negative test data");
        for (String data : NEGATIVE_TEST_DATA) {
            System.out.println(classifier.classify(data));
        }
    }
}
