package temp.demo;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;

@InjectTest @SessionScoped
public class InterfaceImplement implements Interface, Serializable {
}
