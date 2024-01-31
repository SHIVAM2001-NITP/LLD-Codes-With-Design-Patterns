package Client;

import Context.Context;
import Expression.*;

public class Client {
    public static void main(String[] args){
        //initialize the context
        Context context=new Context();
        context.put("a",2);
        context.put("b",4);
        context.put("c",8);
        context.put("d",16);

        //(a*b)+(c*d)
        Expression.AbstractExpression expression2=new SumNonTerminalExpression( new MultiplyNonTerminalExpression(new NumberTerminalExpression("a"),new NumberTerminalExpression("b")),
                new MultiplyNonTerminalExpression(new NumberTerminalExpression("c"),new NumberTerminalExpression("d")));
        System.out.println(expression2.interpret(context));
    }
}
