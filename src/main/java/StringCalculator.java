import java.util.ArrayList;

public class StringCalculator {
    public static int add(String parameter) throws Exception{
        String message = "ERROR: negatives not allowed ";
        int total = 0;
        if(parameter.isEmpty()) {
            return total;
        }
        if (parameter.startsWith("  ")){
            throw new Exception("ERROR: invalid input");
        }
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        if (parameter.startsWith("-")) {
            String[] nums = parameter.split(",");
            for (String str : nums){
                list.add(Integer.parseInt(str));
                if (Integer.parseInt(str)<0){
                    list2.add(Integer.parseInt(str));
                }
            }
            for(int i : list2){
                message += i + " ";
            }
            System.out.println(message);
            throw new Exception(message);
        }
        String delimiter = "[,\n;]";
        if(parameter.startsWith("//")){
            String[] partsOfString = parameter.split("\n", 2);
            delimiter = partsOfString[0].substring(2);
            delimiter = delimiter.replace("*", "\\*").replace("(","\\(")
                    .replace(")","\\)");
            delimiter = delimiter.replaceAll("\\]\\[","|");
            delimiter = delimiter.replaceAll("\\[", "").replaceAll("\\]","");
            parameter = partsOfString[1];
            if (parameter.endsWith(";")){
                throw new Exception("ERROR: invalid input");
            }
            if (delimiter.contains("//")){
                throw new Exception("ERROR: invalid input");
            }
        }
        String[] numbers = parameter.split(delimiter);
        for (String num : numbers) {
            String[] numbers2 = num.split(",");
            for (String num2 : numbers2) {
                if (Integer.parseInt(num2)>=1000){
                    continue;
                }
                total += Integer.parseInt(num2);
            }
        }
        System.out.println(total);
        return total;
    }
    public static void main(String[] args) {
        try {
            add("1,2,3//;\n1000,1;2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
