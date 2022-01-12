package BancoJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BancoJDBC {
private Connection con;
private Statement stmt;

public BancoJDBC() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("driver encontrado");
		}
	catch (ClassNotFoundException e) {
		System.out.println("driver nao encontrado!" + e);
		System.out.println("Error: "+ e.getMessage());
	}

	String url = "jdbc:mysql://localhost:3306/banco";
	String user = "root";
	String password = "marcelo";
	try{
	con=DriverManager.getConnection(url,user,password);
	stmt = con.createStatement();
	}
	catch(SQLException e){
	System.out.println("Error: "+ e.getMessage());
	}
	//inserirRegistros();
	//listarRegistros();
}


private void inserirRegistros(){
try{
stmt.executeUpdate
("INSERT INTO Empregado VALUES (4,'Evandro','6291376654','6780.00')");
}
catch(SQLException e){ 
	System.out.println("Error: "+ e.getMessage());
}
}

public static void main(String[] args) {
BancoJDBC bancoJDBC = new BancoJDBC();
Scanner leia = new Scanner(System.in);
	
	int matricula, matriculaExcluida, matriculaAlterar, opcao;
	String nome, telefone, salario, alterarSal;

System.out.println(" 1-inserir\n 2-alterar\n 3-consultar\n 4-excluir\n 5-sair");

System.out.println("digite sua opção: ");
 opcao = leia.nextInt();

switch(opcao){
	case 1:
		System.out.println("digite a matricula para inserir: ");
		matricula = leia.nextInt();
		System.out.println("Digite o nome para inserir: ");
		nome = leia.next();
		//leia.nextLine();
		System.out.println("Digite o telefone para inserir: ");
		telefone = leia.next();
		System.out.println("Digite o salario para inserir: ");
		salario = leia.next();
		bancoJDBC.inserirRegistros2(matricula, nome, telefone, salario);
		
	break;
	case 2:
		System.out.println("digite a matricula para alterar o salario: ");
		matriculaAlterar = leia.nextInt();
		System.out.println("digite o novo salario: ");
		alterarSal = leia.next();
		bancoJDBC.alterarRegistros(alterarSal, matriculaAlterar);
	break;
	case 3:
		bancoJDBC.listarRegistros();
	break;	
	case 4:
		System.out.println("digite a matricular para excluir: ");
		matriculaExcluida = leia.nextInt();
		bancoJDBC.apagarRegistros(matriculaExcluida);
	break;
	case 5:
		System.out.println("Saindo....");
	break;	
}

}

private void listarRegistros(){
try{
ResultSet rs;
rs = stmt.executeQuery("SELECT * FROM empregado");
while ( rs.next() ) 
{ int matricula = rs.getInt("matricula");
String nome = rs.getString("nome");
String telefone = rs.getString("telefone");
float salario = rs.getFloat("salario");
System.out.println(matricula + "\t" + nome + "\t" +
telefone + "\t" + salario);
}
}catch(SQLException e){
System.out.println("Erro: "+ e.getMessage());
}
}

private void inserirRegistros2(int mat, String n, String tel, String sal){
	try{
	stmt.executeUpdate("INSERT INTO empregado VALUES("+mat+",'"+n+"','"+tel+"','"+sal+"')");
	}catch(SQLException e){
	System.out.println("Erro: "+ e.getMessage());
	}
}

private void alterarRegistros(String sal, int mat){
try{
stmt.executeUpdate("UPDATE Empregado SET salario = '"+sal+"' WHERE matricula="+mat+"");
}catch(SQLException e){
System.out.println("Erro: "+ e.getMessage());
}
}
private void apagarRegistros(int mat){
try{
stmt.executeUpdate("DELETE FROM Empregado WHERE matricula="+mat+"");
}catch(SQLException e){
System.out.println("Erro: "+ e.getMessage());
}
}
}
