//package assignments;
import java.io.BufferedReader;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.io.FileReader;
/**
 * Overview:Ex2Sheet class is a code for a digital spreadsheet with cells that can contain a text, numbers and a specific type of formulas
 It includes methods for spreadsheet manipulation, evaluation, handling circular references, and performing arithmetic operations.
 **/
public class Ex2Sheet implements Sheet {
    private Cell[][] table;
    private Set<Cell> visitedCells = new HashSet<>(); //  (used in set_Depth) Track Subcells that was chacked
    public Ex2Sheet(int x, int y)

    {
        table = new SCell[x][y];
        for(int i=0;i<x;i=i+1)
        {
            for(int j=0;j<y;j=j+1)
            {
                table[i][j] = new SCell("");
                table[i][j].setName(Helpfull.intToChar(i)+""+j);
                table[i][j].setType(table[i][j].getType());
            }
        }
        eval();
    }
    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);
    }
    /**
     * this method returns the value of a cell
     * if isText true return the text
     * if Err - return the ERR string
     * if isNumber true return the number
     * if isForm true calculate the formula with computeForm
     * **/
    @Override
    public String value(int x, int y)
    {
        Cell c = get(x,y);
        if (c.getType() == Ex2Utils.ERR_CYCLE_FORM)
        {
            return "ERR_Cycle_Form";}
        return switch (c.getType())
        {
            case Ex2Utils.ERR_FORM_FORMAT -> "ERR_Form_Format";
            case Ex2Utils.TEXT -> c.getData();
            case Ex2Utils.NUMBER -> String.valueOf(Double.parseDouble(c.getData()));
            case Ex2Utils.FORM -> String.valueOf(eval(x, y));
            default -> throw new IllegalArgumentException("Unknown cell type: " + c.getType());
        };

    }
    @Override
    public Cell get(int x, int y)
    {
        return table[x][y];
    }
    /**
     * This method sets the value of 'ce' to the cell at (x,y) and sets the cell's "name" to accordingly
     **/
    @Override
    public void set(int x, int y, String ce)
    {
        Cell c = new SCell(ce);
        table[x][y] = c;
        c.setName(Helpfull.intToChar(x)+""+y);
    }
    /**
     * return the cell in the cord
     * **/
    @Override
    public Cell get(String cords)
    {
        Cell ans = null;
        ans =  this.table[Helpfull.char2num(cords.charAt(0))][Integer.parseInt(cords.substring(1))];
        return ans;
    }
    @Override
    public int height() // returns this.height
    {
        return table[0].length;
    }

    @Override
    public int width() { // returns this.length
        return table.length;
    }
    /**
     * return if (xx,yy) cords are within the table's boundaries
     **/
    @Override
    public boolean isIn(int xx, int yy)
    {
        boolean ans = xx>=0 && yy>=0;
        if(ans)
        {Cell cell = get(xx, yy);
            return cell!=null;}
        return false;
    }
    /**This algorithm clears the table**/
    private void clear()
    {
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                this.table[i][j] = new SCell("");
            }
        }
    }
    /**
     * returns an array than contain the depth of the cells in @table.
     * **/
    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                ans[i][j] = setDepth(table[i][j]);
                this.table[i][j].setOrder(ans[i][j]);
            }
        }
        return ans;
    }

    /**
     * The algorithm calculates values for all @table and update the printed value of the cells.
     **/
    @Override
    public void eval()
    {
        int[][] dpt = depth();
        for (int i = 0; i < width(); i++)
        {
            for (int j = 0; j < height(); j++)
            {
                table[i][j].setOrder(dpt[i][j]);
                table[i][j].setType(table[i][j].getType());
                if (SCell.is_form(table[i][j].getData())&&getSubCells(table[i][j])!=null)
                {
                    Cell subCell = getSubCells(table[i][j] );
                    if(!SCell.is_valid(subCell)||subCell.getData().isEmpty())
                        table[i][j].setType(Ex2Utils.ERR_FORM_FORMAT);
                }
            }
        }
    }

    /**
     * This method takes a Cell and calculates its depth
     * The method uses a Set (defined earlier) to identify circular references and avoid a Stack Overflow.
     * @param a is evaluated recursively, with the depth of each subcell within 'a' added to its total depth.
     **/
    public int setDepth(Cell a) {
        // Handle base cases
        if (a.getType() == Ex2Utils.TEXT) {
            return 0;
        }
        if (a.getType() == Ex2Utils.NUMBER) {
            return 0;
        }
        if (!SCell.is_form(a.getData())) {
            return Ex2Utils.ERR_FORM_FORMAT;
        }
        if (visitedCells.contains(a)) {
            return -1; // Circular reference detected
        }

        try {
            visitedCells.add(a); // Mark the current cell as visited
            int depth = 0; // Variable to store the maximum depth found

            String str = a.getData(); // Get the formula data from the cell
            for (int i = 0; i < str.length(); i++) {
                if (Character.isLetter(str.charAt(i))) {
                    int first = i;
                    // Extract the column (letters)
                    while (i < str.length() && Character.isLetter(str.charAt(i))) {
                        i++;
                    }
                    String column = str.substring(first, i);

                    // Extract the row (digits)
                    first = i;
                    while (i < str.length() && Character.isDigit(str.charAt(i))) {
                        i++;
                    }
                    String row = str.substring(first, i);

                    // Validate the cell reference
                    if (!row.isEmpty() && Helpfull.cellForm(column + row)) {
                        String b_name = column + row;
                        // Retrieve the sub-cell based on the reference
                        Cell subCell = table[Helpfull.char2num(b_name.charAt(0))][Integer.parseInt(b_name.substring(1))];

                        if (subCell == a) { // Self-reference detected
                            return -1;
                        }

                        // Recursively compute the depth of the sub-cell
                        int sub_depth = setDepth(subCell);
                        if (sub_depth == -1) { // Circular reference detected in the sub-cell
                            return -1;
                        }
                        depth = Math.max(depth, sub_depth + 1); // Update the maximum depth
                    }
                }
            }

            visitedCells.remove(a); // Remove the cell from the visited list after processing
            return depth; // Return the computed depth
        } catch (StackOverflowError e) {
            return -1; // Handle circular references causing stack overflow
        }
    }

    /**
     * This method reads the stored txt file and assigns each cell its appropriate value.
     * It utilizes Java's FileReader and BufferedReader.
     * **/
    @Override
    public void load(String fileName) throws IOException
    {
        clear();
        FileReader fr = new FileReader(fileName);//outer objects
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        int counter = 0;
        while (line != null)
        {
            line = br.readLine();
            counter++;
            if(line != null&&!line.isEmpty()&&counter!=0&& Helpfull.cellForm(line.substring(0,2).replace(" ","")))
            {
                String cord = line.substring(0,2).replace(" ","");
                int x = Helpfull.get_x(cord);
                int y = Helpfull.get_y(cord);
                set(x, y,line.replace(cord,"").replace(" ",""));
            }

        }
    }
    /**
     * This method saves the spreadsheet as a text file, ensuring each cell is assigned its corresponding value.
     * Utilizes Java's FileReader and BufferedReader classes for file operations.
     **/
    @Override
    public void save(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write("This is Txt File for " + fileName + "Using Ex2_Cs101 Sol");
        fw.write("\n");
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                if (!table[i][j].getData().isEmpty())
                { fw.write(table[i][j].getName()+" "+table[i][j].getData());}
                fw.write("\n");
            }
        }
        fw.close();
    }
    /**Utilizes the Evaluate method to determine the displayed value for cell (x, y)**/
    @Override
    public String eval(int x, int y) {
        Cell ans = null;
        if(get(x,y)!=null) {ans = get(x,y);}
        return String.valueOf(eValuate(ans));
    }

    /**
     * This method determines the final value of ce cell's formula, accounting for dependencies.
     * @param ce is evaluated: if it contains ce formula, the computeForm method is called. If it contains subcells, their values are computed by recursion first.
     * The algorithm returns the computed value as ce double.
     **/
    public double eValuate(Cell ce)
    {
        double value=0;
        ce.setData(ce.getData().toLowerCase());
        String str2eval;
        int a_depth = setDepth(ce);
        if (SCell.isNumber(ce.getData())){return Double.parseDouble(ce.getData());}
        if (SCell.is_form(ce.getData())&&a_depth==0&&!ce.getData().isEmpty())
        {   str2eval = ce.getData();
            return computeFrom(str2eval);
        }
        if (SCell.is_form(ce.getData())&&a_depth!=0&&!ce.getData().isEmpty())
        {   Cell c = getSubCells(ce);
            str2eval = ce.getData().replace(getSubCells(ce).getName(),String.valueOf(eValuate(getSubCells(ce))));
            Cell sub = new SCell(str2eval);
            if (containCell(str2eval)){ value = value + eValuate(sub);}
            if (!containCell(str2eval)) value =  value + computeFrom(str2eval);
        }
        return value;
    }
    /**
     * This function retrieves the value of an unempty  cell for a given subcell.
     * @param subCell's data is evaluated.
     **/
    public Cell getSubCells(Cell subCell)
    {
        if (subCell == null) return null;

        for (int i = 0; i < subCell.getData().length(); i++)
        {
            char currentChar = subCell.getData().charAt(i);
            if (Character.isLetter(currentChar))
            {
                currentChar = Character.toUpperCase(currentChar); // Normalize to uppercase
                if (currentChar >= 'A' && currentChar <= 'Z')
                {
                    if (i + 1 < subCell.getData().length() && Character.isDigit(subCell.getData().charAt(i + 1)))
                    {
                        int rowEnd = i + 2;
                        if (rowEnd < subCell.getData().length() && Character.isDigit(subCell.getData().charAt(rowEnd)))
                        {
                            rowEnd++;
                        }

                        int row = Integer.parseInt(subCell.getData().substring(i + 1, rowEnd));
                        if (row >= 0 && row <= 99) {
                            return table[Helpfull.char2num(currentChar)][row];}
                    }
                }
            }
        }
        return null;
    }
    /**
     * Boolean method - Determines if a given string includes a cell reference.
     * Validates if the provided string contains a proper cell reference.
     * @param str The input string to verify.
     **/
    private boolean containCell(String str)
    {  boolean ans = false;
        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isLetter(str.charAt(i)))
            {ans =  Helpfull.cellForm(str.substring(i,i+2));
                if (ans)return ans;}
            ans = Helpfull.cellForm(str.substring(i,i+1));
        }
        return ans;
    }
    /**
     * This function works with the following operators- "+,-,/,*" and processes parentheses.
     * @param str The input string representing the mathematical expression.
     * The expression may include numbers, arithmetic operators, and parentheses
     **/
    public static double computeFrom(String str) {
        String strP = str;
        strP = strP.replaceAll("\\s", ""); // Ignore spaces and equal signs
        strP = strP.replaceAll("=", "");
        if (SCell.isNumber(strP))return Double.parseDouble(strP);
        if (strP.contains("(")) {
            int openParen = strP.lastIndexOf('(');
            int closeParen = strP.indexOf(')', openParen);
            String innerForm = strP.substring(openParen + 1, closeParen);
            double value = computeFrom(innerForm);
            return computeFrom(strP.substring(0, openParen) + value + strP.substring(closeParen + 1));}
        for (int i = strP.length() - 1; i >= 0; i--)
        {
            char c = strP.charAt(i);
            if (c == '+' || c == '-')
            {
                double right = computeFrom(strP.substring(i + 1));
                double left = computeFrom(strP.substring(0, i));

                if (c == '+') return left + right;
                else return left - right;}
        }
        for (int i = strP.length() - 1; i >= 0; i--) {
            char chr = strP.charAt(i);
            if (chr == '*' || chr == '/') {
                double right = computeFrom(strP.substring(i + 1));
                double left = computeFrom(strP.substring(0, i));

                if (chr == '*') return left * right;
                else return left / right;
            }
        }
        return Double.parseDouble(strP);
    }
}