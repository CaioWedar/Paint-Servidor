import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import bd.daos.Desenhos;

public class PedidoSalvamento extends Comunicado
{
	private static final long serialVersionUID = 7323456804627089171L;
	private static int codigo = 1;

	public static synchronized String createID()
	{
	    return String.valueOf(codigo++);
	}  
	
    private Desenho desenho;

    public PedidoSalvamento (Desenho des)
    {
        this.desenho = des;
    }

    public Desenho getDesenho ()
    {
        return this.desenho;
    }
    
    public void salvar()
    {
    	String nome = desenho.getNome();
        try
        {
          if (Desenhos.cadastrado(nome))
          {
            atualizar();
            return;
          }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
      try
      {
    	  	String des = desenho.toString();
	      	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	      	Date data = new Date();
	  	    bd.dbos.Desenho desenho = new bd.dbos.Desenho(codigo, nome, des, formatter.format(data), formatter.format(data));
	  	    Desenhos.incluir(desenho);
	      }     
	      catch (Exception ex)
      {
          ex.printStackTrace();
      }
    }


    
	  public void atualizar() throws Exception
	  {
		  String nome = desenho.getNome();
		  String cria = desenho.getDataCriacao();
		   
	    try {
	    	String des = desenho.toString();
	    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    	Date data = new Date();
            bd.dbos.Desenho desenho =new bd.dbos.Desenho(codigo, nome, des, cria, formatter.format(data));
	    	Desenhos.alterar(desenho);
	    } 
	    catch (Exception ex) 
	    {
	      ex.printStackTrace();
	    }
	  }  
}