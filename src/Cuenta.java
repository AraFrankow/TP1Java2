import javax.swing.JOptionPane;

public class Cuenta {
	String nombre;
	String contraseña;
	private double depositar;
	private double retirar;
	private double transferir;
	private double DineroActual;
	
	public Cuenta(String nombre, String contraseña) {
		this.nombre=nombre;
		this.DineroActual = 0;
		this.contraseña = contraseña;
	}
	
	public String GetNombre() {
		return nombre;
	}
	public String GetContraseña() {
		return contraseña;
	}
	public double GetDepositar() {
		return depositar;
	}
	public double GetRetirar() {
		return retirar;	
		}
	public double GetTransferir() {
		return transferir;
	}
	public double GetDineroActual() {
		return DineroActual;
	}
	
	public void SetDepositar(double depositar) {
		if (depositar>0) {
			DineroActual += depositar;
		}
	}
	public void SetRetirar(double retirar) {
		if (retirar>0 && retirar <= DineroActual) {
			DineroActual -= retirar;
		}
	}
	public void SetTransferir(double transferir, String nombre) {
		if (transferir>0 && transferir <= DineroActual ) {
			DineroActual -= transferir;
			JOptionPane.showMessageDialog(null, "Transfiriendo a "+ nombre+" $"+transferir);
		}
	}
	public void SetDineroActual(double DineroActual) {
		this.depositar= DineroActual+depositar;
	}
	public void SetContraseñal(String contraseña) {
		this.contraseña = contraseña;
	}
	
}
