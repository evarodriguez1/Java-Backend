package n2exercici02;

public class ThrowException {
	public void f() throws Exception2{
		try {
			g();
		}catch (Exception1 e){
			System.out.println("S'ha produ�t un error: "+ e.getMessage());
			throw new RuntimeException(e);
		}//s'embolica una excepci� ja detectada dins d'una altra excepci�
	}
	
	public void g() throws Exception1{
		throw new Exception1 ("Excepci� 1");
	}
}
 

class Exception1 extends Exception{
	public Exception1 (String missatge) {
		super(missatge);
	}
}	
class Exception2 extends Exception{
	public Exception2 (String missatge) {
		super(missatge);
	}
}
