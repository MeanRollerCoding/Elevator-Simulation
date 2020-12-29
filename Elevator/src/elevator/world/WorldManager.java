package elevator.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import elevator.entity.Entity;
import elevator.entity.IEntity;
import elevator.entity.TestEntity;
import elevator.entity.animation.CycleType;
import elevator.entity.animation.ISpriteAnimation;
import elevator.entity.animation.SpriteAnimation;
import elevator.graphics.sprites.SpriteSheets;
import elevator.system.ElevatorSimulationSystem;

public class WorldManager implements IWorldManager {

	private List<IEntity> entities;
	
	@Override
	public void init() {
		this.entities = new ArrayList<IEntity>();
		this.createBaseEntities();
	}

	@Override
	public void enable() {

	}

	@Override
	public void update() {
		for(IEntity entity : this.entities) {
			entity.update();
		}
	}

	@Override
	public void render() {
		for(IEntity entity : this.entities) {
			entity.render();
		}
	}

	@Override
	public void renderGraphics(Graphics g) {
		
	}
	
	private void createBaseEntities() {
		ISpriteAnimation testAnim = new SpriteAnimation(
				3,
				CycleType.BounceBack,
				ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.TestSheet, 0, 0, 40),
				ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.TestSheet, 1, 0, 40),
				ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.TestSheet, 0, 1, 40),
				ElevatorSimulationSystem.getInstance().getGraphicsManager().loadSprite(SpriteSheets.TestSheet, 1, 1, 40));
		this.entities.add(new TestEntity(testAnim));
	}
	

}
