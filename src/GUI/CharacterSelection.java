package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import Objects.classCharacter;

public class CharacterSelection {
	
	public ArrayList<classCharacter> characterList = new ArrayList<>();
	
	public void characterSelect() {
		setupCharacters();
		
		//Big boxes
		JFrame parent = new JFrame();
		
		//Buttons
		JButton selectButton = new JButton("Select Moves");
		JButton showButton = new JButton("Show");
		
		//Labels
		JLabel characterLabel = new JLabel("Characters");
		JLabel HpLabel = new JLabel("HP");
		JLabel AtkLabel = new JLabel("ATK");
		JLabel DefLabel = new JLabel("DEF");
		
		//TextArea and Scroll
		String[] names = new String[characterList.size()];
		for(int j = 0; j < characterList.size(); j++) {
			names[j] = characterList.get(j).getName();
		}
		JList list = new JList(names);
		JTextArea characterTextArea = new JTextArea();
		characterTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(list);
		JTextArea HpTextArea = new JTextArea();
		JTextArea AtkTextArea = new JTextArea();
		JTextArea DefTextArea = new JTextArea();
		
		//Add
		parent.add(characterLabel);
		parent.add(scrollPane);
		parent.add(HpLabel);
		parent.add(HpTextArea);
		parent.add(AtkLabel);
		parent.add(AtkTextArea);
		parent.add(DefLabel);
		parent.add(DefTextArea);
		parent.add(showButton);
		parent.add(selectButton);
		
		//Set Layout		
		parent.setLayout(null);		
		
		//Set some sizes?
		scrollPane.setPreferredSize(new Dimension(280,700));
		selectButton.setPreferredSize(new Dimension(300,100));
		showButton.setPreferredSize(new Dimension(300,100));
		
		
		//Set size and position?
		Insets insets = parent.getInsets();
		parent.setSize(insets.left + insets.right + 600, insets.top + insets.bottom + 1000);
		characterLabel.setBounds(10 + insets.left, 35 + insets.top, 100, 10);
		scrollPane.setBounds(10 + insets.left, 60 + insets.top, scrollPane.getPreferredSize().width, scrollPane.getPreferredSize().height);
		
		HpLabel.setBounds(370 + insets.left, 100 + insets.top, 100, 10);
		HpTextArea.setBounds(370 + insets.left, 115 + insets.top, 150, 30);
		
		AtkLabel.setBounds(370 + insets.left, 200 + insets.top, 100, 10);
		AtkTextArea.setBounds(370 + insets.left, 215 + insets.top, 150, 30);
		
		DefLabel.setBounds(370 + insets.left, 300 + insets.top, 100, 10);
		DefTextArea.setBounds(370 + insets.left, 315 + insets.top, 150, 30);
		
		showButton.setBounds(insets.left, 790 + insets.top, showButton.getPreferredSize().width, showButton.getPreferredSize().height);
		selectButton.setBounds(300 + insets.left, 790 + insets.top, selectButton.getPreferredSize().width, selectButton.getPreferredSize().height);
		
		//Other
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		parent.setLocationRelativeTo(null);
        parent.setVisible(true);
		
		//Fill character text area with character list
		for(int i =0 ; i < characterList.size(); i++) {
			characterTextArea.append(characterList.get(i).getName() + "\n");
		}
				
		parent.setLocationRelativeTo(null);
        parent.setVisible(true);
        
        showButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HpTextArea.setText(Integer.toString(characterList.get(list.getSelectedIndex()).getHP()));
                AtkTextArea.setText(Integer.toString(characterList.get(list.getSelectedIndex()).getATK()));
                DefTextArea.setText(Integer.toString(characterList.get(list.getSelectedIndex()).getDEF()));
            }
        });
        
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HpTextArea.setText(Integer.toString(characterList.get(list.getSelectedIndex()).getHP()));
                AtkTextArea.setText(Integer.toString(characterList.get(list.getSelectedIndex()).getATK()));
                DefTextArea.setText(Integer.toString(characterList.get(list.getSelectedIndex()).getDEF()));
            }
        });
	}
	
	public void setupCharacters() {
		Path dir = Paths.get("Characters/");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		    	Scanner scanner = new Scanner(new File(file.toString()));
		        String[] content = new String[4];
		        int index = 0;
		        while(scanner.hasNextLine()) {
		        	content[index] = scanner.nextLine();
		        	index++;
		        }
		        String name = content[0].split(":")[1];
		        int Hp = Integer.parseInt(content[1].split(":")[1]);
		        int Atk = Integer.parseInt(content[2].split(":")[1]);
		        int Def = Integer.parseInt(content[3].split(":")[1]);
		        characterList.add(new classCharacter(name, Hp, Atk, Def));
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    System.err.println(x);
		}
	}
}
