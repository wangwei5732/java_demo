package designMode.factory;

/**
 * 发送邮件
 */
public class SendEmailImpl implements SendMsg {
    @Override
    public boolean send(String msg) {
        System.out.println("send email");
        return true;
    }
}
