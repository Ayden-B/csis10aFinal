import java.math.BigDecimal;

public class Equation {
    private final String name;
    private final String base;
    String answer;

    public Equation(int num, String base){
        this.name = "EQ" + num;
        this.base = base;
        answer = solver(base);
    }
    public String solver(String base){
        if (valid(base)) {
            if (base.charAt(0) == '-'){
                base = "0" + base;
            }
            if (base.substring(base.length()-1).matches("[-(+*/]")){
                base = base.substring(0, base.length()-1);
            }
            if (base.substring(0, 1).matches("[+)*/]")){
                if (base.charAt(0) == ')'){
                    return "Error: Unclosed";
                }
                base = base.substring(1);
            }
            // find groups and run solve on them, then replace
            base = collapseGroups(base);
            //add exponent check
            // find add/sub and split there to solve
            base = solveAS(base);
            // find mult or div and split there to solve
            base = solveMD(base);
            if (base.endsWith(".0")){
                base = base.substring(0, base.length()-2);
            }
        } else if (base.contains("Error")){
        }else{
            base = "Error: Unsupported Character";
        }
        answer = base;
        return answer;
    }
    public String collapseGroups(String base){
        String subBase; // placeholder strings
        String newBase;

        if (base.indexOf('(') >= 0 && base.indexOf(')') >= 0){
            base = formatGroups(base);
            subBase = base.substring(base.lastIndexOf('(')+1);
            String end = subBase.substring(subBase.indexOf(')')+1);
            subBase = subBase.substring(0,subBase.indexOf(')')); //isolate latest occurring, innermost parentheses group
            String start = base.substring(0, base.lastIndexOf('('));
            String mid = solver(subBase);//solve said group
            if (mid == null){
                return "Error: Empty Group";
            }
            if (mid.contains("Error")){
                return mid;
            }
            newBase = start + mid + end ; // reconstruct the solved group with whatever was surrounding it
            base = collapseGroups(newBase);
        } else if((base.indexOf('(') >= 0 && !(base.indexOf(')') >= 0)) || (!(base.indexOf('(') >= 0) && base.indexOf(')') >= 0)){
            answer = "Error: Unclosed";
        }
        return base;
    }
    public String formatGroups(String base){
        int start = base.indexOf('(');
        if (base.substring(start-1, start).matches("[1-9]")){
            base = base.substring(0,start) + "*" + base.substring(start);
        }
        int end = base.lastIndexOf(')');
        if (end > -1 && (end +1) < base.length()){
            if (base.substring(end+1, end+2).matches("[1-9]")){
                base = base.substring(0, end +1) + "*" + base.substring(end+1);
            }
        }
        return base;
    }
    public String solveAS(String base) {
        String answer;
        BigDecimal term1;
        BigDecimal term2;
        int splitPoint;
        if (!base.contains("Error")) {
            if (base.contains("-") || base.contains("+")) {
                splitPoint = Math.max(base.lastIndexOf('-'), base.lastIndexOf('+'));
                term1 = new BigDecimal(solver(base.substring(0, splitPoint)));
                term2 = new BigDecimal(solver(base.substring(splitPoint+1)));
                if (base.charAt(splitPoint) == '+'){
                    answer = "" + (term1.add(term2));
                } else{
                    answer = "" + (term1.subtract(term2));
                }

                return answer;
            }
        }
        return base;
    }
    public String solveMD(String base){
        String answer;
        BigDecimal term1;
        BigDecimal term2;
        int splitPoint;
        if (!base.contains("Error")) {
            if (base.contains("*") || base.contains("/")) {
                splitPoint = Math.max(base.lastIndexOf('*'), base.lastIndexOf('/'));
                term1 = new BigDecimal(solver(base.substring(0, splitPoint)));
                term2 = new BigDecimal(solver(base.substring(splitPoint+1)));
                if (base.charAt(splitPoint) == '*'){
                    answer = "" + (term1.multiply(term2));
                } else{
                    if (term2.equals(new BigDecimal("0"))){
                        return "Error: Divide by Zero";
                    }
                    answer = "" + (term1.divide(term2));
                }

                return answer;
            }
        }
        return base;
    }
    public static int findChars(String field, char[] queries){
        int returner = field.length();
        for (char query : queries) {
            if ((field.indexOf(query) > -1) && (field.indexOf(query) < returner)) {
                returner = field.indexOf(query);
            }
        }
        return returner;
    }
    public static boolean valid(String entered){
        return entered.matches("[0-9().*/+-]+");
    }
    @Override
    public String toString(){
        return name + ":" + base + " = " + answer;
    }

}
