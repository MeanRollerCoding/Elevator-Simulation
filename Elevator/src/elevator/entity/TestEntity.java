package elevator.entity;

import elevator.graphics.Display;
import elevator.graphics.sprites.ISprite;

public class TestEntity extends Entity {
	
	private int anim;

	public TestEntity(ISprite sprite) {
		super(sprite);
	}
	
	@Override
	public void update() {
		this.anim++;
		this.x = (int) (200 * Math.cos(this.anim / 100.0) + Display.WIDTH / 2);
		this.y = (int) (200 * Math.sin(this.anim / 100.0) + Display.HEIGHT / 2);
	}

}
