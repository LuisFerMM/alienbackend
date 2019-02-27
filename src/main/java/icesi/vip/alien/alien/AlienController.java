package icesi.vip.alien.alien;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Constraint;
import model.Model;
import model.Solution;
import model.Variable;
import solver.interior_point.BarrierMethod;

@RestController
public class AlienController {

	private static final String template = "Hello, %s! Welcome to ICESI's VIP program app.";
	private final AtomicLong counter = new AtomicLong();

	@CrossOrigin
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@CrossOrigin
	@RequestMapping("/test")
	public Solution.ExportSolution test() throws Exception{
		  Model m=new Model(Model.MAXIMIZE);
	        m.addVariable("X1", Variable.CONTINUOUS, 2);
	        m.addVariable("X2", Variable.CONTINUOUS, -1);
	        m.addVariable("X3", Variable.CONTINUOUS, 2);
	        m.addConstraint(new double[]{2,1,0}, Constraint.LESS_OR_EQUAL, 10, "");
	        m.addConstraint(new double[]{1,2,-2}, Constraint.LESS_OR_EQUAL, 20, "");
	        m.addConstraint(new double[]{0,1,2}, Constraint.LESS_OR_EQUAL, 5, "");
	        BarrierMethod bm=new BarrierMethod();
	        return bm.solve(m).exportFormat();
		
	}

}