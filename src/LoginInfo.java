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
    // TODO: Add any relevant fields here.

    public LoginInfo(File f) {
        // TODO: f is a .txt file that will store all the login 
        // information. Do whatever you need to do here.
    }

    /* 
     * This function adds a new login information entry with the argument
     * URL, username, and password.
     */
    public void newLoginEntry(URL url, String username, String password) {
        // TODO: Feel free to change the return variable type if you want.

        return;
    }

    /* 
     * This function retrieves an existing login entry with the argument
     * URL. The username is optional. If a username is provided, the login
     * information entry should match the argument username (case-
     * insensitive.
     */
    public String[] retrieveLoginInfo(URL url, String username) {
        // TODO: Return type should be a String array of length 2.
        // The 0th argument should be the retrieved username.
        // The 1st argument should be the retrieved password.
        // If no username and password are found, then return the array
        // { "No URL Match.", "No URL Match." }.

        return null;
    }

    /* 
     * This function should update the password for the account associated with
     * the argument URL. 
     */
    public String updatePassword(URL url, String newPassword) {
        // TODO: Return "Password successfully updated." If password update was
        // successful and argument URL was found. Otherwise, return
        // "No URL entry found.".

        return null;
    }
}
