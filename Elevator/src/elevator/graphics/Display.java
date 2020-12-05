package elevator.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import elevator.system.ElevatorSimulationSystem;

public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	
	private Thread thread;
	private JFrame frame;
	private String title = "Elevator";
	private boolean running = false;
	
	public Display() {
		this.frame = new JFrame();
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
	}
	
	public void init() {
		this.frame.setTitle(title);
		this.frame.add(this);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(true);
		this.frame.setVisible(true);
	}
	
	public synchronized void start() {
		this.running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}
	
	public synchronized void stop() {
		this.running = false;
		try {
			this.thread.join();
		} catch(InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		int frames = 0;
		
		while(this.running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				this.update();
				this.render();
				delta--;
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				this.frame.setTitle(this.title + " | " + frames + " fps");
				frames = 0;
				timer += 1000;
			}
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		ElevatorSimulationSystem.getInstance().render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void update() {
		WIDTH = this.getWidth();
		HEIGHT = this.getHeight();
		
		ElevatorSimulationSystem.getInstance().update();
	}

}
