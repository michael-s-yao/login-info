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

import java.io.*;
import java.net.URL;
import java.util.*;

public class LoginInfo {
    // File that stores login information in format of "url username password"
    // for each line (one URL per line of the text file).
    private File infoStorage;

    // Scanner to keep to read the file storing login information.
    private Scanner scanner;

    // List to store login information within the program.
    private List<LoginObject> userInfo;

    public LoginInfo(File f) {
        this.infoStorage = f;

        try {
            this.infoStorage.createNewFile();

            this.scanner = new Scanner(infoStorage);
        } catch (Exception e) {
            System.out.println("Invalid file name was entered.");
        }

        this.initializeUserInfo();
    }

    /* 
     * This function will read in all of the login information in the file
     * and convert it into a list: the this.userInfo field.
     *
     */
    public void initializeUserInfo() {
        this.userInfo = new ArrayList<>();

        while (this.scanner.hasNextLine()) {
            String[] info = this.scanner.nextLine().split(" ");

            URL newURL = null;

            if (info.length == 3) {
                try {
                    newURL = new URL(info[0]);
                } catch (Exception e) {
                    System.out.println("Invalid URL entered.");
                    return;
                }
				
                LoginObject o = new LoginObject(newURL, info[1], info[2]);

                this.userInfo.add(o);
            }
            else {
                continue;
            }
        }
    }

    /* This function adds a new login information entry. */
    public void newLoginEntry(URL url, String username, String password) {
    	// Add the new login information to the local list field instance.
        LoginObject o = new LoginObject(url, username, password);

        this.userInfo.add(o);

        // Also add the new login information to the storage text file.
        try {
            FileWriter w = new FileWriter(this.infoStorage, true);
            w.write(url.toString() + " " + username + " " + password + " \n");
            w.close();
        } catch (Exception e) {
            System.out.println("Invalid file name was entered.");
        }
    }

    /* This function retrieves an existing login entry. */
    public String[] retrieveLoginInfo(URL url, String username) {
        // result[0] keeps track of the username (if found).
        // result[1] keeps track of the password (if found).
        String[] result = new String[2];

        result[0] = "No URL Match.";
        result[1] = "No URL Match.";

        // Handle the case where no argument username was passed in.
        if (username == null || username.equals("No URL Match.")) {
            // Iterate backwards through the array to get the most recent
            // login information associated with the argument URL. We have to
            // iterate backwards because previous entries are not removed
            // when a password is updated. So, list elements are sorted in 
            // chronological order.
            for (int i = this.userInfo.size() - 1; i >= 0; i++) {
                if (this.userInfo.get(i).getURL().equals(url)) {
                    result[0] = this.userInfo.get(i).getUsername();

                    result[1] = this.userInfo.get(i).getPassword();

                    break;
                }
            }
        }
        else {
            // Iterate backwards through the array for the same reason as above.
            for (int i = this.userInfo.size() - 1; i >= 0; i--) {
                if (this.userInfo.get(i).getURL().equals(url)) {
                    // If an argument username is provided, then we also have to
                    // confirm that the username matches as well.
                    if (this.userInfo.get(i).getUsername().equalsIgnoreCase(
                            username)) {
                        result[0] = this.userInfo.get(i).getUsername();

                        result[1] = this.userInfo.get(i).getPassword();

                        break;
                    }
                }
            }
        }

        return result;
    }

    /* 
     * This function should update the password for the account associated with
     * the argument URL. 
     */
    public String updatePassword(URL url, String newPassword) {
        String contains = "Password successfully updated.";

        String notContains = "No URL entry found.";

        if (newPassword.isBlank()) {
            return "No new password given.";
        }

        for (int i = 0; i < this.userInfo.size(); i++) {
            if (this.userInfo.get(i).getURL().equals(url)) {
                try {
                    this.userInfo.get(i).changePassword(newPassword);

                    FileWriter w = new FileWriter(this.infoStorage, true);
                    w.write(url.toString() + " " + 
                            this.userInfo.get(i).getUsername() + " " + 
                     	    newPassword + " \n");
                    w.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return contains;
            }
        }

        return notContains;
    }
}
