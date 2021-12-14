package ezbob.controllers;

import ezbob.entities.ArgsCalculation;
import ezbob.entities.OperatorTypeEnum;

import ezbob.services.CalculatorService;
import ezbob.utils.WsAddressConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping(WsAddressConstants.calculateLogicUrl)
public class CalculationController {

    private final static Logger logger =  LogManager.getLogger(CalculationController.class);

    private Map<OperatorTypeEnum, DoCalculate> calcOperatorEnumMap = new HashMap<>();

    //    @PostConstruct
//    public void init() {
    CalculationController() {
        calcOperatorEnumMap.put(OperatorTypeEnum.PLUS, this::calcPlus);
        calcOperatorEnumMap.put(OperatorTypeEnum.MINUS, this::calcMinus);
    }

    private int calcMinus(int[] argsToCalc) {
        int result = 0;
        for (int item : argsToCalc) {
            result -= item;
        }
        return result;
    }

    private int calcPlus(int[] argsToCalc) {
        return IntStream.of(argsToCalc).sum();
    }

    @FunctionalInterface
    public interface DoCalculate {
        int calc(int[] intsToCalc);
    }

    @RequestMapping(method = RequestMethod.POST, value = "calculateArgsB")
    public Integer calculateB(@RequestBody ArgsCalculation argsCalculation) {
        DoCalculate doCalculate = calcOperatorEnumMap.get(argsCalculation.getOperatorTypeEnum());
        return doCalculate.calc(argsCalculation.getNumbers());
    }

    @Autowired
    private HashMap<OperatorTypeEnum, CalculatorService> operatorTypeEnumCalculatorServiceMap;

    @RequestMapping(method = RequestMethod.POST, value = "calculateArgsA")
    public Integer calculateA(@RequestBody ArgsCalculation argsCalculation) {
        String methodName = "calculate:";
        CalculatorService calculatorService = operatorTypeEnumCalculatorServiceMap.get(argsCalculation.getOperatorTypeEnum());
        if (calculatorService == null) {
            String message = String.format(methodName, "UnsupportedOperation for OperatorTypeEnum %s", argsCalculation.getOperatorTypeEnum());
            logger.error(message);
            throw new UnsupportedOperationException(message);
        }
        int result = calculatorService.calc(argsCalculation.getNumbers());
        logger.debug(String.format("%s The result is %s", methodName, result));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "calculateArgs")
    public Integer calculateArgs(@RequestBody ArgsCalculation argsCalculation) {
        String methodName = "calculateArgs:";
        CalculatorService calculatorService = operatorTypeEnumCalculatorServiceMap.get(argsCalculation.getOperatorTypeEnum());
        if (calculatorService == null) {
            String message = String.format(methodName, "UnsupportedOperation for OperatorTypeEnum %s", argsCalculation.getOperatorTypeEnum());
            logger.error(message);
            throw new UnsupportedOperationException(message);
        }
        int result = calculatorService.calc(argsCalculation.getNumbers());
        logger.debug(String.format("%s The result is %s", methodName, result));
        return result;
    }


}
