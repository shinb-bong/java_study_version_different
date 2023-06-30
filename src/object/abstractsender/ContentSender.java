package object;

public abstract class ContentSender {
    String title;
    String nm;

    public ContentSender() {
    }

    public ContentSender(String title, String nm) {
        this.title = title;
        this.nm = nm;
    }

    // 추상메서드  --> 상속을 통해서 반드시 재정의 되어야지만 비로소 인스턴스를 생성 가능
    public abstract void sendMsg(String content);
    
}
