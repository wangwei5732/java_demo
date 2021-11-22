package designMode.factory;

/**
 * 发送消息工厂类-static方式
 */
public class SendFactory {
    public static SendMsg sendSms() {
        return new SendSmsImpl();
    }

    public static SendMsg sendEmail() {
        return new SendEmailImpl();
    }

    public static void main(String[] args) {
        SendMsg sendMsg = SendFactory.sendEmail();
        sendMsg.send("haha");
    }
}
