import java.util.*;

class Slovar {
    private Map<String, Map<String, List<String>>> dictionary;
    {
        dictionary = new TreeMap<>();
    }
    // метод добавления языка ru, en
    public boolean addLang(String lang) {
        lang = lang.toLowerCase(); // игнорирование регистра
        if (dictionary.containsKey(lang)) { //не добавлять уже существующий язык
            return false;
        }
        dictionary.put(lang, new TreeMap<>());
        return true;
    }
    // метод вывода на печать языка ru, en
    public void printLang() {
        int count = 1;
        for (String lang : dictionary.keySet()) {
            System.out.println(count++ + ") " + lang);
        }
    }
    // метод удаления языка ru, en
    public boolean removeLang(String lang) {
        lang = lang.toLowerCase();// игнорирование регистра
        if (dictionary.containsKey(lang)) {
            dictionary.remove(lang);
            return true;
        }
        return false;
    }
    public List<String> toLowerCase(List<String> list) {
        List<String> lisrArr = new ArrayList<>();
        for (String word : list) {
            lisrArr.add(word.toLowerCase());
        }
        return lisrArr;
    }
    public List<String> checkList(List<String> nowArrWord, List<String> newArrWord) {
        for (String word : newArrWord) {
            if (!nowArrWord.contains(word)) {
                nowArrWord.add(word);
            }
        }
        return nowArrWord;
    }
    public boolean addWord(String keyLang, String originalWord, List<String> arrWord) {
        originalWord = originalWord.toLowerCase();// игнорирование регистра
        keyLang = keyLang.toLowerCase();// игнорирование регистра
        arrWord = toLowerCase(arrWord);// игнорирование регистра
        if (dictionary.containsKey(keyLang)) {
            if (dictionary.get(keyLang).containsKey(originalWord)) {
                List<String> arr = dictionary.get(keyLang).get(originalWord);
                List<String> arrNew;
                //проверить есть ли слова отсутствующие в arrWord
                arrNew = checkList(arr, arrWord);
                if (arr.size() != arrNew.size()) {
                    dictionary.get(keyLang).put(originalWord, arrNew);
                    return true;
                }
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
        keyLang = keyLang.toLowerCase();// игнорирование регистра
        word = word.toLowerCase();// игнорирование регистра
        if (dictionary.containsKey(keyLang)) {
            if (dictionary.get(keyLang).containsKey(originalWord)) {
                if (dictionary.get(keyLang).get(originalWord).contains(word)) {
                    return false;
                } else {
                    dictionary.get(keyLang).get(originalWord).add(word);
                    return true;
                }
            } else {
                ArrayList<String> list = new ArrayList<>();
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
    public void printSlovarLang(String newKey) {
        int count = 1;
        if (dictionary.containsKey(newKey)) {
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
    }
    public void printSlovarLang() {
        int count = 1;
        if (dictionary.keySet().size()>0) {

            for (String lang : dictionary.keySet()) {
                System.out.println(count++ + ") "+ lang);
            }
        }
    }
    public String getLangByIndex(int index) {
        int count = 1;
        if (dictionary.keySet().size()>=index && index>=0) {

            for (String lang : dictionary.keySet()) {
                if (index == count) return  lang;
                count++;
                //  System.out.println(count++ + ") "+ lang);
            }
        }

        return null;
    }
    public void printSlovar() {
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
    }
    // метод поиска слова по первым буквам
    public void searchWord(String lang, String search) {
        int count = 1;
        search = search.toLowerCase();
        if (dictionary.containsKey(lang)) {
            for (String word : dictionary.get(lang).keySet()) {
                if (word.startsWith(search)) {
                    if (count == 1)
                        System.out.println("Search : " + search);
                    System.out.println(count++ + ") " + word);
                }
            }
            if (count == 1) {
                System.out.println("Такого слова нет !!!");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Словарь!");
        String action;
        Scanner scanner = new Scanner(System.in);
        Slovar slovar = new Slovar();

        slovar.addLang("ru");
        slovar.addLang("az");
        slovar.addLang("en");
        slovar.addLang("ru");


        System.out.println("Index");
        slovar.printLang();
        System.out.println();
        System.out.println(slovar.getLangByIndex(4));
//        do {
//
//            System.out.println("1 Добавить расскалдку");
//            System.out.println("2 Добавить слово");
//            System.out.println("3 Добавить несколько слов");
//            System.out.println("4 Напечатать расскладку");
//            System.out.println("5 Напечатать словарь");
//            System.out.println("6 Удаление расскалдки");
//            System.out.println("7 Поиск слова");
//            System.out.println("8 Выход");
//
//            action = scanner.nextLine();
//
//            if (action.equals("1")){
//            System.out.println("Выведите расскалдку");
//            String lang = scanner.nextLine();
//            slovar.addLang(lang);
//            }else if (action.equals("2")){
//                System.out.println("Введите язык слова");
//            String langword =scanner.nextLine();
//                System.out.println("Введите исходное слово");
//            String orginalWord =scanner.nextLine();
//                System.out.println("Введите слово перевод");
//            String translateWord =scanner.nextLine();
//            slovar.addWord(langword,orginalWord,translateWord);
//            slovar.printSlovar();
//            }  else if (action.equals("3")){
//            System.out.println("3");
//            }  else if (action.equals("4")){
//            System.out.println("4");
//            }  else if (action.equals("5")){
//            System.out.println("5");
//            }  else if (action.equals("6")){
//            System.out.println("6");
//            }else if (action.equals("7")){
//            System.out.println("7");
//            }else if (action.equals("8")){
//            System.out.println("Выход");
//            break;
//            }else {
//                System.out.println("Неправильный выбор, только от (1-8)");
//            }
//
//
//        }while (true);

















//        Slovar slovar = new Slovar();
//        System.out.println(slovar.addLang("ru"));
//        System.out.println(slovar.addWord("ru", "cat", "котенок"));
//        System.out.println(slovar.addWord("ru", "cat", "кошка"));
//        System.out.println(slovar.addLang("en"));
//        System.out.println(slovar.addLang("en"));
//        System.out.println(slovar.addLang("fr"));
//        System.out.println(slovar.removeLang("pr"));

//        ArrayList<String> arr = new ArrayList<String>();
//        arr.add("Кошка");
//        arr.add("Кот");
//        arr.add("Котенок");
//
//        slovar.addWord("ru", "cat", arr);
//        slovar.addWord("ru", "card", arr);
//        slovar.addWord("ru", "capitan", arr);
//        slovar.addWord("ru", "catalog", arr);

//        slovar.printSlovarLang("ru");
//        slovar.searchWord("ru", "catal");
//        slovar.printSlovar();
//        slovar.printLang();

    }
}
