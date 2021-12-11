/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaeventos;
/**
 * @author miguelange
 */
public class Agenda {
    private Evento eventos[] = new Evento[366];
    private static int NUMENTRADA = 0;
    
    public Agenda(Evento nuevento) {
        this.eventos[NUMENTRADA] = nuevento;
        NUMENTRADA++;
    }
}
