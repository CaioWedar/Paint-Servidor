package bd;

import bd.core.*;


public class BDOracle
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando = new MeuPreparedStatement (
            		"oracle.jdbc.driver.OracleDriver",
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system", "Slip1997");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}
