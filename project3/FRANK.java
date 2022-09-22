package IS;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * IS - a robot by (Matthew Corbett, Maddison Coffey, Landon Maxfield, Parker Schmidt)
 */
public class Frank extends Robot
{
	//variable tracks which direction the robot should move
	boolean foreward = true;
	/**
	 * run: IS's default behavior
	 */
	public void run() {
		//set color
		setColors(Color.red,Color.red,Color.red); //body, gun, and radar all red
		//intiailize robot behavior
		//head to nearest wall and square up opposite
		turnLeft(getHeading() % 90);
		ahead(getBattleFieldWidth());
		turnGunRight(90);
		turnRight(90);


		// Robot main loop
		while(true) {
			//traverses the field and scans scans and switches eirctions 
			if (foreward){
			ahead(getBattleFieldHeight());
			foreward = false;
			scan();
			}else{
			back(getBattleFieldHeight());	
			foreward = true;
			scan();
			}			
		}
	}
	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		//Variable power based on the scanned robots distance
		if (e.getDistance() < 20){
		fire(5);
		}else if (e.getDistance() < 40){
		fire(3);
		}else if (e.getDistance() < 60){
		fire(2);
		}else{
		fire(1);	
		}
}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
	//Swithces direction when struck by a bullet	
	if (foreward){
		foreward = false;	
		}else{
		foreward = true;
		}
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		//switch direction if I hit a wall
		foreward = false;
	}	
	
	public void onWin(WinEvent e){
	//step to the haters
	turnRight(90);
	ahead(3000);	
	turnGunRight(360);
	back(3000);
	turnRight(90);
	ahead(3000);
	
	//seppuku - warrior suicide!
	fire(1000000000);
	
	}
}

	