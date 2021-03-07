package elevator.entity.entities;

import java.util.ArrayList;
import java.util.List;

import elevator.entity.Entity;

public class Elevator extends Entity {

	private static final int ElevatorCellMoveTime = 60;
	private static final int ElevatorFloorWaitTime = 180;
	
	private int x, y, numberOfFloors;
	private int currentFloor, currentCell, targetFloor;
	private int moveCount, doorCount;
	private ElevatorAlgorithm algorithm;
	private List<ElevatorCell> cells;
	private ElevatorMarker marker;
	private boolean[] floorRequests;
	private boolean isMoving, isMovingUp, isMovingDown, doorsOpen;
	
	public Elevator(ElevatorAlgorithm algorithm, int numberOfFloors, int x, int y) {
		super(null);
		this.cells = new ArrayList<ElevatorCell>();
		this.floorRequests = new boolean[numberOfFloors];
		this.x = x;
		this.y = y;
		this.numberOfFloors = numberOfFloors;
		this.algorithm = algorithm;
		this.currentCell = 0;
		this.currentFloor = 1;
		this.createElevator();
	}
	
	@Override
	public void render() {
		for(ElevatorCell cell : this.cells) {
			cell.render();
		}
		
		this.marker.render();
	}
	
	@Override
	public void update() {
		switch(this.algorithm) {
		case Basic:
			this.updateBasic();
			break;
		}
	}
	
	public void addFloorRequest(int floor) {
		if(floor >= 1 && floor <= this.numberOfFloors) {
			this.floorRequests[floor - 1] = true;
		}
	}
	
	public int getNumberOfFloors() {
		return this.numberOfFloors;
	}
	
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	private void updateBasic() {
		if(this.isMoving) {
			this.moveToFloor();
		}
		else if(this.doorsOpen) {
			this.handleOpenDoors();
		}
		else if(this.floorRequests[this.currentFloor - 1]) {
			this.doorCount = 0;
			this.doorsOpen = true;
		}
		else {
			for(int i = 0; i < this.numberOfFloors; i++) {
				if(this.floorRequests[i]) {
					this.targetFloor = i + 1;
					this.isMoving = true;
					this.moveCount = 0;
				}
			}
		}
	}
	
	private void handleOpenDoors() {
		ElevatorDoors doors = (ElevatorDoors) (this.cells.get(this.currentCell));
		if(!doors.areOpen()) {
			doors.open();
		}
		this.doorCount++;
		if(this.doorCount >= ElevatorFloorWaitTime) {
			this.doorCount = 0;
			doors.close();
			this.doorsOpen = false;
			this.floorRequests[this.currentFloor - 1] = false;
		}
	}
	
	private void moveToFloor() {
		if(this.currentFloor == this.targetFloor) {
			this.isMoving = false;
			this.isMovingUp = false;
			this.isMovingDown = false;
			this.updateMarker();
		}
		else {
			if(!this.isMovingUp && !this.isMovingDown) {
				this.isMovingUp = this.currentFloor < this.targetFloor;
				this.isMovingDown = this.currentFloor > this.targetFloor;
			}
			this.moveCount++;
			this.updateMarker();
			if(this.moveCount >= ElevatorCellMoveTime) {
				this.moveCount = 0;
				this.currentCell += this.isMovingDown ? -1 : 1;
				int cellFloorNumber = this.cells.get(this.currentCell).getFloorNumber();
				if(cellFloorNumber != -1) {
					this.currentFloor = cellFloorNumber;
				}
			}
		}
	}
	
	private void updateMarker() {
		int y = this.y - this.currentCell * ElevatorCell.Size;
		y += (int) (this.moveCount * 1.0 / ElevatorCellMoveTime * ElevatorCell.Size * (this.isMovingUp ? -1 : 1));
		this.marker.setPosition(this.x, y);
	}
	
	private void createElevator() {
		for(int i = 0; i < this.numberOfFloors; i++) {
			this.cells.add(new ElevatorDoors(i + 1, this.x, this.y - i * ElevatorCell.Size * 2));
			this.cells.add(new ElevatorShaft(this.x,  this.y - i * ElevatorCell.Size * 2 - ElevatorCell.Size));
			if(i == 0) {
				this.marker = new ElevatorMarker(this.x, this.y);
			}
			this.floorRequests[i] = false;
		}
	}

}
