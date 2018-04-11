import java.awt.Color;

import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * @author Amy Larson amlarson10@dmacc.edu 3/19/2018 updated 4/2/18 CIS152
 * This class creates a JButton that can be disabled or enabled based on conditions.
 */
public class ConditionalButton extends JButton {

	private static final long serialVersionUID = 8864534052108560092L; //compiler flagged need for this
	private String onText;
	private Color onColor;
	private String offText = null;
	private final Color OFF_COLOR = UIManager.getColor("Button.disabledForeground"); //same for all buttons of this class


	/**
	 *Constructor for ConditionalButton
	 */
	public ConditionalButton() {
		super();
	};
		
	/**
	 *Constructor for ConditionalButton
	 * @param onText
	 * @param onColor
	 * @param startEnabled
	 */
	public ConditionalButton(String onText, Color onColor, boolean startEnabled) {
		super();
		this.onColor=onColor;
		setOnText(onText);
		setOffText();
		toggleBtnEnable(startEnabled);
	}

	/**
	 *getter for OFF_COLOR
	 * @return
	 */
	public Color getOFF_COLOR() {
		return OFF_COLOR;
	}
	/**
	 *getter for on color
	 * @return
	 */
	public Color getOnColor() {
		return onColor;
	}

	/**
	 *setter for on color
	 * @param onColor
	 */
	public void setOnColor(Color onColor) {
		this.onColor = onColor;
	}


	/**updates look, text and enable of button
	 * @param on enter true if button should be enabled
	 */
	public void toggleBtnEnable(boolean on) {
			if (on) {// sets button to the 'on' look
				this.setEnabled(true);
				this.setBackground(onColor);
				this.setText(onText);
			} else {// sets button to it's 'off' look
				this.setEnabled(false);
				this.setBackground(OFF_COLOR);
				this.setText(offText);
		}
	}

	/**
	 * gets the text that shows when butten is enabled
	 * @return
	 */
	public String getOnText() {
		return onText;
	}


	/**sets the text that shows when butten is enabled
	 * @param onText
	 */
	public void setOnText(String onText) {
		this.onText = "  "+onText+"  ";
	}

	/**
	 * gets the text that shows when button is disabled
	 * @return
	 */
	public String getOffText() {
		return offText;
	}

	/**
	 * sets text that shows when button is disabled
	 */
	public void setOffText() {
			this.offText = ("  X"+getOnText());
	}
	
}