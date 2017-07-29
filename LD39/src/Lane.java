import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;


public class Lane extends Rectangle{
	boolean flipped = false;
	int index;
	double dy;
	double chance = 0;
	LinkedList<Car> cars = new LinkedList<Car>();
	boolean newCars = true;
	
	//right most is index 0.
	public Lane(boolean flipped, int index){
		//calculate dy and chance of spawning new car 
		this.flipped = flipped;
		this.index = index;
		dy = (double)index / 10.0;
		chance = (1 - index / 10.0) / 10.0;
		if(flipped){
			dy *= -1;			
		}
	}
	
	public void tick(){
		if(newCars){
			if(Math.random() < chance){
				//yay new car			
				
				if(flipped){
					
				}
			}
		}
		
		
		for(Car c : cars){
			c.y += dy;			
		}
		
		if(!cars.isEmpty()){
			cars.getFirst();//most recent car - use to set newCars var when clear
			cars.getLast();//oldest car -- use to determine if off the screen
		}
	}
	
	public void draw(Graphics2D g){
		Color old = g.getColor();
		g.setColor(Color.BLACK);
		g.fill(this);
		g.setColor(Color.WHITE);
		g.drawLine(0, 0, 0, height);
	}
}
