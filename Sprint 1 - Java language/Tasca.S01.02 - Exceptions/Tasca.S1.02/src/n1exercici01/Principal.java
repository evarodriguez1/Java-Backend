package n1exercici01;

public class Principal {

	public static void main(String[] args) {
		
		try{
			main2();
		}catch (Exception e) {
			System.out.println("S'ha produ�t un error:" + e.getMessage());
		}finally {
			System.out.println("Estic passant per el finally");
		}

	}
	public static void main2() throws Exception {
		throw new Exception ("Aix� es una excepci�");
	}
}
