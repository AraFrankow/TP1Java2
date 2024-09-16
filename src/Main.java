import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		String nombre;
		do {
			nombre = JOptionPane.showInputDialog("Ingrese el nombre de cuenta");
		} while (!ValidarNombre(nombre));
		String contra;
		do {
			contra = JOptionPane.showInputDialog("Ingrese la contraseña de su cuenta (minimo 6 caracteres, 1 minuscula y 1 número)");
		} while (!ValidarContrasena(contra));
		Cuenta yo = new Cuenta(nombre, contra);
		String[] acciones = {
				"Depositar", "Retirar", "Transferir", "Dinero actual", "Cambiar contraseña","Salir"
		};
		String accion;
		double depositar, retirar, transferir;
		do {
			accion = (String)JOptionPane.showInputDialog(null, "Bienvenido "+nombre+", que accion quiere hacer?", null, 0, null, acciones, acciones[0]);
			switch (accion) {
			case "Depositar":
				depositar = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el dinero que quiera depositar"));
				if (depositar<=0) {
					do {
						depositar = Double.parseDouble(JOptionPane.showInputDialog("Error, ingrese nuevamente la cantidad que quiera depositar"));
					} while (depositar<=0);
				}
				yo.SetDepositar(depositar);
				JOptionPane.showMessageDialog(null, "Depositando $"+depositar+"...", null, JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("img/depositando.png")));
				break;
				
			case "Retirar":
				contra = JOptionPane.showInputDialog("Ingrese la contraseña de su cuenta");
				if (contra.equals(yo.GetContraseña())) {
					retirar = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el dinero que quiera retirar"));
					if (retirar<=0 || retirar>yo.GetDineroActual()) {
						do {
							retirar = Double.parseDouble(JOptionPane.showInputDialog("Error, ingrese nuevamente la cantidad que quiera retirar"));
						} while (retirar<=0 || retirar>yo.GetDineroActual());
					}	
					yo.SetRetirar(retirar);
					JOptionPane.showMessageDialog(null, "Retirando...", null, JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("img/retirando.gif")));
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta, no puede hacer esta accion");
					break;
				}
				
			case "Transferir":
				contra = JOptionPane.showInputDialog("Ingrese la contraseña de su cuenta");
				if (contra.equals(yo.GetContraseña())) {
					transferir = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el dinero que quiera transferir"));
					if (transferir<=0 || transferir>yo.GetDineroActual()) {
						do {
							transferir = Double.parseDouble(JOptionPane.showInputDialog("Error, ingrese nuevamente la cantidad que quiera transferir"));
						} while (transferir<=0 || transferir>yo.GetDineroActual());
						
					}
					String dni;
					do {
						dni = JOptionPane.showInputDialog("Ingrese el DNI de la persona a la cual le querés transferir");
					} while (!ValidarDni(dni));
					if (ValidarDni(dni)==false) {
						JOptionPane.showMessageDialog(null, "El DNI no es correcto");
						break;
					} else {
						String nombre2 = JOptionPane.showInputDialog("Ingrese el nombre de la persona");
						yo.SetTransferir(transferir, nombre2);
						JOptionPane.showMessageDialog(null, "Transfiriendo...", null, JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("img/transfiriendo.gif")));
						break;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta, no puede hacer esta accion");
					break;
				}
				
			case "Dinero actual":
				JOptionPane.showMessageDialog(null, "Su dinero actual en la cuenta es: $"+ yo.GetDineroActual(), null, JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("img/ver.gif")));
				
				break;
				
			case "Cambiar contraseña":
				String cambiar = JOptionPane.showInputDialog("Ingrese su nombre de usuario");
				if (cambiar.equals(yo.GetNombre())) {
					String contraNueva;
					do {
						contraNueva = JOptionPane.showInputDialog("Ingrese su nueva contraseña");
					} while (!ValidarContrasena(contraNueva));
					JOptionPane.showMessageDialog(null, "Cambiando contraseña...", null, JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("img/contra.gif")));
					yo.SetContraseñal(contraNueva);
				}else {
					JOptionPane.showMessageDialog(null, "Nombre de usuario incorrecto");
				}
				
				break;
			case "Salir":
				JOptionPane.showMessageDialog(null, "Saliendo...", null, JOptionPane.DEFAULT_OPTION, new ImageIcon(Main.class.getResource("img/salir.gif")));
				break;

			default:
				break;
			}
		} while (!accion.equals("Salir"));
		
	}
	
	public static boolean ValidarContrasena(String contra) {
		boolean mayus = false;
		boolean minus = false;
		boolean num = false;
		
		if (contra.length()>=6) {
			for (int i = 0; i < contra.length(); i++) {
				if (Character.isUpperCase(contra.charAt(i))) {
					mayus=true;
				}
				if (Character.isLowerCase(contra.charAt(i))) {
					minus=true;
				}
				if (Character.isDigit(contra.charAt(i))) {
					num=true;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "La contraseña debe tener 6 caracteres");
			return false;
		}
		if (mayus && minus && num) {
			JOptionPane.showMessageDialog(null, "Es correcta");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "No cumple con lo pedido");
			return false;
		}
	}
	public static boolean ValidarDni(String dni) {
		boolean letra = false;
		if (dni.length()<7 || dni.length()>8) {
			return false;
		} else {
			for (int i = 0; i < dni.length(); i++) {
				if (Character.isLetter(dni.charAt(i))) {
					letra=true;
				}
			}
			if (letra==true) {
				JOptionPane.showMessageDialog(null, "No puede tener letras el DNI");
				return false;
			} else {
				return true;
			}
		}	
		
	}
	public static boolean ValidarNombre(String nombre) {
		boolean numero = false;
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Está vacio el nombre");
			return false;
		} else {
			for (int i = 0; i < nombre.length(); i++) {
				if (Character.isDigit(nombre.charAt(i))) {
					numero=true;
				}
			}
			if (numero==true) {
				JOptionPane.showMessageDialog(null, "No puede tener numeros un nombre");
				return false;
			} else {
				return true;
			}
		}
	}

}
