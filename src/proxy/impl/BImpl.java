package proxy.impl;

import proxy.BInterface;

public class BImpl implements BInterface {
    @Override
    public String call() {
        System.out.println("B 호출");
        return "b";
    }
}
