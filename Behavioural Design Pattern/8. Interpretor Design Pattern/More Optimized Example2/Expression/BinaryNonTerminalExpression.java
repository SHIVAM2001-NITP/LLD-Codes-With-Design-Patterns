package Expression;

import Context.Context;

public class BinaryNonTerminalExpression implements AbstractExpression{
    AbstractExpression leftExpression;
    AbstractExpression rightExpression;
    char operator;
    public BinaryNonTerminalExpression(AbstractExpression leftExpression, AbstractExpression rightExpression,char Operator) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator=Operator;
    }
    @Override
    public  int interpret(Context context){
        switch (operator){
            case '+':
                return  leftExpression.interpret(context) + rightExpression.interpret(context);
            case '*':
                return leftExpression.interpret(context) * rightExpression.interpret(context);
            default:
                return 0;
        }

    }
}
