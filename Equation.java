public class Equation {
    private String base;
    private String term1;
    private String term2;

    public Equation(String base){
        this.base = base;
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
        return field.indexOf(query) > -1;
    }

}
