package complete_java_course_from_udemy.project.TextEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
	
	JFrame frame;
	JTextArea textArea;
	JMenuBar menuBar;
	
	TextEditor() {
		frame = new JFrame("Awesome Text Editor");
		textArea = new JTextArea();
		menuBar = new JMenuBar();
		
//		FILE MENU:
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem newItem = new JMenuItem("New");
		fileMenu.add(newItem);
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		
		
//		Since TextEditor class is implementing ActionListener Interface, that's why we are using this keyword:
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		
//		EDIT MENU:
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem cutItem = new JMenuItem("Cut");
		editMenu.add(cutItem);
		
		JMenuItem copyItem = new JMenuItem("Copy");
		editMenu.add(copyItem);
		
		JMenuItem pasteItem = new JMenuItem("Paste");
		editMenu.add(pasteItem);

		cutItem.addActionListener(this);
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		frame.setJMenuBar(menuBar);
		frame.add(textArea);
		frame.setSize(600, 400);
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		new TextEditor();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String currentItem = e.getActionCommand(); // Will return the item which has been clicked, name is in string format.
		
		if(currentItem.equals("Cut")) {
			textArea.cut();
		}else if(currentItem.equals("Copy")) {
			textArea.copy();
		}else if(currentItem.equals("Paste")) {
			textArea.paste();
		}else if(currentItem.equals("Save")) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showSaveDialog(frame); // Will return, what action the user has performed e.g: saved the file or did nothing.
			if(result == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath()); // Got the absolute path of the file.
				try {
					FileWriter fWriter = new FileWriter(file, false);
					BufferedWriter bWriter = new BufferedWriter(fWriter);
					
					bWriter.write(textArea.getText());
					bWriter.flush(); // Any data remaining in the buffer is writtem into the output stream immediately.
					bWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(currentItem.equals("Open")) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(frame);
			if(result == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					FileReader fReader = new FileReader(file);
					BufferedReader bReader = new BufferedReader(fReader);
					String line = "",  fileContent = "";
					while((line = bReader.readLine()) != null) {
						fileContent = fileContent + "\n" + line;
					}
					textArea.setText(fileContent);
					bReader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(currentItem.equals("New")) {
			textArea.setText("");
		}
	}

}
