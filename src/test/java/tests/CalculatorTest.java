package tests;

import ezbob.app.EzBobApp;
import ezbob.entities.ArgsCalculation;
import ezbob.entities.OperatorTypeEnum;
import ezbob.utils.WsAddressConstants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = EzBobApp.class)
//@RunWith(JUnitParamsRunner.class)
//@RunWith(SpringRunner.class)
public class CalculatorTest {

    private static RestTemplate restTemplate;

    private static final String CALCULATE_ARGS = "calculateArgsA";

    @BeforeClass
    public static void beforeClass() {
        EzBobApp.main(new String[]{});
        restTemplate = new RestTemplate();
    }

    @Test
    public void plusTest() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ArgsCalculation argsCalculation = new ArgsCalculation(arr, OperatorTypeEnum.PLUS);
        Integer result = restTemplate.postForObject(WsAddressConstants.calculateFullUrl + CALCULATE_ARGS, argsCalculation, Integer.class);
        Assert.assertEquals("calculation result not as expected", IntStream.of(arr).sum(), result.intValue());
    }

    @Test
    public void minusTest() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ArgsCalculation argsCalculation = new ArgsCalculation(arr, OperatorTypeEnum.MINUS);
        Integer result = restTemplate.postForObject(WsAddressConstants.calculateFullUrl + CALCULATE_ARGS, argsCalculation, Integer.class);
        Assert.assertEquals("calculation result not as expected", -13, result.intValue());
    }

    @Test
    public void unsupportedOperationTest() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ArgsCalculation argsCalculation = new ArgsCalculation(arr, null);
        try {
            restTemplate.postForObject(WsAddressConstants.calculateFullUrl + CALCULATE_ARGS, argsCalculation, Integer.class);
        } catch (RuntimeException e) {
            return;
        }
        Assert.fail("not valid operations not handling");
    }

    @Test(expected = RuntimeException.class)
    public void unsupportedOperation1Test() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ArgsCalculation argsCalculation = new ArgsCalculation(arr, null);
        restTemplate.postForObject(WsAddressConstants.calculateFullUrl + CALCULATE_ARGS, argsCalculation, Integer.class);
    }


}
