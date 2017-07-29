import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LD39 {

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	Thread graphicsThread;
	Thread physicsThread;
	static final long PHYSICS_DELAY = 10;
	static final long GRAPHICS_DELAY = 16;
	Lane[] lanes = new Lane[13];
	
	public static void main(String[] args) {
		LD39 theLD = new LD39();
		theLD.start();
	}
	
	public void start(){
		panel.setPreferredSize(new Dimension(1024, 768));
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		panel.setOpaque(true);
		
		for(int i = 0; i < lanes.length; i++){
			lanes[i] = new Lane(i < 3, lanes.length - i - 1);
			lanes[i].height = 768;
			lanes[i].width = 1024 / lanes.length;
			lanes[i].x = lanes[i].width * i;
		}
		
		graphicsThread = new Thread(){
			public void run(){
				while(true){
					long start = System.currentTimeMillis();
					try{
						graphics();
					}catch(Exception e){
						e.printStackTrace();
					}
					long delay = System.currentTimeMillis() - start - GRAPHICS_DELAY;
					if(delay > 0){
						try{
							Thread.sleep(delay);
						}catch(Exception e){}
					}
				}
			}
		};
		physicsThread = new Thread(){
			public void run(){
				while(true){
					long start = System.currentTimeMillis();
					try{
						physics();
					}catch(Exception e){
						e.printStackTrace();
					}
					long delay = System.currentTimeMillis() - start - PHYSICS_DELAY;
					if(delay > 0){
						try{
							Thread.sleep(delay);
						}catch(Exception e){}
					}
				}
			}
		};
		graphicsThread.start();
		physicsThread.start();
	}
	
	public void graphics(){
		Graphics2D g = (Graphics2D) panel.getGraphics();
		for(int i = 0; i < lanes.length; i++){
			lanes[i].draw(g);
		}
	}
	
	public void physics(){
		for(int i = 0; i < lanes.length; i++){
			lanes[i].tick();
		}
	}

}
