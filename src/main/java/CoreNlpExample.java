import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.util.*;

public class CoreNlpExample {

    public static String text = "Hello, I am Sokratis and I really don't like John. I like George.";

    public static void main(String[] args) {
        StanfordCoreNLP pipeline = new StanfordCoreNLP(
                PropertiesUtils.asProperties(
                        "annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, sentiment",
                        "tokenize.language", "en"));

        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        List<CoreSentence> sentences = document.sentences();

        for(CoreSentence sentence: sentences) {
            //this is the Sentiment label of the sentence
            String sentiment = sentence.sentiment();
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            System.out.println(sentiment + " ["+ sentence +"]" );
            for (CoreLabel token : sentence.tokens()) {
                // this is the text of the token
                String word = token.word();
                //this is the Lemmatization of the token
                String lem = token.lemma();
                // this is the POS tag of the token
                String pos = token.tag();
                // this is the NER label of the token
                String ne = token.ner();

                System.out.println(String.format("Word: [%s] lem: [%s] POS: [%s] ne: [%s]", word, lem, pos, ne));
            }
            System.out.println();
        }
    }

}
