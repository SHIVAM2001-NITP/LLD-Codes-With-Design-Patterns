package Client;

import Context.Context;
import Expression.AbstractExpression;
import Expression.MultiplyNonTerminalExpression;
import Expression.NumberTerminalExpression;

public class Client {
    public static void main(String[] args){
        //initialize the context
         Context context=new Context();
         context.put("a",2);
         context.put("b",4);

         //a*b
        AbstractExpression expression1=new MultiplyNonTerminalExpression(new NumberTerminalExpression("a"),new NumberTerminalExpression("b"));
        System.out.println(expression1.interpret(context));
    }
}
