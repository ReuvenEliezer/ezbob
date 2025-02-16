package ezbob.configuration;

import ezbob.entities.OperatorTypeEnum;
import ezbob.services.CalculatorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Configuration
public class CalculationHandlerConfig {

    @Bean
    @Primary
    public Map<OperatorTypeEnum, CalculatorHandler> calculationHandlers(Map<String, CalculatorHandler> beansMap) {
        Map<OperatorTypeEnum, CalculatorHandler> retMap = new HashMap<>();
        for (CalculatorHandler bean : beansMap.values()) {
            retMap.put(bean.getOperatorType(), bean);
        }
        return retMap;
    }

    @Bean
    public Map<OperatorTypeEnum, CalculatorHandler> calculationHandlers2(Map<String, CalculatorHandler> beansMap) {
        Map<OperatorTypeEnum, CalculatorHandler> handlerMap = new HashMap<>(beansMap.size());
        Map<Class<? extends CalculatorHandler>, OperatorTypeEnum> operatorTypeEnumMap = Arrays.stream(OperatorTypeEnum.values())
                .collect(Collectors.toMap(OperatorTypeEnum::getCalculatorService, Function.identity()));

        for (Map.Entry<String, CalculatorHandler> handlerEntry : beansMap.entrySet()) {
            CalculatorHandler handler = handlerEntry.getValue();
            OperatorTypeEnum channel = operatorTypeEnumMap.get(handler.getClass());
            handlerMap.put(channel, handlerEntry.getValue());
        }
        return handlerMap;
    }
}
