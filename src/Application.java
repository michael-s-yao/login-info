/*******************************************************************************
 * Copyright 2020 Michael S. Yao
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.io.*;
import java.net.*;

public class Application {
    private static int width = 600;
    private static int height = 800;
    
    private static String storagePath = ".hiddenLoginStorageFile.txt";
    
    private static LoginInfo storage;

    public static void main(String[] args) {  
    	File file = new File(storagePath);
    	storage = new LoginInfo(file);

        JFrame frame = new JFrame("Login Information Application"); 

        JButton addButton = new JButton("Add Login Information");    
        addButton.setBounds(200, 210, 200, 30);
        
        JButton retrieveButton = new JButton("Retrieve Login Information");    
        retrieveButton.setBounds(200, 390, 200, 30);
        
        JButton updateButton = new JButton("Update Password");
        updateButton.setBounds(200, 625, 200, 30);

        JLabel label1 = new JLabel("URL: ");		
        label1.setBounds(100, 85, 200, 30);

        JLabel label2 = new JLabel("Username: ");
        label2.setBounds(100, 120, 250, 30);
        
        JLabel label3 = new JLabel("Password: ");
        label3.setBounds(100, 155, 250, 30);
        
        JLabel label4 = new JLabel("URL: ");
        label4.setBounds(100, 300, 250, 30);
        
        JLabel label5 = new JLabel("Username (Optional): ");
        label5.setBounds(100, 335, 250, 30);
        
        JLabel label6 = new JLabel("Password: ");
        label6.setBounds(100, 445, 250, 30);
        
        JLabel label7 = new JLabel("URL: ");
        label7.setBounds(100, 535, 250, 30);
        
        JLabel label8 = new JLabel("New Password: ");
        label8.setBounds(100, 570, 250, 30);

        JLabel outStatus1 = new JLabel();
        outStatus1.setBounds(230, 245, 250, 30);
        
        JLabel outStatus2 = new JLabel();
        outStatus2.setBounds(220, 675, 250, 30);

        JTextField addURL = new JTextField();
        addURL.setBounds(175, 85, 325, 30);

        JTextField addUsername = new JTextField();
        addUsername.setBounds(175, 120, 325, 30);
        
        JTextField addPassword = new JTextField();
        addPassword.setBounds(175, 155, 325, 30);
        
        JTextField retrieveURL = new JTextField();
        retrieveURL.setBounds(175, 300, 325, 30);
        
        JTextField retrieveUsername = new JTextField();
        retrieveUsername.setBounds(240, 335, 260, 30);
        
        JTextField retrievePassword = new JTextField();
        retrievePassword.setBounds(240, 445, 260, 30);
        
        JTextField updateURL = new JTextField();
        updateURL.setBounds(175, 535, 325, 30);
        
        JTextField updatePassword = new JTextField();
        updatePassword.setBounds(240, 570, 260, 30);

        frame.add(outStatus1);
        frame.add(outStatus2);
        frame.add(addURL);
        frame.add(addUsername);
        frame.add(addPassword);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        frame.add(label7);
        frame.add(label8);
        frame.add(addButton); 
        frame.add(retrieveButton);
        frame.add(updateButton);
        frame.add(retrieveURL);
        frame.add(retrieveUsername);
        frame.add(retrievePassword);
        frame.add(updateURL);
        frame.add(updatePassword);
        frame.setSize(width, height);    
        frame.setLayout(null);    
        frame.setVisible(true); 
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                frame.dispose();
                System.exit(0);
            }
        });

        addButton.addActionListener(new ActionListener() {
	        
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	try {
            	    URL url = new URL(addURL.getText());
            	    storage.newLoginEntry(url, addUsername.getText(), 
            	    		addPassword.getText());
            	    
            	    outStatus1.setText("Login Information Added!");
                    
            	} catch (Exception e) {
            		outStatus1.setText("Error in adding login information.");
            		
            		e.printStackTrace();
            	}
            }
        });
        
        retrieveButton.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			URL argURL = new URL(retrieveURL.getText());
        			
        			String[] res;
        		    
        		    if (retrieveUsername.getText().isBlank()) {
            		    res = storage.retrieveLoginInfo(argURL, null);

        		    }
        		    else {
        		    	res = storage.retrieveLoginInfo(argURL, 
        		    			retrieveUsername.getText());
        		    }
        		    
        		    retrieveUsername.setText(res[0]);
        		    
        		    retrievePassword.setText(res[1]);
        		    
        		} catch (Exception e) {
        		    
        		    e.printStackTrace();
        		}
        	}
        });
        
        updateButton.addActionListener(new ActionListener() {
	        
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	try {
            	    URL url = new URL(updateURL.getText());
            	    storage.updatePassword(url, updatePassword.getText());
            	    
            	    outStatus2.setText("Login Information Updated!");
                    
            	} catch (MalformedURLException e) {
            		outStatus2.setText("Error in updating password.");
            		
            		e.printStackTrace();
            	}
            }
        });
    }         
}   

