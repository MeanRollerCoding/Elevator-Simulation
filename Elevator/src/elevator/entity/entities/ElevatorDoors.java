package elevator.entity.entities;

public class ElevatorDoors extends ElevatorCell {
	
	private int floorNumber;
	private boolean areOpen;

	public ElevatorDoors(int floorNumber, int x, int y) {
		super(ClosedSprite);
		this.floorNumber = floorNumber;
		this.x = x;
		this.y = y;
		this.areOpen = false;
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

}
