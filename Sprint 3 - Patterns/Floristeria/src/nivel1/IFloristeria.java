package nivel1;
public interface IFloristeria {

    Ticket crearTicket();
    Stock getStock(String nombre);
    void imprimirStocks ();
    void mostrarCantidadStock();
    void sumatoriaValorStock();
    void mostrarTickets ();

}

