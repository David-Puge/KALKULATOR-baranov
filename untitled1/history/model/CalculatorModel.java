package model;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

public class CalculatorModel {

    // Оператор для // (целочисленное деление)
    private final Operator floorDiv = new Operator("//", 2, true, Operator.PRECEDENCE_DIVISION) {
        @Override
        public double apply(double[] args) {
            return Math.floor(args[0] / args[1]);
        }
    };

    public double evaluate(String expression) {
        expression = expression.replace("**", "^");

        Expression exp = new ExpressionBuilder(expression)
                .operator(floorDiv)
                .build();

        return exp.evaluate();
    }
}