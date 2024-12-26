package ezbob.services;

import ezbob.entities.OperatorTypeEnum;

public interface CalculatorHandler {

    int calc(int[] argsToCalc);

    OperatorTypeEnum getOperatorType();

}
