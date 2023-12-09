import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.util.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

//NAME: Kalki Srinivasan

public class Calc2 extends Application {
	
	public static void main(String[] args) {
		launch(args); 
	}
	
	
	Button btnEqual;
	Button btnAC;
	Button btnAdd;
	Button btnSub;
	Button btnMultiply;
	Button btnDivide;
	Button btnRemainder;
	Button btnFloat;
	ArrayList<Button> btnDigits;
	TextField txtF;
	boolean floatPoint;//boolean to hold whether or not the floating point button is 
	
	//Stores the operands and operator
	String s1, s2, s3;
	
	
	/**
	 * Sets the stage for the application by initializing buttons, textfield and their layout.
	 */
	
	@Override public void start(Stage primaryStage) {
	
		// Create the textfield
		txtF = new TextField();
		
		// Create the buttons
		btnEqual = new Button();
		btnAC = new Button();
		btnAdd = new Button();
		btnSub = new Button();
		btnMultiply = new Button();
		btnDivide = new Button();
		btnRemainder = new Button();
		btnFloat = new Button();
		btnDigits = new ArrayList<Button>(10);
		floatPoint=false;
		
		// Initialize the operands and operators
		s1="";
		s2="";
		s3="";
		
	
		//Setting text labels for non-digit buttons
		btnEqual.setText("=");
		btnAC.setText("AC");
		btnAdd.setText("+");
		btnSub.setText("-");
		btnMultiply.setText("*");	
		btnDivide.setText("/");		
		btnRemainder.setText("%");
		btnFloat.setText(".");
		
		//==============================================================
		
		for(int i = 0;i < 10; i++)
		{
			Button temp = new Button();
			temp.setText(String.valueOf(i));
			btnDigits.add(temp);
		}
		
		
		
		//setting color of AC button to red
		btnAC.setStyle("-fx-background-color:Red");
		//======================================================================
		
		
		
		//==== TODO:3 For the TextField (a) Set text alignment, (b) Disable editing via typing, (c) Set a width ====
		
		txtF.setAlignment(Pos.TOP_RIGHT);//when text is added to text field, it is added to the right
		txtF.setEditable(false);//textfield cannot be edited through keyboard actions
		//txtF.setMaxWidth(225);
		txtF.setMaxWidth(100);
		
		btnAC.setOnAction(this::buttonClickReset);
		btnAdd.setOnAction(this::buttonClickAdd);
		btnSub.setOnAction(this::buttonClickSub);
		btnEqual.setOnAction(this::buttonClickEqual);
		btnMultiply.setOnAction(this::buttonClickMultiply);
		btnDivide.setOnAction(this::buttonClickDivide);
		btnRemainder.setOnAction(this::buttonClickRemainder);
		btnFloat.setOnAction(this::buttonClickFloat);
		
		
		//for loop that sets the onAction event for every press of a digit button
		for (int j=0;j<btnDigits.size();j++) {
			btnDigits.get(j).setOnAction(this::buttonClickDigit);
		}
		
		
		// Add buttons to HBox'es and then to VBox 
		VBox pane = new VBox(20); 
		
		HBox top_box = new HBox(20);
		HBox.setMargin(txtF, new Insets(0,0,0,10));
		top_box.getChildren().add(txtF);
		top_box.getChildren().add(btnAC);
		top_box.getChildren().add(btnEqual);
		
		
		HBox[] mid_box = new HBox[3];
		mid_box[0] = new HBox(35);
		HBox.setMargin(btnDigits.get(1), new Insets(0,0,0,10));
		mid_box[0].getChildren().addAll(btnDigits.subList(1, 4));
		mid_box[0].getChildren().add(btnAdd);
		
		
		mid_box[1] = new HBox(35);
		HBox.setMargin(btnDigits.get(4), new Insets(0,0,0,10));
		mid_box[1].getChildren().addAll(btnDigits.subList(4, 7));
		mid_box[1].getChildren().add(btnSub);
		
		mid_box[2] = new HBox(35);
		HBox.setMargin(btnDigits.get(7), new Insets(0,0,0,10));
		mid_box[2].getChildren().addAll(btnDigits.subList(7, 10));
		mid_box[2].getChildren().add(btnMultiply);
		
		
		HBox bot_box = new HBox(35);
		HBox.setMargin(btnFloat, new Insets(0,0,0,10));
		bot_box.getChildren().add(btnFloat);
		bot_box.getChildren().add(btnDigits.get(0));
		bot_box.getChildren().add(btnRemainder);
		bot_box.getChildren().add(btnDivide);
		
		
		pane.getChildren().addAll(top_box, mid_box[0], mid_box[1], mid_box[2], bot_box);
		
		
		// Add the root pane to a scene
		Scene scene = new Scene(pane, 250, 250);
		
		
		// Finalize and show the stage
		primaryStage.setScene(scene); 
		primaryStage.setTitle("Calculator 2.0"); 
		primaryStage.show();
	}
	
	/**
	 * Reset the operands, operators and the textfield when AC is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickReset(ActionEvent e) {
		
		//clears the operators and textfield
		s3="";	
		s2="";
		s1="";
		txtF.clear();
			}	
	
	/**
	 * Determine the operands when digits are clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickDigit(ActionEvent e) {

		if (s1=="" && s3=="") {
			//set first number to first operand
			s1= ((Button)e.getSource()).getText();
			txtF.setText(s1);
		} else if( s1!="" && s3==""){
			//add another number to first operand if another digit button clicked
			s1+= ((Button)e.getSource()).getText();
			txtF.setText(s1);
		} else if ( s1!="" && s3!="" && s2=="") {
			//clear text field and set first number to second operand
			txtF.clear();
			s2= ((Button)e.getSource()).getText();
			txtF.setText(s2);
		} else if (s1!="" && s3!="" && s2!="") {
			//add another number to second operand if another digit button clicked
			s2+= ((Button)e.getSource()).getText();
			txtF.setText(s2);
		}
		
	}
	
	/**
	 * Determine the operators when "+" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickAdd(ActionEvent e) {
		
		//set operator for addition
	    if (s3=="") {
	    	s3="+";
	    }		
	}
	
	/**
	 * Determine the operator when "-" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickSub(ActionEvent e) {
		// ============== TODO:8  You need to implement this method. ==============
		
		//set operator for substraction
		if (s3=="") {
	    	s3="-";
	    }
		
	 
	}
	
	/**
	 * Determine the operator when "*" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickMultiply(ActionEvent e) {
		if (s3=="") {
	    	s3="*";
	    }
	}
	
	/**
	 * Determine the operator when "/" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickDivide(ActionEvent e) {
		if (s3=="") {
	    	s3="/";
	    }
	}
	
	/**
	 * Determine the operator when "%" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickRemainder(ActionEvent e) {
		if (s3=="") {
	    	s3="%";
	    }
	}
	
	/**
	 * Determine the operands when the floating point button is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickFloat(ActionEvent e) {
		floatPoint=true;//set boolean to true as button is clicked
		
		 if( s1!="" && s3==""){
			//if s1 operand has been entered and floating point button is clicked
			s1+= ((Button)e.getSource()).getText();
			txtF.setText(s1);
		} else if ( s3!="" && s2!="") {
			//if s2 operand has been entered and floating point button is clicked
			s2+=((Button)e.getSource()).getText();
			txtF.setText(s2);}
	}
	
	/**
	 * Determine the result in the textfield when "=" is clicked
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickEqual(ActionEvent e) {
		
		//if else statements to calculate result based on operator button clicked
		//clear textfield of operand and set it to respective operator button clicked
		if (floatPoint==false) {
			if (s3=="+") {
				txtF.clear();
				txtF.setText(String.valueOf(Integer.parseInt(s1)+Integer.parseInt(s2)));
			} else if (s3=="-") {
				txtF.clear();
				txtF.setText(String.valueOf(Integer.parseInt(s1)-Integer.parseInt(s2)));
			} else if (s3=="*") {
				txtF.clear();
				txtF.setText(String.valueOf(Integer.parseInt(s1)*Integer.parseInt(s2)));
			} else if (s3=="/") {
				txtF.clear();
				txtF.setText(String.valueOf(Integer.parseInt(s1)/Integer.parseInt(s2)));
			} else if (s3=="%") {
				txtF.clear();
				txtF.setText(String.valueOf(Integer.parseInt(s1)%Integer.parseInt(s2)));
			}
			
		//if else statements to calculate result based on operator button clicked when floating point button is also clicked
		//usees floats instead of integer functions
		//NumberFormat.getInstance.format() used to remove any zero fractional portions from floating numbers
		} else {
			if (s3=="+") {
				txtF.clear();
				txtF.setText(String.valueOf(NumberFormat.getInstance().format(Float.parseFloat(s1)+Float.parseFloat(s2))));
			} else if (s3=="-") {
				txtF.clear();
				txtF.setText(String.valueOf(NumberFormat.getInstance().format(Float.parseFloat(s1)-Float.parseFloat(s2))));
			} else if (s3=="*") {
				txtF.clear();
				txtF.setText(String.valueOf(NumberFormat.getInstance().format(Float.parseFloat(s1)*Float.parseFloat(s2))));
			} else if (s3=="/") {
				txtF.clear();
				txtF.setText(String.valueOf(NumberFormat.getInstance().format(Float.parseFloat(s1)/Float.parseFloat(s2))));
			} else if (s3=="%") {
				txtF.clear();
				txtF.setText(String.valueOf(NumberFormat.getInstance().format(Float.parseFloat(s1)%Float.parseFloat(s2))));
				}
			}
		
		
		// Resetting the operands and operator after the result is calculated.
			s3="";	
			s2="";
			s1="";
	}

}
