package ezbob.services;

import ezbob.entities.OperatorTypeEnum;
import ezbob.services.CalculatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class CalculationHandlerConfig {

    @Bean
    public HashMap<OperatorTypeEnum, CalculatorService> CalculationHandlers(Map<String, CalculatorService> beansMap) {
        HashMap<OperatorTypeEnum, CalculatorService> retMap = new HashMap<>();
        for (CalculatorService bean : beansMap.values()) {
            retMap.put(bean.getOperatorType(), bean);
        }
        return retMap;
    }
}
