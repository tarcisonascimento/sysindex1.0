package br.com.rheacaoscannerpro.classes;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;


// a classe abaixo verifica se o jinternalframe ja esta aberto e se estiver ele so joga pra frente nao abre outro
//https://www.youtube.com/watch?v=aKWXWtLacAY

public class GerenciadorDeJanelas {
    
    private static JDesktopPane jDesktopPane;

    public GerenciadorDeJanelas(JDesktopPane jDesktopPane) {
        
        GerenciadorDeJanelas.jDesktopPane = jDesktopPane;
    }
    public void abrirJanelas (JInternalFrame jInternalFrame){
        if (jInternalFrame.isVisible()){
        
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
        }else{
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
        
        }
    
    
    
    }
    
    
}
