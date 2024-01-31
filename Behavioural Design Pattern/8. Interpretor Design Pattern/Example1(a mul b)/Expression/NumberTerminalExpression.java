package Expression;

import Context.Context;
import Expression.AbstractExpression;

public class NumberTerminalExpression implements AbstractExpression {
    String stringValue;
   public NumberTerminalExpression(String stringVal){
        this.stringValue=stringVal;
    }
    @Override
    public int interpret(Context context){
        return context.get(stringValue);
    }
}
