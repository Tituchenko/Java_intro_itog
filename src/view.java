import java.util.*;

public class view {
    private static String brendTitle="Производитель";
    private static String modelTitle="Модель";
    private static String diagTitle="Диагональ";
    private static String ramTitle="Оперативная память";
    private static String hddTitle="Жесткий диск";
    private static String osTitle="Операционная система";
    private static String colorTitle="Цвет";
    private static int maxLenghtBrend;
    private static int maxLenghtModel;

    private static int maxLenghtDiag;

    private static int maxLenghtRam;

    private static int maxLenghtHDD;
    private static int maxLenghtOS;
    private static int maxLenghtColor;
    private static int maxLengthCount;


    private static String separator="     ";
    private static Map<String,Integer> avalibleBrend=new HashMap<>();
    private static Map<Float,Integer> avalibleDiag=new HashMap<>();
    private static Map<Integer,Integer> avalibleRam=new HashMap<>();
    private static Map<Integer,Integer> avalibleHDD=new HashMap<>();
    private static Map<String,Integer> avalibleOs=new HashMap<>();

    private static Map<String,Integer> avalibleColor=new HashMap<>();

private static void setDefaultLength(){
    maxLenghtBrend=brendTitle.length();
    maxLenghtModel=modelTitle.length();
    maxLenghtDiag=diagTitle.length();
    maxLenghtRam=ramTitle.length();
    maxLenghtHDD=hddTitle.length();
    maxLenghtOS=osTitle.length();
    maxLengthCount=colorTitle.length();
}
private static int getByteLength (int gbSize){
    if (gbSize>1000) {
        gbSize=gbSize/1000;
    }
    return Integer.toString(gbSize).length();
}
public static void getMaxLenth(ArrayList<Notebook> notebook){
    setDefaultLength();
    maxLengthCount=Integer.toString(notebook.size()).length();
    for (Notebook n:notebook) {
        if (n.brend.length()>maxLenghtBrend){
            maxLenghtBrend=n.brend.length();
        }
        if (n.model.length()>maxLenghtModel){
            maxLenghtModel=n.model.length();
        }
//        int diagLenth=Float.toString(n.diag).length();
//        if (diagLenth>maxLenghtDiag){
//            maxLenghtDiag=diagLenth;
//        }
//        int ramSizeLength=getByteLength(n.ram);
//        if (ramSizeLength>maxLenghtRam){
//            maxLenghtRam=ramSizeLength;
//        }
//        int hddSizeLength=getByteLength(n.hdd);
//        if (hddSizeLength>maxLenghtHDD){
//            maxLenghtHDD=hddSizeLength;
//        }
        if (n.OS.length()>maxLenghtOS){
            maxLenghtOS=n.OS.length();
        }
        if (n.color.length()>maxLenghtColor){
            maxLenghtColor=n.color.length();
        }
        if (avalibleBrend.containsKey(n.brend)){
            avalibleBrend.put(n.brend,avalibleBrend.get(n.brend)+1);
        }else {
            avalibleBrend.put(n.brend,1);
        }
        if (avalibleDiag.containsKey(n.diag)){
            avalibleDiag.put(n.diag,avalibleDiag.get(n.diag)+1);
        }else {
            avalibleDiag.put(n.diag,1);
        }
        if (avalibleRam.containsKey(n.ram)){
            avalibleRam.put(n.ram,avalibleRam.get(n.ram)+1);
        }else {
            avalibleRam.put(n.ram,1);
        }
        if (avalibleHDD.containsKey(n.hdd)){
            avalibleHDD.put(n.hdd,avalibleHDD.get(n.hdd)+1);
        }else {
            avalibleHDD.put(n.hdd,1);
        }
        if (avalibleOs.containsKey(n.OS)){
            avalibleOs.put(n.OS,avalibleOs.get(n.OS)+1);
        }else {
            avalibleOs.put(n.OS,1);
        }

        if (avalibleColor.containsKey(n.color)){
            avalibleColor.put(n.color,avalibleColor.get(n.color)+1);
        }else {
            avalibleColor.put(n.color,1);
        }

    }

}
    public static String showAttribute (String a,int maxLength){
        return a+" ".repeat(maxLength-a.length());
    }

    private static String getgbSizeString(int gb){
        if (gb>=1000){
            return Integer.toString(gb/1000)+"TB";
        }
        return Integer.toString(gb)+"GB";
    }
    public static boolean checkFilter (Notebook n,Map<String,UserFilter> userFilter){
        for (var entry : userFilter.entrySet()) {
            if (entry.getValue().Values.size() > 0) {
                //System.out.println(entry.getKey() + ":" + entry.getValue().Values);
                switch (entry.getKey()){
                    case "Производитель":
                    if (!entry.getValue().Values.contains(n.brend)){
                        return false;
                    }
                    break;
                    case "Цвет":
                        if (!entry.getValue().Values.contains(n.color)){
                            return false;
                        }
                    break;
                    case "Операционная система":
                        if (!entry.getValue().Values.contains(n.OS)){
                            return false;
                        }
                        break;
                    case "Диагональ":
                        if (!entry.getValue().Values.contains(Float.toString(n.diag))){
                            return false;
                        }
                        break;
                    case "Оперативная память":
                        if (!entry.getValue().Values.contains(Integer.toString(n.ram))){
                            return false;
                        }
                        break;
                    case "Жесткий диск":
                        if (!entry.getValue().Values.contains(Integer.toString(n.hdd))){
                            return false;
                        }
                        break;
                }
            }
            if (entry.getValue().min!=0){
                switch (entry.getKey()){
                    case "Диагональ":
                        if (entry.getValue().min>n.diag){
                            return false;
                        }
                        break;
                    case "Оперативная память":
                        if (entry.getValue().min>n.ram){
                            return false;
                        }
                        break;
                    case "Жесткий диск":
                        if (entry.getValue().min>n.hdd){
                            return false;
                        }
                        break;
                }
            }
            if (entry.getValue().max!=0){
                switch (entry.getKey()){
                    case "Диагональ":
                        if (entry.getValue().max<n.diag){
                            return false;
                        }
                        break;
                    case "Оперативная память":
                        if (entry.getValue().max<n.ram){
                            return false;
                        }
                        break;
                    case "Жесткий диск":
                        if (entry.getValue().max<n.hdd){
                            return false;
                        }
                        break;
                }
            }

        }
    return true;
    }

    public static void printNotebook(ArrayList<Notebook> notebook,Map<String,UserFilter> userFilter){
    showUserFilter(userFilter);
        int i=1;
        System.out.println(showAttribute("#",maxLengthCount+1)+showAttribute(brendTitle,maxLenghtBrend)+
                separator+showAttribute(modelTitle,maxLenghtModel)+
                separator+showAttribute(diagTitle,maxLenghtDiag)+
                separator+showAttribute(ramTitle,maxLenghtRam)+
                separator+showAttribute(hddTitle,maxLenghtHDD)+
                separator+showAttribute(osTitle,maxLenghtOS)+
                separator+showAttribute(colorTitle,maxLenghtColor));
        for (Notebook n:notebook) {
            if (checkFilter(n,userFilter)) {
                System.out.println(showAttribute(Integer.toString(i) + ".", maxLengthCount + 1) +
                        showAttribute(n.brend, maxLenghtBrend) +
                        separator + showAttribute(n.model, maxLenghtModel) +
                        separator + showAttribute(Float.toString(n.diag) + "''", maxLenghtDiag) +
                        separator + showAttribute(getgbSizeString(n.ram), maxLenghtRam) +
                        separator + showAttribute(getgbSizeString(n.hdd), maxLenghtHDD) +
                        separator + showAttribute(n.OS, maxLenghtOS) +
                        separator + showAttribute(n.color, maxLenghtColor));
                i++;
            }

        }
    }

    public static String showMainMenu(int allCount){
        System.out.println("Главное меню:");
        System.out.println("1.Вывести список ноутбуков [В базе:" + allCount + "].");
        System.out.println("2.Добавить фильтр.");
        System.out.println("0.Закончить");
        Scanner scanner = new Scanner(System.in);
        String p = scanner.nextLine();
        return p;
    }
    public static String showAddFilterMainMenu(){
        System.out.println("Добавить фильтр по:");
        System.out.println("1.Производитель ноутбука [Вариантов:" +avalibleBrend.size()+"].");
        System.out.println("2.Размер экрана [Вариантов:" +avalibleDiag.size()+"] Мин/Макс support.");
        System.out.println("3.Объем оперативной памяти [Вариантов:" +avalibleRam.size()+"] Мин/Макс support.");
        System.out.println("4.Объем HDD [Вариантов:" +avalibleHDD.size()+"] Мин/Макс support.");
        System.out.println("5.Операционная система [Вариантов:" +avalibleOs.size()+"].");
        System.out.println("6.Цвет[Вариантов:" +avalibleColor.size()+"].");

        System.out.println("0.Назад");
        Scanner scanner = new Scanner(System.in);
        String p = scanner.nextLine();
        return p;
    }
    public static void showUserFilter(Map<String,UserFilter> userFilter){
        System.out.println("Фильтры пользователя :");
        for (var entry : userFilter.entrySet()) {
            if (entry.getValue().Values.size() > 0) {

                    System.out.println(entry.getKey() + ":" + entry.getValue().Values);

            }
            if (entry.getValue().min!=0){
                System.out.println(entry.getKey() + " минимальное значение: "+Float.toString(entry.getValue().min));
            }
            if (entry.getValue().max!=0){
                System.out.println(entry.getKey() + " максимальное значение: "+Float.toString(entry.getValue().max));
            }
        }


    }
    public static String showAddFilter(String typeFilter,Map<String,UserFilter> userFilter){
        Map<Integer,String> values=new HashMap<>();
        String p;
        int i = 1;
        do {
            switch (typeFilter) {
                case "Производитель":
                    System.out.println("Добавить в фильтр производителя:");
                   i = 1;
                    for (String brend : avalibleBrend.keySet()) {
                        if ((userFilter.containsKey(typeFilter)) && userFilter.get(typeFilter).Values.contains(brend)) {
                            System.out.println("*["+Integer.toString(i) + "." + brend + " [Моделей:" + avalibleBrend.get(brend) + "]]*");
                        } else {
                            System.out.println(Integer.toString(i) + "." + brend + " [Моделей:" + avalibleBrend.get(brend) + "]");
                        }
                        values.put(i, brend);
                        i++;
                    }
                    break;
                case "Цвет":
                    System.out.println("Добавить в фильтр цвет:");
                    i = 1;
                    for (String color : avalibleColor.keySet()) {
                        if ((userFilter.containsKey(typeFilter)) && userFilter.get(typeFilter).Values.contains(color)) {
                            System.out.println("*["+Integer.toString(i) + "." + color + " [Моделей:" + avalibleColor.get(color) + "]]*");
                        } else {
                            System.out.println(Integer.toString(i) + "." + color + " [Моделей:" + avalibleColor.get(color) + "]");
                        }
                        values.put(i, color);
                        i++;
                    }
                    break;
                case "Операционная система":
                    System.out.println("Добавить в фильтр операционную систему:");
                    i = 1;
                    for (String os : avalibleOs.keySet()) {
                        if ((userFilter.containsKey(typeFilter)) && userFilter.get(typeFilter).Values.contains(os)) {
                            System.out.println("*["+Integer.toString(i) + "." + os + " [Моделей:" + avalibleOs.get(os) + "]]*");
                        } else {
                            System.out.println(Integer.toString(i) + "." + os + " [Моделей:" + avalibleOs.get(os) + "]");
                        }
                        values.put(i, os);
                        i++;
                    }
                    break;
                case "Диагональ":
                    System.out.println("Добавить в фильтр диагональ:");
                    i = 1;
                    for (Float diag : avalibleDiag.keySet()) {
                        if ((userFilter.containsKey(typeFilter)) && userFilter.get(typeFilter).Values.contains(Float.toString(diag))) {
                            System.out.println("*["+Integer.toString(i) + "." + Float.toString(diag) + " [Моделей:" + avalibleDiag.get(diag) + "]]*");
                        } else {
                            System.out.println(Integer.toString(i) + "." + Float.toString(diag) + " [Моделей:" + avalibleDiag.get(diag) + "]");
                        }
                        values.put(i, Float.toString(diag));
                        i++;
                    }
                    System.out.println("min. Минимальное значение");
                    System.out.println("max. Максимальное значение");
                    break;
                case "Оперативная память":
                    System.out.println("Добавить в фильтр оперативную память:");
                    i = 1;
                    for (Integer ram : avalibleRam.keySet()) {
                        if ((userFilter.containsKey(typeFilter)) && userFilter.get(typeFilter).Values.contains(Integer.toString(ram))) {
                            System.out.println("*["+Integer.toString(i) + "." + getgbSizeString(ram) + " [Моделей:" + avalibleRam.get(ram) + "]]*");
                        } else {
                            System.out.println(Integer.toString(i) + "." + getgbSizeString(ram) + " [Моделей:" + avalibleRam.get(ram) + "]");
                        }
                        values.put(i, Integer.toString(ram));
                        i++;
                    }
                    System.out.println("min. Минимальное значение в GB");
                    System.out.println("max. Максимальное значение в GB");
                    break;
                case "Жесткий диск":
                    System.out.println("Добавить в фильтр жесткий диск:");
                    i = 1;
                    for (Integer hdd : avalibleHDD.keySet()) {
                        if ((userFilter.containsKey(typeFilter)) && userFilter.get(typeFilter).Values.contains(Integer.toString(hdd))) {
                            System.out.println("*["+Integer.toString(i) + "." + getgbSizeString(hdd) + " [Моделей:" + avalibleHDD.get(hdd) + "]]*");
                        } else {
                            System.out.println(Integer.toString(i) + "." + getgbSizeString(hdd) + " [Моделей:" + avalibleHDD.get(hdd) + "]");
                        }
                        values.put(i, Integer.toString(hdd));
                        i++;
                    }
                    System.out.println("min. Минимальное значение в GB");
                    System.out.println("max. Максимальное значение в GB");
                    break;
            }


            System.out.println("0.Назад");
            Scanner scanner = new Scanner(System.in);
            p = scanner.nextLine();
        } while (!p.equals("min")  && !p.equals("max") && !values.containsKey(Integer.parseInt(p)) && !p.equals("0") );
        if ( p.equals("0")){
            return "0";
        }
        if ( p.equals("min")){
            return "min";
        }
        if ( p.equals("max")){
            return "max";
        }
        return values.get(Integer.parseInt(p));
    }



    }


