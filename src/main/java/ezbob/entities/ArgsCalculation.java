package ezbob.entities;

import java.io.Serializable;
import java.util.Arrays;

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

    @Override
    public String toString() {
        return "ArgsCalculation{" +
                "operatorTypeEnum=" + operatorTypeEnum +
                ", numbers=" + Arrays.toString(numbers) +
                '}';
    }


}
