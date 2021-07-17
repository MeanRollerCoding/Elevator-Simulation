package elevator.entity.entities.elevator;

import elevator.graphics.sprites.Colors;
import elevator.graphics.sprites.IFillSprite;
import elevator.graphics.sprites.ISprite;
import elevator.graphics.sprites.SpriteSheets;
import elevator.system.ElevatorSimulationSystem;

public class ElevatorDoors extends ElevatorCell {

	protected static final int ButtonSize = 25;
	
	protected static final IFillSprite UpButton = ElevatorSimulationSystem.getInstance().getGraphicsManager().loadFillSprite(SpriteSheets.Buttons, 0, 0, ButtonSize, Colors.White, Colors.ButtonOff, Colors.Red);
	protected static final IFillSprite DownButton = ElevatorSimulationSystem.getInstance().getGraphicsManager().loadFillSprite(SpriteSheets.Buttons, 0, 1, ButtonSize, Colors.White, Colors.ButtonOff, Colors.ButtonOn);
	
	private int floorNumber;
	private boolean areOpen, upRequest, downRequest;

	public ElevatorDoors(int floorNumber, int x, int y) {
		super(ClosedSprite);
		this.floorNumber = floorNumber;
		this.x = x;
		this.y = y;
		this.areOpen = false;
		this.upRequest = false;
		this.downRequest = false;
	}
	
	public void pushButton(DirectionRequestType type) {
		if(type == DirectionRequestType.Up) {
			this.upRequest = true;
		}
		else if(type == DirectionRequestType.Down) {
			this.downRequest = true;
		}
	}
	
	public void clearButton(DirectionRequestType type) {
		if(type == DirectionRequestType.Up) {
			this.upRequest = false;
		}
		else if(type == DirectionRequestType.Down) {
			this.downRequest = false;
		}
	}
	
	@Override
	public void render() {
		super.render();
		ElevatorSimulationSystem.getInstance().getGraphicsManager().renderFillSprite(this.x + 100, this.y + 30, UpButton, this.upRequest ? 1 : 0);
		ElevatorSimulationSystem.getInstance().getGraphicsManager().renderFillSprite(this.x + 100, this.y + 55, DownButton, this.downRequest ? 1 : 0);
	}
	
	@Override
	public int getFloorNumber() {
		return this.floorNumber;
	}
	
	public void open() {
		this.sprite = OpenSprite;
		this.areOpen = true;
	}
	
	public void close() {
		this.sprite = ClosedSprite;
		this.areOpen = false;
	}
	
	public boolean areOpen() {
		return this.areOpen;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getSize() {
		return Size;
	}
	
	public int getButtonSize() {
		return ButtonSize;
	}

}
