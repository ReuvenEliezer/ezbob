package ezbob.controllers;

import ezbob.entities.ArgsCalculation;
import ezbob.entities.OperatorTypeEnum;

import ezbob.services.CalculatorHandler;
import ezbob.utils.WsAddressConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping(WsAddressConstants.calculateLogicUrl)
public class CalculationController {

    private final static Logger logger = LogManager.getLogger(CalculationController.class);

    private final Map<OperatorTypeEnum, DoCalculate> calcOperatorEnumMap = new HashMap<>();

    @Autowired
    private Map<OperatorTypeEnum, CalculatorHandler> operatorTypeEnumCalculatorServiceMap;

    @Autowired
    @Qualifier("calculationHandlers2")
    private Map<OperatorTypeEnum, CalculatorHandler> calculationHandlers2;

    //    @PostConstruct
//    public void init() {
    CalculationController() {
        calcOperatorEnumMap.put(OperatorTypeEnum.PLUS, this::calcPlus);
        calcOperatorEnumMap.put(OperatorTypeEnum.MINUS, this::calcMinus);
    }

    private int calcMinus(int[] argsToCalc) {
        return IntStream.of(argsToCalc).reduce((a, b) -> (a - b)).getAsInt();
    }

    private int calcPlus(int[] argsToCalc) {
        return IntStream.of(argsToCalc).sum();
    }

    @FunctionalInterface
    public interface DoCalculate {
        int calc(int[] intsToCalc);
    }

    @RequestMapping(method = RequestMethod.POST, value = "calculateArgsC")
    public Integer calculateC(@RequestBody ArgsCalculation argsCalculation) {
        CalculatorHandler doCalculate = calculationHandlers2.get(argsCalculation.getOperatorTypeEnum());
        return doCalculate.calc(argsCalculation.getNumbers());
    }

    @RequestMapping(method = RequestMethod.POST, value = "calculateArgsB")
    public Integer calculateB(@RequestBody ArgsCalculation argsCalculation) {
        DoCalculate doCalculate = calcOperatorEnumMap.get(argsCalculation.getOperatorTypeEnum());
        return doCalculate.calc(argsCalculation.getNumbers());
    }

    @RequestMapping(method = RequestMethod.POST, value = "calculateArgsA")
    public Integer calculateA(@RequestBody ArgsCalculation argsCalculation) {
        String methodName = "calculate:";
        CalculatorHandler calculatorHandler = operatorTypeEnumCalculatorServiceMap.get(argsCalculation.getOperatorTypeEnum());
        if (calculatorHandler == null) {
            String message = String.format(methodName, "UnsupportedOperation for OperatorTypeEnum %s", argsCalculation.getOperatorTypeEnum());
            logger.error(message);
            throw new UnsupportedOperationException(message);
        }
        int result = calculatorHandler.calc(argsCalculation.getNumbers());
        logger.debug("{} The result is {}", methodName, result);
        return result;
    }

}
