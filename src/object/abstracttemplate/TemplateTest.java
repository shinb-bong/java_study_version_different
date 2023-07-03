package object.abstracttemplate;

public class TemplateTest {
    public static void main(String[] args) {
        AbstractTemplate logic1 = new SubClassLogic1();
        logic1.execute();
        AbstractTemplate logic2 = new SubClassLogic2();
        logic2.execute();
    }
}
