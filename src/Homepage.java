
import java.awt.Font;

import javax.swing.JLabel;

public class Homepage extends Page {
	
	public Homepage() {

		// space component
        JLabel spacer = new JLabel(" ");
		
		// adding the title component
		JLabel title = new JLabel("Welcome to the Knowledge Base!");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		// adding the subtitle
		JLabel subtitle = new JLabel("Knowledge by students for students ...");
		subtitle.setFont(new Font("Helvetica", Font.BOLD, 18));

		add(title);
		add(spacer);
		add(subtitle);
	}	
}