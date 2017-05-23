/* Course: CS 3370/Fall 2013
   modified by Adriana Carolina Camacho and Jessica Rebollosa
   Assignment 8: Animation
   Instructor: Jesse Allen
   Date of last modification: Nov 24 2013
   			The coordinates of just the ant and not the entire scene 
 * */

import java.awt.*;
import java.awt.event.*;

public class Menus implements ActionListener {
	ComplexMain main;

	public Menus(ComplexMain theMain) {
		main = theMain;
		addMenus();
	}

	private void addMenus() {
		MenuBar bar = new MenuBar();
		main.setMenuBar(bar);

		Menu menuGen = new Menu("Exit Application");
		bar.add(menuGen);
		addMenuItem("Exit",menuGen);

		Menu moveAnt = new Menu("Move Ant Instructions");
		bar.add(moveAnt);
		addMenuItem("type 'w' to move forward", moveAnt);
		addMenuItem("type 'd' to turn right", moveAnt);
		addMenuItem("type 'a' to turn left", moveAnt);
		addMenuItem("type 's' to STOP", moveAnt);

		Menu darkMode = new Menu("The Dark Mode");
		bar.add(darkMode);
		addMenuItem("On/Off", darkMode);
		
		Menu rotation = new Menu("Rotation");
		bar.add(rotation);
		addMenuItem("Pause", rotation);
	}

	public void addMenuItem(String text,Menu menu) {
		MenuItem item = new MenuItem(text);
		item.addActionListener(this);
		menu.add(item);
	}

	public void actionPerformed(ActionEvent event) {
		handleAction(event.getActionCommand());
	}

	public void handleAction(String s) {
		if (s=="On/Off"){
			main.standardAmbient.toggle();
			main.standardDirectionalLight.toggle();
		}
		if (s=="Exit") System.exit(0);
		if (s == "Pause") main.rotatingSystem.toggle();
	}
}