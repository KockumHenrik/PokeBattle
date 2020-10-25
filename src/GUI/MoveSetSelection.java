package GUI;

import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import Objects.FullCharacter;
import Objects.Move;
import Objects.MoveSet;
import Objects.classCharacter;

public class MoveSetSelection {
	
	public CharacterSelection cs;
	public classCharacter cChar;
	
	public MoveSetSelection(CharacterSelection cs) {
		this.cs = cs;
	}
	
	public MoveSet moveSet;
	public ArrayList<Move> moves = new ArrayList<>();
	public ArrayList<String> nameOfMoves = new ArrayList<>();
	
	public void selectMoveSet(classCharacter cChar) {
		setupMoves();		
		this.cChar = cChar;
		//Big box
		JFrame parent = new JFrame("Select Move Set");
		
		//Buttons
		JButton showButton = new JButton("Show Stats");
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Remove");
		JButton doneButton = new JButton("Done");
		
		//Labels
		JLabel movesLabel = new JLabel("Choose 4 Moves");
		JLabel powerLabel = new JLabel("Power");
		JLabel ppLabel = new JLabel("PP");
		JLabel selectedMovesLabel = new JLabel("Selected Moves");
				
		//Fill list with moves
		String[] moveName = new String[moves.size()];
		for(int j = 0; j < moves.size(); j++) {
			moveName[j] = moves.get(j).getName();
		}
		JList moveList = new JList(moveName);
		moveList.setSelectedIndex(0);
		
		//Scroll, List and Text Areas
		JScrollPane movesPane = new JScrollPane(moveList);
		JTextArea powerTextArea = new JTextArea();
		JTextArea ppTextArea = new JTextArea();
		JList selectedMoves = new JList(nameOfMoves.toArray());
		JScrollPane selectedMovesPane = new JScrollPane(selectedMoves);
		
		//Add
		parent.add(movesLabel);
		parent.add(movesPane);
		parent.add(showButton);
		parent.add(addButton);
		parent.add(removeButton);
		parent.add(doneButton);
		parent.add(powerLabel);
		parent.add(powerTextArea);
		parent.add(ppLabel);
		parent.add(ppTextArea);
		parent.add(selectedMovesLabel);
		parent.add(selectedMovesPane);
		
		//Set Layout		
		parent.setLayout(null);	
		
		//Set some sizes?
		movesPane.setPreferredSize(new Dimension(210,250));
		selectedMovesPane.setPreferredSize(new Dimension(210, 200));
		showButton.setPreferredSize(new Dimension(150,60));
		addButton.setPreferredSize(new Dimension(100,60));
		removeButton.setPreferredSize(new Dimension(100,60));
		doneButton.setPreferredSize(new Dimension(150, 100));
		
		//Set size and position?
		Insets insets = parent.getInsets();
		parent.setSize(insets.left + insets.right + 450, insets.top + insets.bottom + 800);
		movesLabel.setBounds(20 + insets.left, 35 + insets.top, 100, 10);
		movesPane.setBounds(20 + insets.left, 60 + insets.top, movesPane.getPreferredSize().width, movesPane.getPreferredSize().height);
		selectedMovesLabel.setBounds(20 + insets.left, 410 + insets.top, 100, 10);
		selectedMovesPane.setBounds(20 + insets.left, 425 + insets.top , selectedMovesPane.getPreferredSize().width, selectedMovesPane.getPreferredSize().height);
		powerLabel.setBounds(270 + insets.left, 140 + insets.top, 100, 10);
		powerTextArea.setBounds(270 + insets.left, 155 + insets.top, 150, 30);		
		ppLabel.setBounds(270 + insets.left, 240 + insets.top, 100, 10);
		ppTextArea.setBounds(270 + insets.left, 255 + insets.top, 150, 30);	
		showButton.setBounds(270 + insets.left, 60 + insets.top, showButton.getPreferredSize().width, showButton.getPreferredSize().height);
		addButton.setBounds(20 + insets.left, 60 + 20 + movesPane.getPreferredSize().height + insets.top, addButton.getPreferredSize().width, addButton.getPreferredSize().height);
		removeButton.setBounds(130 + insets.left, 60 + 20 + movesPane.getPreferredSize().height + insets.top, removeButton.getPreferredSize().width, removeButton.getPreferredSize().height);
		doneButton.setBounds(270 + insets.left, 425 + insets.top, doneButton.getPreferredSize().width, doneButton.getPreferredSize().height);
		
		//Other
		moveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		parent.setLocationRelativeTo(null);
        parent.setVisible(true);
        removeButton.setEnabled(false);
        doneButton.setEnabled(false);
		
		showButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerTextArea.setText(Integer.toString(moves.get(moveList.getSelectedIndex()).getPower()));
                ppTextArea.setText(Integer.toString(moves.get(moveList.getSelectedIndex()).getPp()));
            }
        });
		
		addButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(nameOfMoves.size() < 4 && !nameOfMoves.contains(moves.get(moveList.getSelectedIndex()).getName())) {
            		nameOfMoves.add(moves.get(moveList.getSelectedIndex()).getName());
            		moves.add(moves.get(moveList.getSelectedIndex()));
                    selectedMoves.setListData(nameOfMoves.toArray());                  
                    removeButton.setEnabled(true);
                    if(nameOfMoves.size() == 4) {
                    	addButton.setEnabled(false);
                    	doneButton.setEnabled(true);
                    }
            	}
            }
        });
		
		removeButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	nameOfMoves.remove(selectedMoves.getSelectedIndex());
            	selectedMoves.setListData(nameOfMoves.toArray());
            	selectedMoves.setSelectedIndex(0);
            	if(nameOfMoves.size() == 0) {
            		removeButton.setEnabled(false);
            	}
            	addButton.setEnabled(true);
            	doneButton.setEnabled(false);
            }
        });
		doneButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	moveSet = new MoveSet();
            	for(int i = 0; i < 4; i++) {
            		moveSet.add(moves.get(i));
            	}            	
            	FullCharacter newChar = new FullCharacter(cChar, getMoveSet());
                cs.partyList.add(newChar);
                cs.nameOfMembersInParty.add(newChar.getCharacter().getName());
//                cs.party.setListData(cs.nameOfMembersInParty.toArray());
                cs.characterSelect();
                parent.dispose();
                
            }
        });
		
		
	}

	private void setupMoves() {
		Path dir = Paths.get("Moves/");
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
		        int power = Integer.parseInt(content[1].split(":")[1]);
		        int pp = Integer.parseInt(content[2].split(":")[1]);		        
		        moves.add(new Move(name, power, pp));
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    System.err.println(x);
		}	
	}
	
	public MoveSet getMoveSet() {
		return moveSet;
		
	}
}
