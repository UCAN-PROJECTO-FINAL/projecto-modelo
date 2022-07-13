/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.ArAnoCurricular;
import ejbs.facades.ArAnoCurricularFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author david-salgueiro
 */
@Named(value = "arAnoCurricularCadastrarBean")
@SessionScoped
public class arAnoCurricularCadastrarBean implements Serializable {

    @EJB
    private ArAnoCurricularFacade anoCurricularFacade;

    private ArAnoCurricular anoCurricular;
//    private List<AnoCurricular> listaAnoCurricular;
    
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
//    private String pdf = "data:application/pdf;base64,JVBERi0xLjQKJeLjz9MKNCAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDYyMD4+c3RyZWFtCnic1VbJbtswEL3zK+aYAq1MUtTC9OTETZHCMdpYPRU9CDLtKJDMVJKdf8vntIf+RrmEXpRYNSAfWgjyjOiZebM8UvqBLhLkh0CjEJIZ+pCgL4jCJ71KAKtL//qEQlKiwRUBgiGZo7M3yb223ZpgyMpdJ8qwRxlQEnsk2Dgz7YyNSbVAZ1NZ5FnepL9/SZgJmIrlXaojY1i8Ev3bdyVnJjsVSl0qBIFHs2Bj3n50RiRQjhGFErGQG61ALGJeQM0Di/Ri6LHYqjGFDFmNGUutcW3tnDgDE0nJzEbn2pIwpoPoB8Kwc7KqsTQaNZbYQTonlYjLM0Pz/zDn6abdh3ngEw/rxLklAX1BgktZPlRynTb5WsLkCd4BxjQ4igbY4yGOYmjLHWJYpmyJQVUkRfcSBZwYrdhobZnZf8LA2SitLTMT0Jg8K/sic4CFU/aFnvs/l9LOWB/VEHSfdW/t3W6lF5jM6bNebL1t+w8RI1TZBr4XRI4X8T4tJrIUoLlRiEYeeSgcwiJx+BewJ7jwrnui+ESjsMMoN6ulOu4e8r7lML0Ru4A+iyqXs74wekK0o2mJqBtxisl0gWC1nzFXb5PxMA7xKSZEOxo3XqXL2bGvoM7xdKHcpMu7n31LUQcwD7soUNcyrUGUMK/EshHnwPrOivgQkQ0kaUPSYIDpgGJK+9aGO3FGaZPCcCHUpEpVmTzv3UoGUdRBCnmf6s+TW7HIawUH0+vhpXpNjb8OJ6Nh/1p5B1VGMlvZIh28zmSS1llult/3hKc8Urfnx67XvD1TuBJrUYm8knCC0VL9GRF2Qto9+BZSWR8F9gf40Th9CmVuZHN0cmVhbQplbmRvYmoKMSAwIG9iago8PC9Hcm91cDw8L1MvVHJhbnNwYXJlbmN5L1R5cGUvR3JvdXAvQ1MvRGV2aWNlUkdCPj4vQ29udGVudHMgNCAwIFIvVHlwZS9QYWdlL1Jlc291cmNlczw8L0NvbG9yU3BhY2U8PC9DUy9EZXZpY2VSR0I+Pi9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJXS9Gb250PDwvRjEgMiAwIFIvRjIgMyAwIFI+Pj4+L1BhcmVudCA1IDAgUi9NZWRpYUJveFswIDAgNjEyIDMxMl0+PgplbmRvYmoKNiAwIG9iagpbMSAwIFIvWFlaIDAgMzIyIDBdCmVuZG9iagoyIDAgb2JqCjw8L1N1YnR5cGUvVHlwZTEvVHlwZS9Gb250L0Jhc2VGb250L0hlbHZldGljYS9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKMyAwIG9iago8PC9TdWJ0eXBlL1R5cGUxL1R5cGUvRm9udC9CYXNlRm9udC9IZWx2ZXRpY2EtQm9sZC9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKNSAwIG9iago8PC9LaWRzWzEgMCBSXS9UeXBlL1BhZ2VzL0NvdW50IDEvSVRYVCgyLjEuNyk+PgplbmRvYmoKNyAwIG9iago8PC9OYW1lc1soSlJfUEFHRV9BTkNIT1JfMF8xKSA2IDAgUl0+PgplbmRvYmoKOCAwIG9iago8PC9EZXN0cyA3IDAgUj4+CmVuZG9iago5IDAgb2JqCjw8L05hbWVzIDggMCBSL1R5cGUvQ2F0YWxvZy9QYWdlcyA1IDAgUi9WaWV3ZXJQcmVmZXJlbmNlczw8L1ByaW50U2NhbGluZy9BcHBEZWZhdWx0Pj4+PgplbmRvYmoKMTAgMCBvYmoKPDwvTW9kRGF0ZShEOjIwMjIwMjI1MjEyMjQ2KzAxJzAwJykvQ3JlYXRvcihKYXNwZXJSZXBvcnRzIExpYnJhcnkgdmVyc2lvbiBudWxsKS9DcmVhdGlvbkRhdGUoRDoyMDIyMDIyNTIxMjI0NiswMScwMCcpL1Byb2R1Y2VyKGlUZXh0IDIuMS43IGJ5IDFUM1hUKT4+CmVuZG9iagp4cmVmCjAgMTEKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwNzAyIDAwMDAwIG4gCjAwMDAwMDA5ODAgMDAwMDAgbiAKMDAwMDAwMTA2OCAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDExNjEgMDAwMDAgbiAKMDAwMDAwMDk0NSAwMDAwMCBuIAowMDAwMDAxMjI0IDAwMDAwIG4gCjAwMDAwMDEyNzggMDAwMDAgbiAKMDAwMDAwMTMxMCAwMDAwMCBuIAowMDAwMDAxNDEzIDAwMDAwIG4gCnRyYWlsZXIKPDwvSW5mbyAxMCAwIFIvSUQgWzwzNzliOTA1Yjc3YTg2ODE4YmUxODA5MTQ4NTU3OTJjZj48OTE2YjI2N2ZlMTcyYWUxYTI3OGFhMjY5MzNlMjI3Yjg+XS9Sb290IDkgMCBSL1NpemUgMTE+PgpzdGFydHhyZWYKMTU4MAolJUVPRgo=\"";
    
    public arAnoCurricularCadastrarBean() {
    }
    
    
     @PostConstruct
    public void init()
    {
//        listaAnoCurricular = anoCurricularFacade.findAll();
        anoCurricular = new ArAnoCurricular();
    }
    
    //Metodos para formulário
    
    public void voltar()
    {

        this.btnSalvar = true;
        this.btnFormularioCadastro = false;
        this.tabListar = false;
        this.estado = false;

    }

    public void novo()
    {

        this.btnSalvar = false;
        this.btnFormularioCadastro = true;
        this.tabListar = false;
        this.estado = false;
        init();

    }

    public void btnListar()
    {

        this.tabListar = true;
        this.btnFormularioCadastro = false;
        this.btnSalvar = true;
        this.estado = false;
    }

    public void btnEditar()
    {

        this.estado = true;
        this.tabListar = false;

    }
    
    
    public String gravar()
    {
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.anoCurricular = new ArAnoCurricular();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ano Curricular já existe"));
               return "";
           }

            this.anoCurricularFacade.create(this.anoCurricular);
            init();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dados salvos com sucesso!"));
        } 
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar   

    public List<ArAnoCurricular> verificarRegistro() 
    {
        return this.anoCurricularFacade.findAnoCurricularByDescricao(this.anoCurricular.getDescricao());
    }
    
    public List<ArAnoCurricular> listaAnoCurricular() 
    {
        return this.anoCurricularFacade.findAllOrderedByPk();
    }
 
    public String alterar(ArAnoCurricular anoCurricular) 
    {

        btnEditar();
        this.anoCurricular = anoCurricular;
        System.out.println("anoCurricular: " + anoCurricular);
        return "";

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.anoCurricular = new ArAnoCurricular();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ano Curricular já existe"));
                return ;
            }

            this.anoCurricularFacade.edit(this.anoCurricular);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
    
    
    public void eliminar(ArAnoCurricular anoCurricular)
    {
        try
        {
            anoCurricularFacade.remove(anoCurricularFacade.find(anoCurricular.getPkAnoCurricular()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Ano Curricular, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }
    
    
    public boolean isBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(boolean btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public boolean isBtnFormularioCadastro() {
        return btnFormularioCadastro;
    }

    public void setBtnFormularioCadastro(boolean btnFormularioCadastro) {
        this.btnFormularioCadastro = btnFormularioCadastro;
    }

    public boolean isTabListar() {
        return tabListar;
    }

    public void setTabListar(boolean tabListar) {
        this.tabListar = tabListar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArAnoCurricular getAnoCurricular() {
        return anoCurricular;
    }

    public void setAnoCurricular(ArAnoCurricular anoCurricular) {
        this.anoCurricular = anoCurricular;
    }

   


}
