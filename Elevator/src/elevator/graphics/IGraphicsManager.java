package elevator.graphics;

import elevator.graphics.sprites.ISprite;
import elevator.graphics.sprites.SpriteSheets;
import elevator.system.subsystem.IRenderableSubsystem;

public interface IGraphicsManager extends IRenderableSubsystem {

	ISprite loadSprite(String path);
	
	ISprite loadSprite(SpriteSheets spriteSheet, int x, int y, int size);
	
	void renderSprite(int xp, int yp, ISprite sprite);
	
	int getScreenPixel(int x, int y);

}
