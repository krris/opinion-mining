import io.github.krris.opinionminer.OpinionMiner;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by krris on 22.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class UserInterfaceTest {

    @Test
    public void useTraingDataTest() {
        String[] arguments = {
                "-useTrainingData", "training-data/",
                "-train",
                "-classify", "Super książka! Polecam!"
        };
        OpinionMiner.main(arguments);
    }

    @Test
    @Ignore // uncomment only if you want to spend some time (few minutes) running web crawler
    public void collectTrainingDataTest() {
        String[] arguments = {
                "-collectData",
                "-train",
                "-classify", "Super książka! Polecam!"
        };
        OpinionMiner.main(arguments);
    }

    @Test
         public void classifyAndTrainTest() {
        String[] arguments = {
                "-train",
                "-classify", "Super książka! Polecam!"
        };
        OpinionMiner.main(arguments);
    }

    @Test
    public void classifyTest() {
        String[] arguments = {
                "-classify", "Super książka! Polecam!"
        };
        OpinionMiner.main(arguments);
    }
}
