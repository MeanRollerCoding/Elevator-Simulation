package elevator.system.instance;

import java.awt.Graphics;

import elevator.simulation.ISimulationManager;

public interface IElevatorSimulationInstance {
	
	void create();
	
	void instantiate();
	
	void enable();
	
	void update();
	
	void render(Graphics g);
	
	ISimulationManager getSimulationManager();

}
