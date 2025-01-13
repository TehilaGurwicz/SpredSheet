
import java.util.Objects;
public class SCell implements Cell {
    private String line;
    private int type;
    private String name;
    private int dpth;
    // Add your code here
    public SCell(String s) {
        setData(s);
        this.type = getType();
    }
    public String getName(){return name;}
    public void setName(String n){ this.name = n;}
    @Override
    public int getOrder() {
        return this.dpth;
    }
    /**
     * this algorithm checks if a string has an invalid char and every opening parenthesis has closing parenthesis
     * @param form and return if the string is a valid formula.
     * **/
    public static boolean is_form(String form) {
        if (form == null || form.length() < 2) return false;
        if (form.charAt(0) != '=') return false;
        if (form.substring(1).matches(".*[0-9]+[a-zA-Z].*")) return false;
        if (form.substring(1).contains("=")) return false;
        if (Character.isLetter(form.charAt(form.length() - 1))) return false;
        if (Helpfull.sCount(form, '(') != Helpfull.sCount(form, ')')) return false;
        if (form.endsWith(".")) return false;
        for (int i = 0; i < form.length() - 1; i++)
        {
            char currentChar = form.charAt(i);
            char nextChar = form.charAt(i + 1);
            if (currentChar == '.' && nextChar == '.') return false;
            if (currentChar == '.' && !Character.isDigit(nextChar)) return false;
            if (Character.isLetter(currentChar) && !(Character.isDigit(nextChar) || nextChar == '(')) return false;
            if (Helpfull.operator(currentChar) && !(Character.isLetter(nextChar) || Character.isDigit(nextChar) || nextChar == '(')) return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return getData();
    }
    @Override
    public void setData(String s) {
        line = s;
    }
    @Override
    public String getData() {
        return line;
    }
    /**checks if a string is a number
     **/
    public static boolean isNumber(String str) {
        try {
            double s = Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean isText(String str) {
        return !isNumber(str) && !str.startsWith("=");
    }
    /**
     * checks the type of cell based on its data
     * if isNumber is true return Number
     * if isForm is true return Form
     * if isText is true return Text
     * if the data is formula but invalid return Err_Form
     * if subcell is defined by itself return Err_Cycle
     * **/
    @Override
    public int getType() {
        if (this.type == Ex2Utils.ERR_FORM_FORMAT) return Ex2Utils.ERR_FORM_FORMAT;
        if(getData()==null){return Ex2Utils.TEXT;}
        if (getData().isEmpty())return Ex2Utils.TEXT;
        if(Objects.equals(getData(), Ex2Utils.ERR_FORM)){return Ex2Utils.ERR_FORM_FORMAT;}
        if(this.dpth==Ex2Utils.ERR_CYCLE_FORM)return Ex2Utils.ERR_CYCLE_FORM;
        String tmp_dta = getData();
        if(getData().charAt(0)=='='&&!is_form(getData())|| tmp_dta.replace("=","").isEmpty()){return Ex2Utils.ERR_FORM_FORMAT;}
        if (isText(getData())) {return Ex2Utils.TEXT;}
        if (isNumber(getData())) {return Ex2Utils.NUMBER;}
        if (is_form(getData())) {return Ex2Utils.FORM;}
        return -1;
    }

    @Override
    public void setType(int t) {
        type = t;
    }
    public static boolean is_valid(Cell cell)
    {
        return (is_form(cell.getData()) || isNumber(cell.getData())|| isText(cell.getData()));
    }

    @Override
    public void setOrder(int t) {
        this.dpth = t;
    }
}