package ezbob.services;

import ezbob.entities.OperatorTypeEnum;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class CalculatorPlusHandlerImpl implements CalculatorHandler {

    @Override
    public int calc(int[] argsToCalc) {
        return IntStream.of(argsToCalc).sum();
    }

    @Override
    public OperatorTypeEnum getOperatorType() {
        return OperatorTypeEnum.PLUS;
    }
}
