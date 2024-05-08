public class Equation {
    private String name;
    private String base;
    private String term1;
    private String term2;
    String answer;

    public Equation(int num, String base){
        this.name = "EQ" + num;
        this.base = base;
        answer = solver(base);
    }
    public String solver(String base){
        // find groups and run solve on them, then replace
        base = collapseGroups(base);
        //exponent check
        // find mult or div and split there to solve
        //solveMD();
        // find add/sub and split there to solve
        //solveAS();
        answer = base;
        return answer;
    }
    public String collapseGroups(String base){
        String subBase; // placeholder strings
        String newBase;
        if (base.indexOf('(') >= 0 && base.indexOf(')') >= 0){
            subBase = base.substring(base.lastIndexOf('(')+1);
            subBase = subBase.substring(0,subBase.indexOf(')')); //isolate latest occurring, innermost parentheses group
            String start = base.substring(0, base.lastIndexOf('('));
            String mid = solver(subBase);
            String end = base.substring(start.length() + mid.length()+2);
            newBase = start + mid + end ; // reconstruct the answer to what was in parentheses with whatever was surrounding them
            base = collapseGroups(newBase);
        }
        return base;
    }
    public void breakUp( char axis){
        char[] ops = {'+', '-', '*', '/'};
        int place = -1;
        for (int i = 3; i > -1; i--){
            if(base.indexOf(ops[i]) > -1){
                place = base.indexOf(ops[i]);
            }
        }
        if (place > -1) {
            term1 = base.substring(0, place);
            term2 = base.substring(place + 1);

        }

    }
    public static boolean find(String field, char query){
        //returns place where defined character is, if it exists in the string
        return field.indexOf(query) > -1;
    }
    @Override
    public String toString(){
        return name + ":" + base + " = " + answer;
    }

}
