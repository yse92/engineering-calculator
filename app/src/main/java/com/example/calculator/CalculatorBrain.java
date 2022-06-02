package com.example.calculator;

public class CalculatorBrain {

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;

    //типы операторов
    public static final String ADD = "+";
    public static final String SUBSTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "C";
    public static final String CLEARMEMORY = "MC";
    public static final String ADDTOMEMORY = "M+";
    public static final String SUBSTRACTFROMMEMORY = "M-";
    public static final String RECALLMEMORY = "MR";

    public static final String SQUAREROOT = "√";
    public static final String SQUARED = "x²";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "+/-";

    public static final String SIN = "sin";
    public static final String COS = "cos";
    public static final String TAN = "tan";

    //public static final String EQUALS = "="


    public CalculatorBrain() {
        //init
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    protected double performOperation(String operator) {
        switch(operator) {
            case CLEAR:
                mOperand = 0;
                mWaitingOperator = "";
                mWaitingOperand = 0;
                //mCalculatorMemory = 0;
                break;
            case CLEARMEMORY:
                mCalculatorMemory = 0;
                break;
            case ADDTOMEMORY:
                mCalculatorMemory += mOperand;
                break;
            case SUBSTRACTFROMMEMORY:
                mCalculatorMemory -= mOperand;
                break;
            case RECALLMEMORY:
                mOperand = mCalculatorMemory;
                break;
            case SQUAREROOT:
                mOperand = Math.sqrt(mOperand);
                break;
            case SQUARED:
                mOperand = Math.pow(mOperand, 2);
                break;
            case INVERT:
                if(mOperand != 0){
                    mOperand = 1 / mOperand;
                }
                break;
            case TOGGLESIGN:
                mOperand = -mOperand;
                break;
            case SIN:
                mOperand = Math.sin(Math.toRadians(mOperand));
                break;
            case COS:
                mOperand = Math.cos(Math.toRadians(mOperand));
                break;
            case TAN:
                mOperand = Math.tan(Math.toRadians(mOperand));
                break;
            default:
                performWaitingOperation();
                mWaitingOperand = mOperand;
                mWaitingOperator = operator;
                break;
        }

        return mOperand;
    }

    private void performWaitingOperation() {
        switch (mWaitingOperator) {
            case ADD:
                mOperand += mWaitingOperand;
                break;
            case SUBSTRACT:
                mOperand = mWaitingOperand - mOperand;
                break;
            case MULTIPLY:
                mOperand *= mWaitingOperand;
                break;
            case DIVIDE:
                if(mOperand != 0)
                mOperand = mWaitingOperand / mOperand;
                break;
        }
    }

    public double getMemory() {
        return mCalculatorMemory;
    }

    public void setMemory(double memory) {
        mCalculatorMemory = memory;
    }
}
