package designpattern.abstractclass_templatepattern.ex1;

public class Desktop extends Computer {
    @Override
    public void display() {
        System.out.println("Desktop Display");
    }

    @Override
    public void typing() {
        System.out.println("Desktop typing");
    }
}
