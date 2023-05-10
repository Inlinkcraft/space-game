package main;

import org.lwjgl.glfw.GLFW;

import engine.io.Input;
import engine.io.Window;

public class Main implements Runnable {

	public final int WIDTH = 1280, HEIGHT = 760;
	
	public Thread game;
	public Window window;
	
	public void start() {
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		//System.out.println("Initializing Game!");
		
		window = new Window(WIDTH, HEIGHT, "Game");
		window.create();
		
	}
	
	public void run() {
		
		init();
		
		while (!window.shouldClose()) {
			update();
			render();
			
			// close window if escape is hit
			if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) return;
			
		}
		
		window.destroy();
		
	}
	
	private void update() {
		//System.out.println("Updating Game!");
		
		window.update();
		
		// test mouse
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("Mouse is at: ("+ Input.getMouseX() + ", " + Input.getMouseY() +")");
		
	}
	
	private void render() {
		//System.out.println("Rendering Game!");
		
		window.swapBuffers();
		
	}
	
	public static void main(String[] args) {
		new Main().start();	
	}

}
