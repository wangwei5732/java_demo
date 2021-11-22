package designMode.factory;

/**
 * 发送短信
 */
public class SendSmsImpl implements SendMsg {
    @Override
    public boolean send(String msg) {
        System.out.println("send sms");
        return true;
    }
}
