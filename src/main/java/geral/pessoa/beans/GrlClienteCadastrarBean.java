/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.pessoa.beans;

//import ejbs.cache.LocBairrosCache;
//import ejbs.cache.LocComunasCache;
//import ejbs.cache.LocMunicipiosCache;
//import ejbs.cache.LocProvinciasCache;
//import ejbs.cache2.GrlPessoaCache2;
//import ejbs.entities.GrlConfiguracoes;
//import ejbs.entities.GrlPessoa;
//import ejbs.entities.LocBairros;
//import ejbs.entities.LocComunas;
//import ejbs.entities.LocMunicipios;
//import ejbs.entities.LocProvincias;
//import ejbs.facades.GrlConfiguracoesFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.mensagens.Mensagem;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@ViewScoped
public class GrlClienteCadastrarBean implements Serializable
{
    
    
//	@EJB
//	private GrlConfiguracoesFacade grlConfiguracoesFacade;
//
//	@Inject
//	private GrlPessoaCache2 pessoaFacade;
//    
//	@Inject
//	private LocProvinciasCache grlProvinciaFacade;
//    
//	@Inject
//	private LocMunicipiosCache grlMunicipioFacade;
//    
//	@Inject
//	private LocComunasCache grlComunasFacade;
//    
//	@Inject
//	private LocBairrosCache grlBairrosFacade;
//
//	private GrlPessoa pessoa;
//	private GrlConfiguracoes grlConfiguracoes;
//	private LocBairros enderecoResidencia, enderecoNaturalidade;

	/**
	 * *
	 * Local de Residencia
	 */
	private int idPaisResidencia;
	private int idProvinciaResidencia;
	private int idMunicipioResidencia;
	private int idComunaResidencia;
	private String valorLabelDistritoComunaResidencia = "Comunas";

//	private LocProvincias provinciaResidencia, provinciaNaturalidade;
//	private LocMunicipios municipioResidencia, municipioNaturalidade;
//	private LocComunas comunaResidencia, comunaNaturalidade;

	/**
	 * *
	 * Local de Naturalidade
	 */
	private int idPaisNaturalidade;
	private int idProvinciaNaturalidade;
	private int idMunicipioNaturalidade;
	private int idComunaNaturalidade;
	private String valorLabelDistritoComunaNaturalidade = "Comunas";

	/**
	 * Residencia
	 */
//	private List<LocProvincias> provinciasResidencia;
//	private List<LocMunicipios> municipiosResidencia;
//	private List<LocComunas> comunasResidencia;
//	private List<LocBairros> bairrosResidencia;

	/**
	 * Naturalidade
	 */
//	private List<LocProvincias> provinciasNaturalidade;
//	private List<LocMunicipios> municipiosNaturalidade;
//	private List<LocComunas> comunasNaturalidade;
//	private List<LocBairros> bairrosNaturalidade;

	/**
	 * Creates a new instance of LocPessoaCadastrarBean
	 */
	public GrlClienteCadastrarBean()
	{
	}

	/**
	 *
	 */
//	@PostConstruct
//	public void init()
//	{
//		pessoa = new GrlPessoa();
//		this.grlConfiguracoes = this.grlConfiguracoesFacade.find();
//		initSexoPreferencial();
//		initEnderecoPreferencialResidencia();
//		initEnderecoPreferencialNaturalidade();
//	}
//
//	/**
//	 *
//	 */
//	public void initSexoPreferencial()
//	{
//		this.pessoa.setFkSexo(this.grlConfiguracoes.getFkSexoPreferencial());
//	}
//
//	public void initEnderecoPreferencialResidencia()
//	{
//		this.enderecoResidencia = this.grlConfiguracoes.getFkEnderecoPreferencial();
//		this.pessoa.setFkResidenciaBairro(enderecoResidencia);
//		initListasResidencia();
//	}
//
//	public void initListasResidencia()
//	{
//		this.idComunaResidencia = this.enderecoResidencia.getFkLocComunas().getPkLocComunas();
//		this.bairrosResidencia = this.grlBairrosFacade.findAllByPkLocComunasOrderByNome(idComunaResidencia);
//		this.idMunicipioResidencia = this.enderecoResidencia.getFkLocComunas().getFkLocMunicipios().getPkLocMunicipios();
//		this.comunasResidencia = this.grlComunasFacade.findAllByPkLocMunicipioOrderByNome(idMunicipioResidencia);
//		//8888888888 actualizar etiqueta da combo comunas
//		actualizarValorLabelDistritoComunaResidencia();
//
//		this.idProvinciaResidencia = this.enderecoResidencia.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().getPkLocProvincias();
//		this.municipiosResidencia = this.grlMunicipioFacade.findAllByPkLocProvinciasOrderByNome(idProvinciaResidencia);
//
//		this.idPaisResidencia = this.enderecoResidencia.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().getFkLocPais().getPkLocPais();
////		this.provinciasResidencia = this.grlProvinciaFacade.findAllByPaisOrderByNome(idPaisResidencia);
//	}

//	public void initListasNaturalidade()
//	{
//		this.idComunaNaturalidade = this.enderecoNaturalidade.getFkLocComunas().getPkLocComunas();
//		this.bairrosNaturalidade = this.grlBairrosFacade.findAllByPkLocComunasOrderByNome(idComunaNaturalidade);
//
//		this.idMunicipioNaturalidade = this.enderecoNaturalidade.getFkLocComunas().getFkLocMunicipios().getPkLocMunicipios();
//		this.comunasNaturalidade = this.grlComunasFacade.findAllByPkLocMunicipioOrderByNome(idMunicipioNaturalidade);
//		actualizarValorLabelDistritoComunaNaturalidade();
//		this.idProvinciaNaturalidade = this.enderecoNaturalidade.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().getPkLocProvincias();
//		this.municipiosNaturalidade = this.grlMunicipioFacade.findAllByPkLocProvinciasOrderByNome(idProvinciaNaturalidade);
//
//		this.idPaisNaturalidade = this.enderecoResidencia.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().getFkLocPais().getPkLocPais();
////		this.provinciasNaturalidade = this.grlProvinciaFacade.findAllByPaisOrderByNome(idPaisNaturalidade);
//	}

	/**
	 *
	 */
//	public void initEnderecoPreferencialNaturalidade()
//	{
//		this.enderecoNaturalidade = this.grlConfiguracoes.getFkEnderecoPreferencial();
//		this.pessoa.setFkNaturalidadeMunicipio(enderecoNaturalidade.getFkLocComunas().getFkLocMunicipios());
//		initListasNaturalidade();
//	}

	/**
	 *
	 */
//	public void actualizarProvinciasResidencia()
//	{
//		//this.idPaisResidencia = this.pessoa.getFkResidenciaBairro().getFkLocComunas().getFkLocMunicipios().
//			//getFkLocProvincias().getFkLocPais().getPkLocPais();
//		//System.err.println("0: ClienteCadastrarBean.actualizarProvinciasResidencia()\tidPaisResidencia: " + idPaisResidencia);
//		//this.provinciasResidencia = this.grlProvinciaFacade.findAllByPaisOrderByNome(idPaisResidencia);
//		this.provinciaResidencia = this.provinciasResidencia.get(0);
//		this.enderecoResidencia.getFkLocComunas().getFkLocMunicipios().setFkLocProvincias(provinciaResidencia);
//		this.pessoa.setFkResidenciaBairro(enderecoResidencia);
//		//System.err.println("1: ClienteCadastrarBean.actualizarProvinciasResidencia()\tidPaisResidencia: " + idPaisResidencia);
//		actualizarMunicipiosResidencia();
//		//System.err.println("2: ClienteCadastrarBean.actualizarProvinciasResidencia()\tidPaisResidencia: " + idPaisResidencia);
//	}

	/**
	 *
	 */
//	public void actualizarProvinciasNaturalidade()
//	{
//		//this.idPaisNaturalidade = this.pessoa.getFkNaturalidadeMunicipio().getFkLocProvincias().getFkLocPais().getPkLocPais();
//		//this.provinciasNaturalidade = this.grlProvinciaFacade.findAllByPaisOrderByNome(idPaisNaturalidade);
//		this.provinciaNaturalidade = provinciasNaturalidade.get(0);
//
//		this.enderecoNaturalidade.getFkLocComunas().getFkLocMunicipios().setFkLocProvincias(provinciaNaturalidade);
//		this.pessoa.setFkNaturalidadeMunicipio(enderecoNaturalidade.getFkLocComunas().getFkLocMunicipios());
//		actualizarMunicipiosNaturalidade();
//	}

	/**
	 *
	 */
//	public void actualizarMunicipiosResidencia()
//	{
//		//System.err.println("0: ClienteCadastrarBean.actualizarMunicipios()");
//		//this.idProvinciaResidencia = this.pessoa.getFkResidenciaBairro().getFkLocComunas().getFkLocMunicipios().
//			//getFkLocProvincias().getPkLocProvincias();
//		//System.err.println("1: ClienteCadastrarBean.actualizarMunicipios()\tidProvinciaPreferencial: " + idProvinciaResidencia);
//		this.municipiosResidencia = this.grlMunicipioFacade.findAllByPkLocProvinciasOrderByNome(idProvinciaResidencia);
//		this.municipioResidencia = municipiosResidencia.get(0);
//		this.enderecoResidencia.getFkLocComunas().setFkLocMunicipios(municipioResidencia);
//		this.pessoa.setFkResidenciaBairro(enderecoResidencia);
//		//System.err.println("2: ClienteCadastrarBean.actualizarMunicipios()\tmunicipiosPreferenciais: " + municipiosResidencia);
//		actualizarComunasResidencia();
//	}

	/**
	 *
	 */
//	public void actualizarMunicipiosNaturalidade()
//	{
//		//System.err.println("0: ClienteCadastrarBean.actualizarMunicipios()");
////		this.idProvinciaNaturalidade = this.pessoa.getFkNaturalidadeMunicipio().getFkLocProvincias().getPkLocProvincias();
//		//System.err.println("1: ClienteCadastrarBean.actualizarMunicipios()\tidProvinciaPreferencial: " + idProvinciaNaturalidade);
//		this.municipiosNaturalidade = this.grlMunicipioFacade.findAllByPkLocProvinciasOrderByNome(idProvinciaNaturalidade);
//		this.municipioNaturalidade = municipiosNaturalidade.get(0);
//		this.enderecoNaturalidade.getFkLocComunas().setFkLocMunicipios(municipiosNaturalidade.get(0));
//		//System.err.println("2: ClienteCadastrarBean.actualizarMunicipios()\tmunicipiosPreferenciais: " + municipiosNaturalidade);
//		this.pessoa.setFkNaturalidadeMunicipio(enderecoNaturalidade.getFkLocComunas().getFkLocMunicipios());
//		actualizarComunasNaturalidade();
//	}

	/**
	 *
	 */
//	public void actualizarComunasResidencia()
//	{
//		//this.idMunicipioResidencia = this.pessoa.getFkResidenciaBairro().getFkLocComunas().getFkLocMunicipios().getPkLocMunicipios();
//		this.comunasResidencia = this.grlComunasFacade.findAllByPkLocMunicipioOrderByNome(idMunicipioResidencia);
//		actualizarValorLabelDistritoComunaResidencia();
//
//		this.comunaResidencia = this.comunasResidencia.get(0);
//		this.enderecoResidencia.setFkLocComunas(comunaResidencia);
//		this.pessoa.setFkResidenciaBairro(enderecoResidencia);
//		actualizarBairrosResdencia();
//	}

	/**
	 *
	 */
//	public void actualizarComunasNaturalidade()
//	{
////		this.idMunicipioNaturalidade = this.pessoa.getFkNaturalidadeMunicipio().getPkLocMunicipios();
//		this.comunasNaturalidade = this.grlComunasFacade.findAllByPkLocMunicipioOrderByNome(idMunicipioNaturalidade);
//		actualizarValorLabelDistritoComunaNaturalidade();
//		this.comunaNaturalidade = this.comunasNaturalidade.get(0);
//		this.enderecoNaturalidade.setFkLocComunas(comunaNaturalidade);
//		this.pessoa.setFkNaturalidadeMunicipio(enderecoNaturalidade.getFkLocComunas().getFkLocMunicipios());
//		actualizarBairrosNaturalidade();
//	}

//	public void actualizarValorLabelDistritoComunaResidencia()
//	{
//		valorLabelDistritoComunaResidencia = this.grlComunasFacade.actualizarValorLabelDistritoComuna(comunasResidencia);
//		if (this.valorLabelDistritoComunaResidencia.equalsIgnoreCase("Comuna/Distrito"))
//		{
//			grlComunasFacade.colocarPrefixo(comunasResidencia);
//		}
//	}

//	public void actualizarValorLabelDistritoComunaNaturalidade()
//	{
//		valorLabelDistritoComunaNaturalidade = this.grlComunasFacade.actualizarValorLabelDistritoComuna(comunasNaturalidade);
//		if (this.valorLabelDistritoComunaNaturalidade.equalsIgnoreCase("Comuna/Distrito"))
//		{
//			grlComunasFacade.colocarPrefixo(comunasNaturalidade);
//		}
//	}

	/**
	 *
	 */
//	public void actualizarBairrosResdencia()
//	{
//		//this.idComunaResidencia = this.pessoa.getFkResidenciaBairro().getFkLocComunas().getPkLocComunas();
//		this.bairrosResidencia = this.grlBairrosFacade.findAllByPkLocComunasOrderByNome(idComunaResidencia);
//
//		this.enderecoResidencia = bairrosResidencia.get(0);
//		this.pessoa.setFkResidenciaBairro(enderecoResidencia);
//	}

	/**
	 *
	 */
//	public void actualizarBairrosNaturalidade()
//	{
////		this.idComunaNaturalidade = this.pessoa.getFkNaturalidadeMunicipio().ge
////        this.idComunaNaturalidade = this.pessoa.getFkEnderecoNaturalidade().getFkLocComunas().getPkLocComunas();
//		this.bairrosNaturalidade = this.grlBairrosFacade.findAllByPkLocComunasOrderByNome(idComunaNaturalidade);
//
//		this.enderecoNaturalidade = bairrosNaturalidade.get(0);
////        this.pessoa.setFkEnderecoNaturalidade(enderecoNaturalidade);
//	}

	/*public int getIdPaisResidencia()
	{
		return idPaisResidencia;
	}

	public void setIdPaisResidencia(int idPaisResidencia)
	{
		this.idPaisResidencia = idPaisResidencia;
	}

	public LocBairros getEnderecoResidencia()
	{
		return enderecoResidencia;
	}

	public void setEnderecoResidencia(LocBairros enderecoResidencia)
	{
		this.enderecoResidencia = enderecoResidencia;
	}

	public LocBairros getEndereNaturalidade()
	{
		return enderecoNaturalidade;
	}

	public void setEndereNaturalidade(LocBairros enderecoNaturalidade)
	{
		this.enderecoNaturalidade = enderecoNaturalidade;
	}

	public List<LocProvincias> getProvinciasResidencia()
	{
		return provinciasResidencia;
	}

	public void setProvinciasResidencia(List<LocProvincias> provinciasResidencia)
	{
		this.provinciasResidencia = provinciasResidencia;
	}

	public List<LocMunicipios> getMunicipiosResidencia()
	{
		return municipiosResidencia;
	}

	public void setMunicipiosResidencia(List<LocMunicipios> municipiosResidencia)
	{
		this.municipiosResidencia = municipiosResidencia;
	}

	public List<LocComunas> getComunasResidencia()
	{
		return comunasResidencia;
	}

	public void setComunasResidencia(List<LocComunas> comunasResidencia)
	{
		this.comunasResidencia = comunasResidencia;
	}

	public List<LocBairros> getBairrosResidencia()
	{
		return bairrosResidencia;
	}

	public void setBairrosResidencia(List<LocBairros> bairrosResidencia)
	{
		this.bairrosResidencia = bairrosResidencia;
	}

	public GrlPessoa getGrlPessoa()
	{
		return pessoa;
	}

	public String getValorLabelDistritoComunaResidencia()
	{
		return valorLabelDistritoComunaResidencia;
	}

	public void setValorLabelDistritoComunaResidencia(String valorLabelDistritoComunaResidencia)
	{
		this.valorLabelDistritoComunaResidencia = valorLabelDistritoComunaResidencia;
	}

	public LocBairros getEnderecoNaturalidade()
	{
		return enderecoNaturalidade;
	}

	public String getValorLabelDistritoComunaNaturalidade()
	{
		return valorLabelDistritoComunaNaturalidade;
	}

	public List<LocProvincias> getProvinciasNaturalidade()
	{
		return provinciasNaturalidade;
	}

	public List<LocMunicipios> getMunicipiosNaturalidade()
	{
		return municipiosNaturalidade;
	}

	public List<LocComunas> getComunasNaturalidade()
	{
		return comunasNaturalidade;
	}

	public List<LocBairros> getBairrosNaturalidade()
	{
		return bairrosNaturalidade;
	}*/

//    private LocBairros getInstanciaBairros()
//    {
//        LocBairros bairro = new LocBairros();
//        bairro.setFkLocComunas(new LocComunas());
//        bairro.getFkLocComunas().setFkLocMunicipios(new LocMunicipios());
//        bairro.getFkLocComunas().getFkLocMunicipios().setFkLocProvincias(new LocProvincias());
//        bairro.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().setFkLocPais(new LocPais());
//
//        return bairro;
//    }
//	public boolean createRegister()
//	{
//		//Ainda não terminado
//		if (pessoa != null)
//		{
//			pessoaFacade.create(pessoa);
//			return true;
//		}
//		return false;
//	}

	/**
	 *
	 * @throws ExcepcaoCarregamentoTabelasExcel
	 */
//	public void createRegisterWithPersonalizedException()
//	{
//		if (!this.createRegister())
//		{
//			Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao criar Pessoa",
//				"Tentativa falhada ao criar Pessoa");
//		}
//	}
}
