package mainspark.alex.stilwell.example.one;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient;
import com.amazonaws.services.secretsmanager.model.AWSSecretsManagerException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleOne {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleOne.class);

    public static void main(String[] args) throws JsonProcessingException {
        var secretName = "/iam/credentials/s3/mainspark-iam-user-s3";
        var secretsClient = AWSSecretsManagerClient.builder()
                .withClientConfiguration(new ClientConfiguration())
                .withRegion(Regions.EU_WEST_2)
                .build();

        var secretString = getValue(secretsClient, secretName);
        AccessKey secret = new ObjectMapper()
                .readerFor(AccessKey.class)
                .readValue(secretString);
        var s3Client = AmazonS3Client.builder()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(secret.getAccessKey(), secret.getSecretKey())))
                .withRegion(Regions.EU_WEST_2)
                .build();

        s3Client.putObject("mainspark-bucket", "exampleOne/fileOne.txt", "Content");
        var getObjectResult = s3Client.getObject("mainspark-bucket", "exampleOne/fileOne.txt");
        LOGGER.info("Get object result was {}", getObjectResult);

    }

    public static String getValue(AWSSecretsManager secretsManager, String secretName) {
        try {
            var secretValueRequest = new GetSecretValueRequest()
                    .withSecretId(secretName);

            var valueResponse = secretsManager.getSecretValue(secretValueRequest);
            var secret = valueResponse.getSecretString();
            LOGGER.info("Retrieved Secret was {}", secret);
            return secret;

        } catch (AWSSecretsManagerException e) {
            LOGGER.error("Error when retrieving AWS Secret", e);
            System.exit(1);
            return null;
        }
    }
}