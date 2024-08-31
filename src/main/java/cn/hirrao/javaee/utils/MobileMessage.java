package cn.hirrao.javaee.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;

public class MobileMessage {
    public static Client createClient() throws Exception {
        Config config = new Config().setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID")).setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    public static boolean sendMessage(String phoneNumber, String code) {
        try {
            Client client = MobileMessage.createClient();
            SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest().setSignName(System.getenv("SIGN_NAME")).setTemplateCode(System.getenv("TEMPLATE_CODE")).setPhoneNumbers(phoneNumber).setTemplateParam(String.format("{\"code\":\"%s\"}", code));
            RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            System.out.println(client.sendSmsWithOptions(sendSmsRequest, runtime).getBody().getMessage());
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }

    public static String generateCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
    }
}