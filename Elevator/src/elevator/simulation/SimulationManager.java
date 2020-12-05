package elevator.simulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SimulationManager implements ISimulationManager {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Verdana", 1, 40));
		g.drawString("Hello world!", 100, 100);
	}

}
