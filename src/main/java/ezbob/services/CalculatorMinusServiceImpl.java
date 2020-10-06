package ezbob.services;

import ezbob.entities.OperatorTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class CalculatorMinusServiceImpl implements CalculatorService {

    @Override
    public int calc(int[] argsToCalc) {
        int result = 0;
        for (int item : argsToCalc) {
            result -= item;
        }
        return result;
    }

    @Override
    public OperatorTypeEnum getOperatorType() {
        return OperatorTypeEnum.MINUS;
    }
}
