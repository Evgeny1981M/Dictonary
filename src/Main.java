import java.util.*;

class Slovar {
    private Map<String, Map<String, List<String>>> dictionary;

    {
        dictionary = new TreeMap<>();
    }

    // метод добавления языка ru,en
    public boolean addLang(String lang) {
        lang = lang.toLowerCase(); // игнорирование регистра
        if (dictionary.containsKey(lang)) { //не добавлять уже существующий язык
            return false;
        }
        dictionary.put(lang, new TreeMap<>());
        return true;
    }

    // метод вывода на печать языка
    public void printLang() {
        int count = 1;
        for (String lang : dictionary.keySet()) {
            System.out.println(count++ + ") " + lang);
        }
    }

    // метод удаления языка
    public boolean removeLang(String lang) {
        lang = lang.toLowerCase();// игнорирование регистра
        if (dictionary.containsKey(lang)) {
            dictionary.remove(lang);
            return true;
        }
        return false;
    }

    public List<String> toLowerCase(List<String> list) {
        List<String> lisrArr = new ArrayList<String>();
        for (String word : list) {
            lisrArr.add(word.toLowerCase());
        }
        return lisrArr;
    }


    public boolean addWord(String keyLang, String originalWord, List<String> arrWord) {
        originalWord = originalWord.toLowerCase();// игнорирование регистра
        keyLang = keyLang.toLowerCase();
        arrWord = toLowerCase(arrWord);
        if (dictionary.containsKey(keyLang)) {
            if (dictionary.get(keyLang).containsKey(originalWord)) {
                return false;
            } else {
                dictionary.get(keyLang).put(originalWord, arrWord);
                return true;
            }
        } else {
            if (addLang(keyLang))
                return addWord(keyLang, originalWord, arrWord);
            else return false;
        }
    }

    public boolean addWord(String keyLang, String originalWord, String word) {
        originalWord = originalWord.toLowerCase();// игнорирование регистра
        keyLang = keyLang.toLowerCase();
        word = word.toLowerCase();
        if (dictionary.containsKey(keyLang)) {
            if (dictionary.get(keyLang).containsKey(originalWord)) {
                if (dictionary.get(keyLang).get(originalWord).contains(word)) {
                    return false;
                } else {
                    dictionary.get(keyLang).get(originalWord).add(word);
                    return true;
                }
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(word);
                dictionary.get(keyLang).put(originalWord, list);
                return false;
            }
        } else {
            if (addLang(keyLang)) {
                boolean isadd = addWord(keyLang, originalWord, word);
                return isadd;
            } else {
                return false;
            }
        }
    }

    public void printSlovarLang(String key) {
        int count = 1;
        for (String newKey : dictionary.keySet()) {
            System.out.println(newKey);
            System.out.println("--------------------------------");
            for (String word : dictionary.get(newKey).keySet()) {
                System.out.print(word + " : ");
                for (String tr : dictionary.get(newKey).get(word)) {
                    System.out.print(tr + " , ");
                }
                System.out.println();
            }
        }
//        System.out.println(dictionary.get(key));
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("Словарь!");
        Slovar slovar = new Slovar();
        System.out.println(slovar.addLang("ru"));
        System.out.println(slovar.addLang("en"));
        System.out.println(slovar.addLang("fr"));
        System.out.println(slovar.removeLang("pr"));

        ArrayList<String> arr = new ArrayList<String>();
        arr.add("Кошка");
        arr.add("Кот");

        slovar.addWord("en", "cat", arr);

        slovar.addWord("en", "Car", "Автомобиль");
        slovar.addWord("en", "Car", "Машина");
        slovar.addWord("en", "Car", "Транспорт");
        slovar.addWord("en", "Head", "Голова");

        slovar.printSlovarLang("en");

        slovar.printLang();

    }
}