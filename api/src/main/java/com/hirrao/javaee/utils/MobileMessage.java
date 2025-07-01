package com.hirrao.javaee.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobileMessage {
    private static final Logger logger = LoggerFactory.getLogger(MobileMessage.class);

    public static Client createClient() throws Exception {
        Config config = new Config().setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID")).setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    public static int sendMessage(String phoneNumber, String code, String time) {
        try {
            Client client = MobileMessage.createClient();
            SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest().setSignName(System.getenv("SIGN_NAME")).setTemplateCode(System.getenv("TEMPLATE_CODE")).setPhoneNumbers(phoneNumber).setTemplateParam(String.format("{\"code\":\"%s\",\"time\":\"%s\"}", code, time));
            RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            var response = client.sendSmsWithOptions(sendSmsRequest, runtime);
            switch (response.getBody().getCode()) {
                case "MOBILE_NOT_ON_SERVICE", "isv.MOBILE_NUMBER_ILLEGAL":
                    logger.error("{}", response.body.message);
                    return -2;
                case "OK":
                    logger.info("{}", response.body.message);
                    break;
                default:
                    logger.error("{}", response.body.message);
                    return -1;
            }
        } catch (Exception error) {
            logger.error("{}", error.getMessage());
            return -1;
        }
        return 0;
    }

    public static String generateCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
    }
}