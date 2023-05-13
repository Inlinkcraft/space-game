package main;

import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.graphics.Vertex;

import engine.maths.Vector3f;

import org.lwjgl.glfw.GLFW;

import engine.io.Input;
import engine.io.Window;

public class Main implements Runnable {

	public final int WIDTH = 1280, HEIGHT = 760;
	
	public Thread game;
	public Window window;
	
	public Renderer renderer;
	
	public Shader shader;
	
	public Mesh mesh = new Mesh(new Vertex[] {
		
		new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f)),
		new Vertex(new Vector3f(0.5f, 0.5f, 0.0f)),
		new Vertex(new Vector3f(0.5f, -0.5f, 0.0f)),
		new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f)),
			
	}, new int[] {
		
		0, 1, 2,
		0, 3, 2		
		
	});
	
	public void start() {
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		//System.out.println("Initializing Game!");
		
		window = new Window(WIDTH, HEIGHT, "Game");
		
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		
		renderer = new Renderer(shader);
		
		window.setBackgroundColor(1f, 0f, 0f);
		
		window.create();
		mesh.create();
		shader.create();
		
	}
	
	public void run() {
		
		init();
		
		while (!window.shouldClose()) {
			update();
			render();
			
			if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
			
		}
		
		window.destroy();
		
	}
	
	private void update() {
		//System.out.println("Updating Game!");
		
		window.update();
		
		// test mouse
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("Mouse is at: ("+ Input.getMouseX() + ", " + Input.getMouseY() +"), scrolled at: (" + + Input.getScrollX() + ", " + Input.getScrollY() +")" );
		
	}
	
	private void render() {
		//System.out.println("Rendering Game!");
		
		renderer.renderMesh(mesh);
		
		window.swapBuffers();
		
	}
	
	public static void main(String[] args) {
		new Main().start();	
	}

}
