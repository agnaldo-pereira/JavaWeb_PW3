package principal;

import model.Pessoa;

public class Principal {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa(1, "Fulano", "email@email.com", "123", "", 0, "", null);
		
		System.out.println(p1.toString());

	}

}
