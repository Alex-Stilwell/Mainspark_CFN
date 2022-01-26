package mainspark.alex.stilwell.lambda.one;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaFunctionExample implements RequestHandler<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(LambdaFunctionExample.class);

    @Override
    public String handleRequest(String input, Context context) {
        logger.info("Input: " + input);
        return "Hello World - " + input;
    }
}