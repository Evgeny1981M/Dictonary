import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Dictionary {
    private Map<String, Map<String, List<String>>> dictionary;

    {
        dictionary = new TreeMap<>();
    }
    public boolean addLang(String lang) {
        lang=lang.toLowerCase();
        if (dictionary.containsKey(lang)){
            return false;
        }
        dictionary.put(lang, new TreeMap<>());
        return true;
    }
    public void printLang() {
        int count=1;
        for (String lang : dictionary.keySet()) {
            System.out.println(count++ + ")" +lang);

        }
    }
}

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        System.out.println(dictionary.addLang("RU"));
        System.out.println(dictionary.addLang("ru"));
        System.out.println(dictionary.addLang("en"));
        System.out.println(dictionary.addLang("fr"));
        dictionary.printLang();
    }
}