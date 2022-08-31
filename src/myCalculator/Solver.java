package myCalculator;

interface Eval{
    public double eval(String leftOp, String rightOp);
}

public class Solver{

    /* 
	AUTHOR: TOMIWA ADESANYA
	EMAIL: t.g.adesanya392@gmail.com
	DESC: Solver is a non-complex java class to evaluate simple mathematical expressions of basic arithmetic operations add(+), subtract(-), multiply(x or *),
	division(/), and power(**  or ^). The equation is simply passed as argument to the Class' constructor and the non-static method eval() is called to 
	evaluate equation and get a result/solution. 
	EXAMPLE: 
		Solver equationOne = new Solver("2 + 5 x 2");
		System.out.println(equationOne.eval()); // Prints 12
	NOTE: Feel free to modify code and Open a pull request on github. 
    */


    // Attributes of class
    private String equation; // Equation to be solved
    private Float solution;  
    private static Eval add = (leftOp, rightOp) -> {return Double.parseDouble(leftOp) + Double.parseDouble(rightOp);}; // adds left operand to right operand
    private static Eval sub = (leftOp, rightOp) -> {return Double.parseDouble(leftOp) - Double.parseDouble(rightOp);}; // subtracts right operand from left operand
    private static Eval mul = (leftOp, rightOp) -> {return Double.parseDouble(leftOp) * Double.parseDouble(rightOp);}; // multiplies left operand with right operand
    private static Eval div = (leftOp, rightOp) -> {return Double.parseDouble(leftOp) / Double.parseDouble(rightOp);};  // divides left operand by right operand
    private static Eval pow = (leftOp, rightOp) -> {return Math.pow(Double.parseDouble(leftOp), Double.parseDouble(rightOp));}; // raised left operand to the power of right operand

    // Class Constructor
    public Solver(String eq){
        eq = replace(eq, " ", "");
        eq = replace(eq, "+", " + ");
        eq = replace(eq, "-", " - ");
        eq = replace(eq, "*", " * ");
        eq = replace(eq, "x", " x " );
        eq = replace(eq, "/", " / ");
        eq = replace(eq, "^", " ^ ");
        this.equation = eq; // Equation to be solved
    }

    // PRIVATE METHODS DEFINITION

    private void evaluate(String symbol, Eval operator){
        // Replaces subequations of an equation with solution to the subequation
        double value;
        String leftOp, rightOp; 
        
        while(this.equation.contains(symbol)){
            String[] equationArr = this.equation.split(" ");
            String subEquation;
            int symbolIndex = getIndex(equationArr, symbol);

            if (symbolIndex == 909) break; // 409 is the int value returned if symbol isnt in the array

            leftOp = equationArr[symbolIndex-1];
            rightOp = equationArr[symbolIndex+1];

            value = operator.eval(leftOp, rightOp);

            subEquation = leftOp + " " + symbol + " " + rightOp;
            this.equation = this.equation.replace(subEquation, String.valueOf(value));
        }
    }

    private static int getIndex(String[] stringArr, String value){
        // Returns the index of a specified value in a String array if the value is present in array otherwise returns 909 
        for (int i=0; i < stringArr.length; i++){
            if (stringArr[i].equals(value)){
                return i;
            }
        }
        return 909; // Returned if value not in string array
    }

    private static String replace(String eq, String oldStr, String newStr){
        // Replaces an old substring with a new substring if string contains substring
        if (eq.contains(oldStr)){
            eq = eq.replace(oldStr, newStr);
        }
        return eq;
    }

   // PUBLIC METHOD DEFINITION

    public String eval(){
	// Evaluates equation and returns solution
        this.evaluate("^", pow);
        this.evaluate("x", mul);
        this.evaluate("*", mul);
        this.evaluate("/", div);
        this.evaluate("+", add);
        this.evaluate("-", sub);

        return this.equation;
    }
}