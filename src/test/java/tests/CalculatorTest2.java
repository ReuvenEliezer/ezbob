package tests;

import ezbob.app.EzBobApp;
import ezbob.entities.ArgsCalculation;
import ezbob.entities.OperatorTypeEnum;
import ezbob.utils.WsAddressConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = EzBobApp.class)
//@RunWith(JUnitParamsRunner.class)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CalculatorTest2 {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CALCULATE_ARGS = "calculateArgsA";

//    @BeforeClass
//    public static void beforeClass() {
//        EzBobApp.main(new String[]{});
//        restTemplate = new RestTemplate();
//    }

    @Test
    public void plusTest() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ArgsCalculation argsCalculation = new ArgsCalculation(arr, OperatorTypeEnum.PLUS);
        Integer result = restTemplate.postForObject(WsAddressConstants.calculateFullUrl + CALCULATE_ARGS, argsCalculation, Integer.class);
        Assert.assertEquals("calculation result not as expected", IntStream.of(arr).sum(), result.intValue());
    }

}
