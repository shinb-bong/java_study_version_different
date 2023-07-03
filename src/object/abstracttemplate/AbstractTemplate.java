package object.abstracttemplate;

public abstract class AbstractTemplate {

    public void execute(){
        System.out.println("AbstractTemplate.execute");
        long startTime = System.currentTimeMillis();

        // biz
        call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("resultTime = " + resultTime);
    }

    protected abstract void call();
}
