import io.github.krris.opinionminer.OpinionMiner;
import io.github.krris.opinionminer.opinion.OpinionExtractor;

/**
 * Created by krris on 21.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class TestData {
    public static final String[] POSITIVE_TEST_DATA = {
            "Wymiana 24h przez 7 dni w tygodniu to genialna opcja! :-)",
            "procesor dla swiadomych uzytkownikow. Wydajnosc niesamowita. PS. W sieci sa informacje o wadliwych egzemplarzach (grzanie sie). U mnie w idle 27st., po obciazeniu grami (Alien, Grid) max 57st. Chlodzenie nieboxowe. Plus dla intela za dorzucanie kluczy do gier i programow, ale poprzednia generacja miala tego wiecej.",
            "Najlepszy z najlepszych jak na razie. Poprawiona temperatura oraz osiągi względem 4770k. Polecam !",
            "Cała transakcja przebiegła bezproblemowo i błyskawicznie. Bardzo dobre ceny i profesjonalne podejscie, na pewno jeszcze nie raz zrobię zakupy w tym sklepie.",
            "Fajne ceny, szybka i bezproblemowa obsługa. Zamierzam wracać po kolejne zakupy - jak najbardziej polecam",
            "Błyskawicznie przeprowadzona transakcja! Bardzo dobry kontakt,ekspresowa wysyłka oraz świetny towar w 100% zgodny z opisem.GORĄCO POLECAM tego sprzedawcę,dziękuję i pozdrawiam serdecznie! :)",
            "Transakcja przebiegła pomyślnie. Polecam.",
            "Bardzo fajny, mały, lekki obiektyw. Na tyle jasny, że można spokojnie robić dzieciom (w ruchu) zdjęcia w pomieszczeniach- bez konieczności używania lampy. Także jasne szkiełko z dobrą ostrością za naprawdę niewielkie pieniądze. Polecam każdemu.",
            "Dobry produkt w swojej klasie cenowej. Bardzo dobra ostrość na przesłonie większej od 2. Poprawne działanie dla aparatów z tzw. śrubokrętem.",
            "Towar zgodny z zamowieniem . Mile zaskoczenie co do ilosci funkcji. Podoba mi sie funkcja odblokowania telefonu na odcisk palca. Ekran dotykowy bardzo czuly , rewelacja.",
            "Jestem bardzo zadowolona z produktu. Jest ładny, praktyczny i doskonale sprawdza się w pracy.",
            "Produkt spełnił wszystkie moje oczekiwania jest w 100% zgodny z opisem umieszczonym na stronie, jestem bardzo zadowolony z tego zakupu i serdecznie polecam go innym kupującym.",
            "Opony są jajowate - mówiono mi i czytałem podobne opinie i wraz kupiłem. Nadmienię, że jeżdżę dwoma samochodami i rocznie przejeżdżam około 60 tys. km. Zimówek używam od roku 92. Zaznaczam tylko, że nie spadłem z księżyca bo uważam, że wiele osób zajmujących stanowisko w takich opiniach nie ma bladego pojęcia o innych, markowych oponach. Wiem, że to bieżnik pochodzący od Goodyear'a z modelu UG5 ale nic poza tym. Totalna porażka.",
            "Obiektyw świetnie ryzuje, i robi ładne rozmycia na najmniejszej przysłonie. Idealny do zdjęć portretowych. Bardzo przystępna cena, obiektyw na pewno jeden z lepszych w swoim przedziale cenowym. Naprawdę godny uwagi i polecenia. Ja jestem nim zachwycona:)",
            "Wbrew pozorem zegarek jest lekki,termometr i barometr po skalibrowaniu na ręce działają bez zarzutu. Wysokościomierz to lipa. Wytrzymały, przyciski w miarę łatwo dostępne, podświetlenie ok. Bateria słoneczna długo trzyma. Super pasek. Polecam ten zegarek.",
            "Spełnienie moich wymagań odnośnie G-SHOCKa. 4 Lata przeglądałem różne modele, tańsze, droższe. Aż wreszcie pojawił się GW-9200. Zawsze aktualne data i czas dzięki Waveceptor, doładowanie akumulatorka za pomocą baterii słonecznych, wytrzymała konstrukcja, wodoodporność. Termometr i barometr to już tylko dodatki do zabawy...",
            "Spełnia oczekiwania, wykorzystuje go w 100 %.Polecam kupno tego typu ze względu na moc i możliwość wrzucania większych kawałków.",
            "Potężny, mocarny procesor - czego chcieć więcej? Póki co nawet nie ma potrzeby podkręcania, bo daje sobie radę ze wszystkim czego potrzebuję! Jak dorzucę mu GTX970 to zobaczymy na co go stać w grach, bo póki co tylko benchmarki. :D",
            "Zdecydowanie nie zamieniłbym tego aparatu na inny! Potrzebowałem aparatu uniwersalnego no i jest. ma wszystko co potrzebne - szybki AF, niezłą jakość zdjęć, no i oczywiście duży zoom! Spisuje się świetnie jak potrzebuję zrobić zdjęcie naprawdę oddalonemu ode mnie obiektowi. jest ekstra! Ostatnio nawet bawiłem się w zdjęcia księżyca :D Trzeba mieć statyw no, ale można się nieźle nakręcić na fotografię z takim aparatem. Czekam teraz na przejrzyste mroźne powietrze i zdjęcia w górach :D Będzie niezła zabawa! Także polecam wszystkim jeszcze niezdecydowanym ;)",
            "Bardzo fajny aparacik, a do tego solidnie wykonany. Posiada wbudowany celownik ramkowy. Ma bardzo przyjemny zoom, bo aż 50x. Nie długo lecę w alpy więc zobaczę jak się sprawdza w ekstr warunkach :) Fajny, lekki uważam, że to dobrze wydane pieniądze.",
    };

    public static final String[] NEGATIVE_TEST_DATA = {
            "Słaby film, nie wiem jak można nakręcić tak słabe kino i jeszcze promować je w takiej cenie, nieporozumienie. Odradzam, szkoda kasy!",
            "Hm... Właściwie wystarczy powiedzieć, że to film z Jean Claudem i już wszystko jest jasne. Wiadomo, że akcja będzie kręcić się wokół walk bohaterów i wymierzania sobie ciosów plus kopania się po odwłoku. Tutaj jest nie inaczej: Van Damme gra żołnierza w przyszłości, więc walczy i to często. Całość oprawiona jest w jakąś historię, żeby nie było, ale i tak główny akcent kładzie się na scenach walk. Mimo to film ogląda się całkiem nieźle, nikomu nie będę go odradzać.",
            "Ten serwis to jakaś kpina. Korzystałem z ich usług przez kilka miesięcy - i z lenistwa nie zmieniałem wierząc, że ciągłe problemy to rzeczywiście nieszczęśliwe zbiegi okoliczności. Pieniądze wychodzą z ich serwisu i nie trafiają na moje konto najczęściej w wyniku \"błędu systemu\". Te niespodziewane błędy systemu zdarzyły się 3 na 8 transakcji których dokonywałem. W dwóch przypadkach (w tym wczoraj) poskutkowało to opóźnieniem w płatności raty kredytu. Jeśli spłacasz kredyt to nie jest serwis dla siebie. Jeśli lubisz dopominać się i wydzwaniać o własne pieniądze to jest to miejsce dla Ciebie.",
            "Mop parowy niestety jest wielkim nieporozumieniem. Nic nie działa(np.kółka od szczotki odpadły przy próbie uruchomienia) i nie jest zgodne z opisem( np.mop miał być angielski,nie takich samych jak na zdjeciu szmatek).",
            "Bolą po nich oczy. Nie można nosić dłużej niż 8 godzin, bo jest później masakra. Tak jakby oczy pod nimi w ogóle nie oddychały. Kupiłem 6 sztuk, obecnie noszę drugą parę i to nie jest efekt przestawienia (trwałby ponad miesiąc?). Lepsze są J&J Acuvue. Słabsza moc od J&J, mimo, że kupiłem tą samą moc (-3.5) - niby widać wyraźnie, ale nie tak jak w J&J. Spodziewałem się więcej od soczewek droższych od J&J Acuvue.",
            "Nie polecam nikomu tej firmy do wymiany walut.",
            "Mam nadzieję, że nikt mnie za to nie ukrzyżuje. Próbowałam przeczytać jako nastolatka i kompletnie nie przypadło mi do gustu.",
            "Chciałam, chciałam ale nie żarło. Po dwóch podejściach powiedziałam pas.",
            "Nie polecam! Po skończeniu się gwarancji zaczął się psuć! Ciągle przerywa sygnał a z nowymi urządzeniami już nawet nie łączy... TRAGEDIA!",
            "Nie wart swej ceny. Byak przejrzystej instrukcji konfiguracji.Zasięg bezprzewodowy niezadowalający.",
            "Jest to ogólna ocena foteli Calviano. Typowa chińska tandeta. Elementy nie są dobrze spasowane, fotel trzeszczy już od początku użytkowania. Materiały i ogólna jakość montażu pozostawia bardzo wiele do życzenia. Wszystko jest wykonane bardzo niedbale i sprawia wrażenie że zaraz się rozleci. Fotel dopasowany do niższych osób. Przy wzroście około 190 cm oparcie przeszkadza w wygodnym siedzeniu. Najwyższy model fotela tej firmy powinien kosztować max 150 zł Ogólnie NIE POLECAM tego produktu.",
            "Podłokietniki ledwo dało się przykręcić, bo nie były dopasowane do dziurek na śruby. Drewno, z którego wykonane są oparcia jest tandetne, cała farba już z niego zeszła i widać jasne drewno ze spodu. Masaż nie jest masażem tylko wibracjami, które wcale nie relaksują tylko denerwują. Ogólnie produkt tandetny.",
            "Niszczarka padła po 15 minutach pracy. NIE polecam",
            "Brak dowodu zakupu a i gwarancji. Do dziś nie dosłali paragonu ani faktury. Żenada.",
            "Otrzymałam laptop z uszkodzoną matrycą. Odesłałam, a zamiast wymiany rozpoczęto proces reklamacji. Po 10 dniach czekania zażądałam zwrotu pieniędzy, które na szczęście zwrócono. Jednak nie bez kłótni. Nie polecam.",
            "sklep działa powolnie. Odbieranie zamówienia trwa dłużej niż deklarowane jest na stronie. Sposób odbioru CD-Key'a jest nieintuicyjny. Kontakt z obsługą działa również powoli. No i wreszcie puenta. Skyrim Legendary Edition na stronie jest opisane jako wersja polsko-języczna, a finalnie otrzymałem angielsko-języczną. Mądrzejszy o te wiedzę skorzystałbym z oferty tego sklepu wyłącznie w ostateczności bo finalnie wszystko się ułożyło, ale musiałem czekać 30 minut na e-mail, z grą w języku odmiennym od oferowanego.",
            "Moja ocena jest negatywna czekam na klucz już parę godzin i dalej nic kolega wysłał z tego samego banku dostał klucz po 5 min. a na dowód wpłaty nie wydają pierwszy i ostatni raz kupiłem tu klucz",
            "Przedmiot niezgodny z opisem. Po odesłaniu bardzo słaby kontakt i długo realizowana reklamacja. Sprawa załatwiona niezgodnie z moja prośbą. ODRADZAM zakupy w AGITO.",
            "Nie polecam. Przelewy wysylane sa z zagranicznego rachunku w CHF, a kosztami obciaza sie odbiorce. W efekcie placi sie znacznie wiecej niz oszczednosc na kursie wymiany. Internetowykantor nie informuje o tym wyraznie podczas dokonywania transakcji.",
            "KantorOnline to faktycznie lipa. System nie działa, na zaksięgowanie zasilenie konta zamiast obiecanych maks. 30 min. czekałem ponad 10h. Zlecenie przelewu nie działa... tzn. działa tylko że pieniądze zniknęły... nie ma ich ani w serwisie, ani na rachunku w banku. Czekam już 4 dni, aż się gdziekolwiek pojawią. Kontakt z obsługą żaden: live chat: nikt się nie zgłasza, można sobie popisać samemu ze sobą infolinia: sama się rozłącza po tekście że rozmowy mogą być nagrywane email: olewka nikt nie odpisuje"
    };

    private static final String TRAINING_DATA = "training-data/";

    public static void main(String[] args) {
        OpinionMiner opinionMiner = new OpinionMiner();
//        opinionMiner.collectData();
        opinionMiner.trainClassifier(TRAINING_DATA);

        checkPositiveData(opinionMiner);
        System.out.println("=================================");
        checkNegativeData(opinionMiner);
    }

    private static void checkPositiveData(OpinionMiner opinionMiner) {
        int correctResultCounter = 0;

        System.out.println("Positive test data:");
        for (String data : POSITIVE_TEST_DATA) {
            String result = opinionMiner.classify(data);
            System.out.println("Resutl: [" + result + "], " + data);
            if (result.equals(OpinionExtractor.POSITIVE)) {
                correctResultCounter++;
            }
        }
        System.out.println("Correct result for: " + correctResultCounter + "/" + POSITIVE_TEST_DATA.length);
    }

    private static void checkNegativeData(OpinionMiner opinionMiner) {
        int correctResultCounter = 0;

        System.out.println("Negative test data:");
        for (String data : NEGATIVE_TEST_DATA) {
            String result = opinionMiner.classify(data);
            System.out.println("Resutl: [" + result + "], " + data);
            if (result.equals(OpinionExtractor.NEGATIVE)) {
                correctResultCounter++;
            }
        }
        System.out.println("Correct result for: " + correctResultCounter + "/" + NEGATIVE_TEST_DATA.length);
    }


}
