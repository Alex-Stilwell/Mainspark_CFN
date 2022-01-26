package mainspark.alex.stilwell.lambda.one;

import com.fasterxml.jackson.annotation.JsonSetter;

public class AccessKey {
    private String accessKey;
    private String secretKey;

    @JsonSetter("ACCESS_KEY")
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @JsonSetter("SECRET_KEY")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
