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

import Objects.FullCharacter;
import Objects.classCharacter;

public class CharacterSelection {
	
	public ArrayList<classCharacter> characterList = new ArrayList<>();
	public ArrayList<FullCharacter> partyList = new ArrayList<>();
	public ArrayList<String> nameOfMembersInParty = new ArrayList<>();
	public MoveSetSelection mss;
	public JList party;
	public JList nameList;
	public JButton editButton;
	public boolean first = true;
	public String[] names;
	
	public void characterSelect() { //Clean up and but GUI parts in seperate method
		if(first) {
			setupCharacters();
		}
		
		//Big boxes
		JFrame parent = new JFrame("Build Party");
		
		//Buttons
		JButton selectButton = new JButton("Select Moves");
		JButton showButton = new JButton("Show Stats");
		editButton = new JButton("Edit Character");
		
		//Labels
		JLabel characterLabel = new JLabel("Characters");
		JLabel partyLabel = new JLabel("Party");
		JLabel HpLabel = new JLabel("HP");
		JLabel AtkLabel = new JLabel("ATK");
		JLabel DefLabel = new JLabel("DEF");
		JLabel SpdLabel = new JLabel("SPD");
		
		//Fill list with character names (Move to setup characters later)
		if(first) {
			names = new String[characterList.size()];
			for(int j = 0; j < characterList.size(); j++) {
				names[j] = characterList.get(j).getName();
			}			
		}		
		nameList = new JList(names);
		nameList.setSelectedIndex(0);
		party = new JList(nameOfMembersInParty.toArray());
		if(nameOfMembersInParty.size()>0) {
			party.setSelectedIndex(0);
		}
		
		//Scroll, List and Text areas
		JScrollPane scrollPane = new JScrollPane(nameList);
		JScrollPane partyPane = new JScrollPane(party);
		JTextArea HpTextArea = new JTextArea();
		JTextArea AtkTextArea = new JTextArea();
		JTextArea DefTextArea = new JTextArea();
		JTextArea SpdTextArea = new JTextArea();
		
		//Add
		parent.add(characterLabel);
		parent.add(scrollPane);
		parent.add(partyLabel);
		parent.add(partyPane);
		parent.add(HpLabel);
		parent.add(HpTextArea);
		parent.add(AtkLabel);
		parent.add(AtkTextArea);
		parent.add(DefLabel);
		parent.add(DefTextArea);
		parent.add(SpdLabel);
		parent.add(SpdTextArea);
		parent.add(showButton);
		parent.add(selectButton);
		parent.add(editButton);
		
		//Set Layout		
		parent.setLayout(null);		
		
		//Set some sizes?
		scrollPane.setPreferredSize(new Dimension(280,450));
		partyPane.setPreferredSize(new Dimension(280,200));
		selectButton.setPreferredSize(new Dimension(150,60));
		showButton.setPreferredSize(new Dimension(150,60));
		editButton.setPreferredSize(new Dimension(260,60));
			
		//Set size and position?
		Insets insets = parent.getInsets();
		parent.setSize(insets.left + insets.right + 600, insets.top + insets.bottom + 1000);
		characterLabel.setBounds(10 + insets.left, 35 + insets.top, 100, 10);
		scrollPane.setBounds(10 + insets.left, 60 + insets.top, scrollPane.getPreferredSize().width, scrollPane.getPreferredSize().height);		
		partyLabel.setBounds(10 + insets.left, 660 + insets.top, 100, 20);
		partyPane.setBounds(10 + insets.left, 680 + insets.top, partyPane.getPreferredSize().width, partyPane.getPreferredSize().height);		
		HpLabel.setBounds(370 + insets.left, 140 + insets.top, 100, 10);
		HpTextArea.setBounds(370 + insets.left, 155 + insets.top, 150, 30);		
		AtkLabel.setBounds(370 + insets.left, 240 + insets.top, 100, 10);
		AtkTextArea.setBounds(370 + insets.left, 255 + insets.top, 150, 30);		
		DefLabel.setBounds(370 + insets.left, 340 + insets.top, 100, 10);
		DefTextArea.setBounds(370 + insets.left, 355 + insets.top, 150, 30);	
		SpdLabel.setBounds(370 + insets.left, 440 + insets.top, 100, 10);
		SpdTextArea.setBounds(370 + insets.left, 455 + insets.top, 150, 30);	
		showButton.setBounds(370 + insets.left, 60 + insets.top, showButton.getPreferredSize().width, showButton.getPreferredSize().height);
		selectButton.setBounds(10 + insets.left, 525 + insets.top, selectButton.getPreferredSize().width, selectButton.getPreferredSize().height);
		editButton.setBounds(320 + insets.left, 680 + insets.top, editButton.getPreferredSize().width, editButton.getPreferredSize().height);
		
		//Other
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		parent.setLocationRelativeTo(null);
        parent.setVisible(true);
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        showButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HpTextArea.setText(Integer.toString(characterList.get(nameList.getSelectedIndex()).getHP()));
                AtkTextArea.setText(Integer.toString(characterList.get(nameList.getSelectedIndex()).getATK()));
                DefTextArea.setText(Integer.toString(characterList.get(nameList.getSelectedIndex()).getDEF()));
                SpdTextArea.setText(Integer.toString(characterList.get(nameList.getSelectedIndex()).getSPD()));
            }
        });
        
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	callMoveSetSelector(characterList.get(nameList.getSelectedIndex()));
            	parent.dispose();
            }
        }); 
        
        editButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	mss.editMoveSet(partyList.get(party.getSelectedIndex()));
            	partyList.remove(party.getSelectedIndex());
                nameOfMembersInParty.remove(party.getSelectedIndex());
            	parent.dispose();
            }
        });
        
        first = false;
	}
	
	public void callMoveSetSelector(classCharacter ch) {
		mss = new MoveSetSelection(this);
        //parent.setVisible(false);
    	mss.selectMoveSet(ch);
        
	}
	
	public void setupCharacters() {
		Path dir = Paths.get("Characters/");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		    	Scanner scanner = new Scanner(new File(file.toString()));
		        String[] content = new String[5];
		        int index = 0;
		        while(scanner.hasNextLine()) {
		        	content[index] = scanner.nextLine();
		        	index++;
		        }
		        String name = content[0].split(":")[1];
		        int Hp = Integer.parseInt(content[1].split(":")[1]);
		        int Atk = Integer.parseInt(content[2].split(":")[1]);
		        int Def = Integer.parseInt(content[3].split(":")[1]);
		        int Spd = Integer.parseInt(content[4].split(":")[1]);
		        characterList.add(new classCharacter(name, Hp, Atk, Def, Spd));
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    System.err.println(x);
		}
	}
}
