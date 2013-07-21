import com.punchline.javalib.entities.*;
import com.punchline.javalib.entities.components.*;

public class TesterConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Entity e = new Entity("test", "testGroup", "testType");
		e.addComponent(new TestComponent());
		TestComponent c = e.getComponent();
		System.out.println(c.getClass().getName());
		System.out.println(e.hasComponent(TestComponent.class));
		
		
		
	}

}
