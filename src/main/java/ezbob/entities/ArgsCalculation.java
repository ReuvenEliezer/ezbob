package ezbob.entities;

import java.io.Serializable;

public class ArgsCalculation implements Serializable {

    private OperatorTypeEnum operatorTypeEnum;
    private int[] numbers = new int[]{};

    public ArgsCalculation() {
    }

    public ArgsCalculation(int[] numbers, OperatorTypeEnum operatorTypeEnum) {
        this.numbers = numbers;
        this.operatorTypeEnum = operatorTypeEnum;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public OperatorTypeEnum getOperatorTypeEnum() {
        return this.operatorTypeEnum;
    }



}
