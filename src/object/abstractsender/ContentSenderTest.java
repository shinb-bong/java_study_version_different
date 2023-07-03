package object.abstractsender;

public class ContentSenderTest {

    public static void main(String[] args) {
        KakaoSender kakaoSender = new KakaoSender("안녕","아이유","정류장");
        kakaoSender.kakaoAlert();
        kakaoSender.sendMsg("유인나");

        System.out.println("=========");

        GmailSender gmailSender = new GmailSender("바이", "징크스", "필트오버");
        String recipient = "제이스";
        gmailSender.sendMsg(recipient);
        gmailSender.gmailAlert(recipient);
    }
}
