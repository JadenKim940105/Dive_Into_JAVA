package designpattern.factory_pattern.abstract_factory_pattern;

public class ComputerFactory {

    public static Computer getComputer(ComputerAbstractFactory factory){
        return  factory.createComputer();
    }
}
