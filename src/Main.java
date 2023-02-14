import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /*
    Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки,
отвечающие фильтру. Критерии фильтрации можно хранить в Map.
Например: “Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации
можно также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
     */
    private static Map<String,UserFilter> userFilter =new HashMap<>();

    public static void main(String[] args) {
        ArrayList<Notebook> notebook = converToNotebook(database.getNotebookFromFile());
        view.getMaxLenth(notebook);
        mainMenu(notebook);
    }

    public static void mainMenu(ArrayList<Notebook> notebook) {
        String menu = "";
        Boolean runAgain = true;
        do {
            menu = view.showMainMenu(notebook.size());
            switch (menu) {
                case "1":
                    view.printNotebook(notebook,userFilter);
                    break;
                case "2":
                    addFilterMainMenu(notebook);
                    break;
                case "0":
                    runAgain = false;
                    break;
                default:
                    System.out.println("Ошибка ввода пункта!");
                    break;
            }

        } while (runAgain);
    }

    public static void addFilterMainMenu(ArrayList<Notebook> notebook) {
        String menu = "";
        Boolean runAgain = true;
        do {
            menu = view.showAddFilterMainMenu();
            switch (menu) {
                case "1":
                    addFilterMenu("Производитель");
                    break;
                case "2":
                    addFilterMenu("Диагональ");
                    break;
                case "3":
                    addFilterMenu("Оперативная память");
                    break;
                case "4":
                    addFilterMenu("Жесткий диск");
                    break;
                case "5":
                    addFilterMenu("Операционная система");
                    break;
                case "6":
                    addFilterMenu("Цвет");
                    break;
                case "0":
                    runAgain = false;
                    break;
                default:
                    System.out.println("Ошибка ввода пункта!");
                    break;
            }

        } while (runAgain);
    }
    public static void addFilterMenu(String typeFilter){
        String menu;
        do {
            view.showUserFilter(userFilter);
            menu = view.showAddFilter(typeFilter,userFilter);
                if (menu.equals("0")){
                    break;
                } else {
                    addFilter(typeFilter,menu);
                }
        } while (!menu.equals("0"));



    }


    public static int getSizeByte(String s){
        s=s.replace("TB","000");
        s=s.replace("GB","");
        return Integer.parseInt(s);
    }
    public static ArrayList<Notebook> converToNotebook(ArrayList<String> text){
        ArrayList<Notebook> notebook = new ArrayList<>();
        for (int i = 1; i < text.size(); i++) {
            String[] s=text.get(i).split(";");
            Notebook n = new Notebook();
            n.brend=s[0];
            n.model=s[1];

            n.diag=Float.valueOf( s[2].replace("\"",""));
            n.ram=getSizeByte( s[3]);
            n.hdd=getSizeByte(s[4]);
            n.OS=s[5];
            n.color=s[6];
            notebook.add(n);
        }
        return notebook;
    }
    public static void addFilter(String typeFilter, String menu) {
        if (userFilter.containsKey(typeFilter)) {
            UserFilter filter = userFilter.get(typeFilter);
            if (menu.equals("min") || menu.equals("max")) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите пороговое значение: ");
                String val = scanner.nextLine();
                if (menu.equals("min")) {
                    filter.min = Float.parseFloat(val);
                    filter.Values.clear();
                } else {
                    filter.max = Float.parseFloat(val);
                    filter.Values.clear();
                }


            } else {
                filter.min = 0;
                filter.max = 0;
                if (menu.equals("min") || menu.equals("max")) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Введите пороговое значение: ");
                    String val = scanner.nextLine();
                    if (menu.equals("min")) {
                        filter.min = Float.parseFloat(val);
                        filter.Values.clear();
                    } else {
                        filter.max = Float.parseFloat(val);
                        filter.Values.clear();
                    }
                } else {
                    if (filter.Values.contains(menu)) {
                        filter.Values.remove(menu);
                    } else {
                        filter.Values.add(menu);
                    }
                }
                userFilter.put(typeFilter, filter);
            }
        } else {
            UserFilter filter = new UserFilter();

            if (menu.equals("min") || menu.equals("max")) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите пороговое значение: ");
                String val = scanner.nextLine();
                if (menu.equals("min")) {
                    filter.min = Float.parseFloat(val);
                    filter.Values.clear();
                } else {
                    filter.max = Float.parseFloat(val);
                    filter.Values.clear();
                }

            } else {
                filter.Values.add(menu);


            }
            userFilter.put(typeFilter, filter);
        }
    }


}