package ezbob.entities;


import ezbob.services.CalculatorMinusHandlerImpl;
import ezbob.services.CalculatorPlusHandlerImpl;
import ezbob.services.CalculatorHandler;

public enum OperatorTypeEnum {
    PLUS(CalculatorPlusHandlerImpl.class),
    MINUS(CalculatorMinusHandlerImpl.class),
    ;

    private final Class<? extends CalculatorHandler> calculatorService;

    OperatorTypeEnum(Class<? extends CalculatorHandler> calculatorService) {
        this.calculatorService = calculatorService;
    }

    public Class<? extends CalculatorHandler> getCalculatorService() {
        return calculatorService;
    }
}
