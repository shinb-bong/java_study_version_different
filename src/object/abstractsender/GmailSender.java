package object.abstractsender;

public class KakaoSender extends ContentSender{
    String content;

    public KakaoSender(String title, String nm, String content) {
        super(title, nm);
        this.content = content;
    }

    @Override
    public void sendMsg(String recipient) {
        System.out.println("제목=" + this.title);
        System.out.println("이름=" + this.nm);
        System.out.println("내용=" + this.content);
        System.out.println("받는 사람=" + recipient);
    }

    // 고유메소드
    public void kakaoAlert(){
        System.out.println("카톡! 카톡!");
    }
}
