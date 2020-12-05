package elevator.system.instance;

import java.awt.Graphics;

import elevator.simulation.ISimulationManager;
import elevator.simulation.SimulationManager;

public class ElevatorSimulationInstance implements IElevatorSimulationInstance {

	private ISimulationManager simulationManager;
	
	public ElevatorSimulationInstance() {
		this.simulationManager = null;
	}
	
	@Override
	public void create() {
		this.simulationManager = new SimulationManager();
	}

	@Override
	public void instantiate() {
		this.simulationManager.init();
	}

	@Override
	public void enable() {
		this.simulationManager.enable();
	}

	@Override
	public void update() {
		this.simulationManager.update();
	}

	@Override
	public void render(Graphics g) {
		this.simulationManager.render(g);
	}

	@Override
	public ISimulationManager getSimulationManager() {
		return this.simulationManager;
	}

}
