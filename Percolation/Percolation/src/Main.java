import java.util.Collection;
import  java.awt.FileDialog;
import java.awt.Frame;
import java.io.*;
import javax.swing.JOptionPane;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class Main {

//	input Text, table components declear

	protected Shell shlCalculateapplication;
	private Text txtProbab;
	private Text txtDimention;
	private Table table;	
	private TableColumn column;
	
	private Engine engine;
	private Text txtStep;
	private Label lblStep;
	private Button btnRenormaliz;
	private Button btnSave;
	
	private int cal_status = 0; //0 - calculate, 1 - renormalization

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCalculateapplication.open();
		shlCalculateapplication.layout();
		while (!shlCalculateapplication.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCalculateapplication = new Shell();
		shlCalculateapplication.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		shlCalculateapplication.setSize(608, 714);
		shlCalculateapplication.setText("A program for calculating percolation");
		shlCalculateapplication.setLocation(500,100);
		
		table = new Table(shlCalculateapplication, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(33, 166, 533, 499);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		// create column "conductivity" and setvalue.
		column = new TableColumn(table, SWT.CENTER);
		column.setText("Conductivity");
		column.setWidth(400);
		// create column "propability" and setvalue.
		TableColumn column2 = new TableColumn(table, SWT.NULL);
		column2.setText("Propability");
		column.setWidth(600);
		
		Group group = new Group(shlCalculateapplication, SWT.NONE);
		group.setBounds(33, 10, 532, 110);
		
		final Label lblRG = new Label(shlCalculateapplication, SWT.NONE);
		lblRG.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblRG.setBounds(33, 145, 202, 15);		
		
		Button btnCalculate = new Button(group, SWT.NONE);
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				double probabiliy;
				int dimension;
				engine = new Engine();
				
				table.clearAll();
				table.setItemCount(0);		
					
				if(txtProbab.getText()!=""){
					probabiliy = Double.parseDouble(txtProbab.getText());
				}else{
			
					JOptionPane.showMessageDialog(null, "Propability is Empty!");
					return;
				}
				if(txtDimention.getText()!=""){
					dimension = Integer.parseInt(txtDimention.getText());
				}else{
					JOptionPane.showMessageDialog(null, "Size is Empty!");
					return;
				}			
				
				engine.mainFunction(probabiliy, dimension);
				setTableValue();
				
				cal_status = 0;
				lblRG.setText("");
				
				//enable renormaliz button and step text
				txtStep.setEnabled(true);
				btnRenormaliz.setEnabled(true);
				btnSave.setEnabled(true);				 
			}
		});
		btnCalculate.setBounds(100, 74, 97, 26);
		btnCalculate.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnCalculate.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnCalculate.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btnCalculate.addSelectionListener(new SelectionListener() {			
			public void widgetSelected(SelectionEvent  e) {				
			}			
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub				
			}
		});
		btnCalculate.setText("Calculate");
		
		Label lblDimention = new Label(group, SWT.NONE);
		lblDimention.setBounds(52, 40, 97, 21);
		lblDimention.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblDimention.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblDimention.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblDimention.setText("Size");
		
		txtDimention = new Text(group, SWT.BORDER | SWT.CENTER);
		txtDimention.setText("2");
		txtDimention.setBounds(155, 40, 102, 21);
		txtDimention.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		
		txtProbab = new Text(group, SWT.BORDER | SWT.CENTER);
		txtProbab.setText("0.5");
		txtProbab.setBounds(155, 13, 102, 21);
		txtProbab.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		
		Label lblProb = new Label(group, SWT.NONE);
		lblProb.setBounds(52, 13, 97, 21);
		lblProb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblProb.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblProb.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblProb.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblProb.setText("Propability");
		
		txtStep = new Text(group, SWT.BORDER | SWT.CENTER);
		txtStep.setText("5");
		txtStep.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		txtStep.setEnabled(false);
		txtStep.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
								
				// Get the wikdget whose text was modified
		        Text stepText = (Text) arg0.widget;
		        String tt = stepText.getText();	        
	         	   
		        if(tt.length() > 0){
					try{
					   int step = Integer.parseInt(stepText.getText());
					   if(step < 0  || step > 15){
							JOptionPane.showMessageDialog(null, "Step should be in range of between 0 and 15");						
							stepText.setText("");
							return;
						}
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Step should be in range of between 0 and 15");
						stepText.setText("");
						return;
				    }
		        }		         
			}
		});
		txtStep.setBounds(380, 29, 102, 21);
		
		lblStep = new Label(group, SWT.NONE);
		lblStep.setText("Step");
		lblStep.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblStep.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblStep.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblStep.setBounds(315, 29, 59, 21);
		
		
		
		btnRenormaliz = new Button(group, SWT.NONE);
		btnRenormaliz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {			
				
				if(txtDimention.getText().trim().equals("2")){
				
					if(txtStep.getText().trim().equals("")){
						JOptionPane.showMessageDialog(null, "Please Enter a Number between 0 and 15 on Step-textbox");
						return;
					}
					engine.renormalizStep = Integer.parseInt(txtStep.getText());					
					double probab = Double.parseDouble(txtProbab.getText());
					
					engine.renormalization(probab);							
					
					table.clearAll();
					table.setItemCount(0);						
					setTableValue();
					
					cal_status = 1;  
					
					String strRG = "R=" + Double.toString(engine.R) + "  G=" + Double.toString(engine.G);		
					lblRG.setText(strRG);
				}
				else{
					JOptionPane.showMessageDialog(null, "The Renormalization cannot calculate with " + txtDimention.getText() + " of the dimension");
					//desable renormaliz button and step text
					txtStep.setEnabled(false);
					btnRenormaliz.setEnabled(false);						  
				}
			}
		});		
		btnRenormaliz.setEnabled(false);
		btnRenormaliz.setText("Renormalize");
		btnRenormaliz.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnRenormaliz.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnRenormaliz.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btnRenormaliz.setBounds(355, 74, 97, 26);
		
		btnSave = new Button(shlCalculateapplication, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				FileDialog fd = new FileDialog(new Frame(), "Save", FileDialog.SAVE);	
				fd.setFile("Result.txt");
				
				fd.setVisible(true);
				
				if(fd.getDirectory() == null || fd.getFile() == null){
					return;
				}
				 
				StringBuilder sb = new StringBuilder();
				sb.append(fd.getDirectory());
				sb.append(fd.getFile());
				String path = sb.toString();
				
				try {				      
					 BufferedWriter out = new BufferedWriter(new FileWriter(path));
					 
					 //The first line in the file is a Propability value and a dimension value.
					 String firstLine = "p=";
					 firstLine += txtProbab.getText();
					 firstLine += " d=";
					 firstLine += txtDimention.getText();
					 
					 out.write(firstLine); out.newLine();		
					 
					 //write data
					 Collection<Double> keys = engine.finalVals.keySet();
					 for(Double key: keys){
						String str = Double.toString(key);
						str += " ";
						str += Double.toString(engine.finalVals.get(key));						
						out.write(str); out.newLine();						
					}
					 
					//If renormalization is calculated
					if(cal_status == 1){
						String lastLine = "R=" + Double.toString(engine.R) + "  G=" + Double.toString(engine.G);
						out.write(lastLine); out.newLine();	
					}
					 
					JOptionPane.showMessageDialog(null, "The data was saved.");					 
				    out.close();				       
				} catch (IOException ex) {
				     System.err.println(ex);  
				     System.exit(1);
				}			
			}
		});
		btnSave.setEnabled(false);
		btnSave.setText("Save");
		btnSave.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		btnSave.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnSave.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW));
		btnSave.setBounds(469, 134, 97, 26);
		
		
		
		// add listener to txt input components in order to monitor the input value
		txtProbab.addModifyListener(new ModifyListener(){
			
		      public void modifyText(ModifyEvent event) {
		    	  
		        // Get the widget whose text was modified
		        Text probText = (Text) event.widget;
		        String tt = probText.getText();
		        
		        // except of empty latters		        
		        if(tt.trim().equals("")) return;
		        
		        try{
					double prob = Double.parseDouble(tt);
					
			        // if input value is lower than 1.0
			        if(!(prob <= 1.0)){
						JOptionPane.showMessageDialog(null, "Propability should be in range of between 0.5 and 1!");
						probText.setText("");
						return;
					}
			        
			        int len = probText.getCharCount();
			        
			        //if input value is bigger than 1.0
			        if(len > 2 && prob <0.5){
			        	JOptionPane.showMessageDialog(null, "Propability should be in range of between 0.5 and 1!");
			        	probText.setText("");
						return;
			        }					
		        }catch(Exception e){
					//text.setText("");
					JOptionPane.showMessageDialog(null, "Propability should be in range of between 0.5 and 1!");
					probText.setText("");
					return;
				}				
		     }	      
		});
		
		txtDimention.addModifyListener(new ModifyListener(){			
		      public void modifyText(ModifyEvent event) {    	
		    	  
		        // Get the wikdget whose text was modified
		        Text DimText = (Text) event.widget;
		        String tt = DimText.getText();
		        // except of empty latters 
		        if(tt.trim().equals("")){
		        	return;
		        }
				try{
				   int dimension = Integer.parseInt(DimText.getText());
				   if(!(dimension>=2 && dimension<=4)){
						JOptionPane.showMessageDialog(null, "Size should be in range of between 2 and 4!");						
						DimText.setText("");
						return;
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Size should be in range of between 2 and 4!");
					DimText.setText("");
					return;
			     }		
		     }
		});
		// should call function pack for every column
		for (int i = 0; i < table.getColumnCount(); i++) {
		    table.getColumn(i).pack();
		}
	}
	
	public void setTableValue(){		 
		
		Collection<Double> keys = engine.finalVals.keySet();
		for(Double key: keys){
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0,Double.toString(key));
			item.setText(1,Double.toString(engine.finalVals.get(key)));		 
		}
		
		for (int i = 0; i < table.getColumnCount(); i++) {
		    table.getColumn(i).pack();
		}
	 
	}
}
