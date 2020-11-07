/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Owner
 */
public class PlayerNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PlayerNotFoundException</code> without
     * detail message.
     */
    public PlayerNotFoundException() {
        super("NBA API could not find player with last name: ");
    }

    /**
     * Constructs an instance of <code>PlayerNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PlayerNotFoundException(String msg) {
        super(msg);
    }
}
