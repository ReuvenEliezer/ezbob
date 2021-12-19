package ezbob.services;

import ezbob.entities.OperatorTypeEnum;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class CalculatorMinusServiceImpl implements CalculatorService {

    @Override
    public int calc(int[] argsToCalc) {
        return IntStream.of(argsToCalc).reduce((a, b) -> (a - b)).getAsInt();
    }

    @Override
    public OperatorTypeEnum getOperatorType() {
        return OperatorTypeEnum.MINUS;
    }
}
