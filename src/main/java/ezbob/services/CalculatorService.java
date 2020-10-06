package ezbob.services;

import ezbob.entities.OperatorTypeEnum;

public interface CalculatorService {

    int calc(int[] argsToCalc);

    OperatorTypeEnum getOperatorType();

}
