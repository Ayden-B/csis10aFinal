import java.math.BigDecimal;

public class Equation {
    private final String name;
    private String base;
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
            // find groups and run solve on them, then replace
            base = collapseGroups(base);
            //add exponent check
            // find add/sub and split there to solve
            base = solveAS(base);
            // find mult or div and split there to solve
            base = solveMD(base);
            answer = base;
        } else {
            answer = "Error: Unsupported Character";
        }

        return answer;
    }
    public String collapseGroups(String base){
        String subBase; // placeholder strings
        String newBase;

        if (base.indexOf('(') >= 0 && base.indexOf(')') >= 0){
            subBase = base.substring(base.lastIndexOf('(')+1);
            String end = subBase.substring(subBase.indexOf(')')+1);
            subBase = subBase.substring(0,subBase.indexOf(')')); //isolate latest occurring, innermost parentheses group
            String start = base.substring(0, base.lastIndexOf('('));
            String mid = solver(subBase);//solve said group
            newBase = start + mid + end ; // reconstruct the solved group with whatever was surrounding it
            base = collapseGroups(newBase);
        } else if((base.indexOf('(') >= 0 && !(base.indexOf(')') >= 0)) || (!(base.indexOf('(') >= 0) && base.indexOf(')') >= 0)){
            answer = "Error: Unclosed ";
        }
        return base;
    }
    public String solveAS(String base) {
        String answer;
        String term1;
        String term2;
        int splitPoint;
        if (!base.contains("Error")) {
            if (base.contains("-") || base.contains("+")) {
                if (base.lastIndexOf('-') > base.lastIndexOf('+')){
                    splitPoint = base.lastIndexOf('-');
                }else{
                    splitPoint = base.lastIndexOf('+');
                }
                term1 = solver(base.substring(0, splitPoint));
                BigDecimal _term1 = new BigDecimal(solver(base.substring(0, splitPoint)));
                term2 = solver(base.substring(splitPoint+1));
                BigDecimal _term2 = new BigDecimal(solver(base.substring(splitPoint+1)));
                if (base.charAt(splitPoint) == '+'){
                    answer = "" + (_term1.add(_term2));
                } else{
                    answer = "" + (_term1.subtract(_term2));
                }

                return answer;
            }
        }
        return base;
    }
    public String solveMD(String base){
        String answer;
        String term1;
        String term2;
        int splitPoint;
        if (!base.contains("Error")) {
            if (base.contains("*") || base.contains("/")) {
                if (base.lastIndexOf('*') > base.lastIndexOf('/')){
                    splitPoint = base.lastIndexOf('*');
                }else{
                    splitPoint = base.lastIndexOf('/');
                }
                term1 = solver(base.substring(0, splitPoint));
                BigDecimal _term1 = new BigDecimal(solver(base.substring(0, splitPoint)));
                term2 = solver(base.substring(splitPoint+1));
                BigDecimal _term2 = new BigDecimal(solver(base.substring(splitPoint+1)));
                if (base.charAt(splitPoint) == '*'){
                    answer = "" + (_term1.multiply(_term2));
                } else{
                    answer = "" + (_term1.divide(_term2));
                }

                return answer;
            }
        }
        return base;
    }
//    public void breakUp( char axis){
//        char[] ops = {'+', '-', '*', '/'};
//        int place = -1;
//        for (int i = 3; i > -1; i--){
//            if(base.indexOf(ops[i]) > -1){
//                place = base.indexOf(ops[i]);
//            }
//        }
//        if (place > -1) {
//            term1 = base.substring(0, place);
//            term2 = base.substring(place + 1);
//
//        }
//
//    }
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
    public static void main(String[] args) {

    }
    @Override
    public String toString(){
        return name + ":" + base + " = " + answer;
    }

}
