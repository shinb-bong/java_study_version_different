package object.abstractsender;

public class GmailSender extends ContentSender{
    String content;

    public GmailSender(String title, String nm, String content) {
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
    public void gmailAlert(String recipient){
        System.out.println("메일을 받는사람은 = "+ recipient);
    }
}
