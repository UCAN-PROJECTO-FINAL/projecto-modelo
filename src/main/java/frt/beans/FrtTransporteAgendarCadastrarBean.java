/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;


import utils.Defs;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
//import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import ejbs.cache.AgendarTransporteCache;
import ejbs.entities.FrtTransporteConfiguracoes;
import ejbs.entities.FrtTransporteAgendar;
import ejbs.entities.FrtTransporteTipoAgendamento;
import java.io.Serializable;
import javax.inject.Named;
import ejbs.entities.PtTransporte;
import ejbs.facades.FrtTransporteConfiguracoesFacade;
import ejbs.facades.PtTransporteFacade;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "transporteAgendarCadastrarBean")
//@ViewScoped
@ViewScoped
public class FrtTransporteAgendarCadastrarBean implements Serializable
{
    
    @EJB
    private PtTransporteFacade ptTransporteFacade;

    
    @Inject
    private AgendarTransporteCache agendarTransporteCache;
    
    @EJB
    FrtTransporteConfiguracoesFacade frtConfiguracoesFacade;

    
    
    private PtTransporte  selectedPtTransporte;
    private FrtTransporteConfiguracoes  frtConfiguracoes;
    private FrtTransporteTipoAgendamento tbTipoAgendamento;
    private FrtTransporteAgendar frAgendarTransporte;
    private List<PtTransporte> selectedTransporte;
    private Date dataInicio, dataFim;
    //private PtTransporte selectedPtTransporte;
    private DiaNode copyDia;
    private List<DiaNode> allDias, selectedDias, targetDias;
    private MesNode currentMonth;
    private List<MesNode> allMeses, selectedMeses;
    private List<HoraNode> selectedHoras;
    private boolean dataFimDisabled = true, seleccaoSemanal,isAllDaysSelected=false;
    private String selectAllDiasLabel;
    
    private int codigotbTipoAgendamento = 0;
    

    /**
     * Creates a new instance of TransporteAgendarCadastrarBean
     */
    public FrtTransporteAgendarCadastrarBean() 
    { 
       // tbTipoAgendamento = tbTipoAgendamento;
        
    }
    
    @PostConstruct
    public void init()
    {

        allMeses = new ArrayList<>();
        selectedMeses = new ArrayList<>();
        selectedDias = new ArrayList<>();
        selectedHoras = new ArrayList<>();
        targetDias = new ArrayList<>();
        selectedTransporte = new ArrayList<>();
        tbTipoAgendamento = new FrtTransporteTipoAgendamento();
        selectAllDiasLabel = "selecionar todos";
        frAgendarTransporte = new FrtTransporteAgendar();
        //selectedPtTransporte
        
        initConfiguracao();
    }
    
    public void initConfiguracao()
    {
        frtConfiguracoes = frtConfiguracoesFacade.find();
        codigotbTipoAgendamento = frtConfiguracoes.getFkFrtTipoAgendamento().getPkTipoAgendamento();
        
    }
    
    
    
    
    
    public void adicionar(ActionEvent event)
    {

        int codigo = Integer.parseInt(event.getComponent().getAttributes().get("codigoTransporte").toString());
        System.err.println("Entrou seleccionarTransporte: " + codigo);
        this.selectedPtTransporte = ptTransporteFacade.find(codigo);
        findTransporte();
          
    }
    
    
    
    
    public void changeMonths()
    {
        clearMonthsSelections();
        allMeses.clear();

        Calendar calendarInicio = Calendar.getInstance(), calendarFim = Calendar.getInstance();
        calendarInicio.setTime(dataInicio);

        int current_ano = calendarInicio.get(Calendar.YEAR), max_ano;
        int current_month = calendarInicio.get(Calendar.MONTH), max_mes = 12;
        int current_day = calendarInicio.get(Calendar.DAY_OF_MONTH), max_days;
        Date data, date_day;

        if (dataFim != null && dataFim.compareTo(dataInicio) > 0)
        {
            data = dataFim;
            calendarFim.setTime(data);
            max_ano = calendarFim.get(Calendar.YEAR);
            if (calendarInicio.get(Calendar.YEAR) == calendarFim.get(Calendar.YEAR))
            {
                max_mes = calendarFim.get(Calendar.MONTH);
            }

        }
        else
        {
            data = new Date(calendarInicio.get(Calendar.YEAR) + 1, calendarInicio.get(Calendar.MONTH), calendarInicio.get(Calendar.DAY_OF_MONTH));
            max_ano = current_ano + 1;
            calendarFim.setTime(data);
        }

        System.err.println("0: EstruturaLogicaFsicaCarregar.changeMonths() dataFim:" + dataFim);
        Calendar cld = Calendar.getInstance();
        MesNode mes;
        String dateFormat;
        boolean isInDB;

        for (int i_ano = current_ano; i_ano <= max_ano; i_ano++)
        {
            if (i_ano == max_ano)
            {
                max_mes = calendarFim.get(Calendar.MONTH);
            }

            for (int i_mes = current_month; i_mes <= max_mes; i_mes++)
            {
                cld.set(i_ano, i_mes, current_day);
                dateFormat = new SimpleDateFormat("MMM").format(cld.getTime());
                System.err.println("frt.beans.CadastrarAgendaTransporteBean.changeMonths():" +cld.getTime());
                System.err.println("frt.beans.CadastrarAgendaTransporteBean.changeMonths():" +selectedPtTransporte);
                isInDB = agendarTransporteCache.isMesSelectedByOthersTransport(cld.getTime(), selectedPtTransporte);
                mes = new MesNode(dateFormat + "/" + i_ano, i_mes, i_ano, cld, isInDB, allMeses.size());


                if (i_ano == max_ano)
                {
                    max_days = calendarFim.get(Calendar.DAY_OF_MONTH);
                }
                else
                {
                    max_days = mes.data.getActualMaximum(Calendar.DAY_OF_MONTH);
                }

                loadDias(mes, max_days);
                mes.setCor(agendarTransporteCache.getMesColorTransporte(cld, selectedPtTransporte));
                allMeses.add(mes);

                current_day = 1;
            }

            current_month = 0;
        }

        currentMonth = allMeses.get(0);
        allDias = currentMonth.dias;
        System.err.println("0: EstruturaLogicaFsicaCarregar.changeMonths()" + allMeses.get(0).nome + "...." + allMeses.get(allMeses.size() - 1).nome);
    }
    
    
    public void loadDias(MesNode mes, int max_days)
    {
        //int max_days = mes.data.getActualMaximum(Calendar.DAY_OF_MONTH);
        int hour;
        HoraNode horaNode;
        Calendar cld = Calendar.getInstance();
        cld.setTime(mes.data.getTime());
        String dateFormat, horaStr, date_full;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date d1;
        boolean isInDB;
        for (int i_day = mes.data.get(Calendar.DAY_OF_MONTH); i_day <= max_days; i_day++)
        {
            cld.set(Calendar.DAY_OF_MONTH, i_day);
            dateFormat = new SimpleDateFormat("EE").format(cld.getTime());
            isInDB = agendarTransporteCache.isDiaSelectedByOthersTransporte(cld, selectedPtTransporte);
            DiaNode day = new DiaNode(dateFormat + "/" + i_day, i_day, isInDB);
            day.setCor(agendarTransporteCache.getDiaColorTransporte(cld, selectedPtTransporte));

            for (hour = 0; hour < 24; hour++)
            {
                cld.set(Calendar.HOUR_OF_DAY, hour);

                horaStr = hour > 9 ? ("" + hour) : ("0" + hour);
                try
                {
                    date_full = cld.get(Calendar.YEAR) + "-" + (cld.get(Calendar.MONTH) + 1) + "-" + cld.get(Calendar.DAY_OF_MONTH);

                    d1 = (java.util.Date) format.parse(date_full + " " + horaStr + ":00:00");
                    isInDB = agendarTransporteCache.isHoraSelectedByOthersTransporte(cld, selectedPtTransporte);
                    day.horas.add(new HoraNode(hour, cld.getTime(), isInDB, agendarTransporteCache.getHoraColorTransporte(cld, selectedPtTransporte)));
                }
                catch (ParseException ex)
                {
                    Logger.getLogger(FrtTransporteAgendarCadastrarBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            mes.dias.add(day);
        }

    }
    
    public boolean disableOrEnable(boolean isSelected)
    {
        if (isSelected)
        {
            return true;
        }
        else
        {
            return false;
        }
        //return false;
    }
     
    public void selectUnselectMonth(int idMes)
    {
        if (allMeses.get(idMes).isSelected())
        {
            allMeses.get(idMes).setSelected(false);
            selectedMeses.remove(allMeses.get(idMes));
        }
        else
        {
            allMeses.get(idMes).setSelected(true);
            selectedMeses.add(allMeses.get(idMes));
        }

    }

    
    public void clearHoursSelections()
    {
        for (HoraNode hora : selectedHoras)
        {
            hora.setSelected(false);
        }
        selectedHoras.clear();
    }
    
    public void clearDaysSelections()
    {
        for (DiaNode dia : selectedDias)
        {
            dia.setSelected(false);
        }
        selectedDias.clear();
        clearHoursSelections();
    }
    
    
    public void clearMonthsSelections()
    {
        for (MesNode mes : selectedMeses)
        {
            mes.setSelected(false);
        }
        selectedMeses.clear();
        clearDaysSelections();
    }
    
    public void changeStatusDataFim()
    {
        if (dataFimDisabled)
        {
            dataFimDisabled = false;
        }
        else
        {
            dataFimDisabled = true;
        }
    }
    
    public void changeDays(int month)
    {
        currentMonth = allMeses.get(month);
        allDias = currentMonth.dias;
    }
    
    public void replicarDias()
    {
        System.err.println("0: EstruturaLogicaFsicaCarregar.replicarDias() copyDia:" + copyDia);
        System.err.println("0: EstruturaLogicaFsicaCarregar.replicarDias() targetDias:" + targetDias);
        int hour;
        for (DiaNode dia : targetDias)
        {
            for (hour = 0; hour < 24; hour++)
            {
                if (copyDia.horas.get(hour).isSelected())
                {
                    if (!dia.horas.get(hour).isSelectedInDB())
                    {
                        dia.horas.get(hour).setSelected(true);
                        dia.horas.get(hour).setCor(Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE);
                    }
                }
            }
            dia.setSelected(true);
            selectedDias.add(dia);
        }
    }
    
    public void unSelectAllDays()
    {
        targetDias.clear();
    }
    
    private void selectAllDays()
    {
        for (DiaNode dia : allDias)
        {
            if (!dia.isSelectedInDB && dia != copyDia)
            {
                dia.setCheckboxSelected(true);
                dia.setCor(Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE);
                targetDias.add(dia);
            }
        }
    }
    
    public void selectUnselectAllDays()
    {
        if(isAllDaysSelected)
            unSelectAllDays();
        else
            selectAllDays();
        
    }
    
    
    private void unselecetAllExpect(DiaNode dia)
    {
        for (DiaNode dn : this.allDias)
        {
            if (dn.getNumero() == dia.getNumero())
            {
                continue;
            }
            dn.setRadioSelected(false);
        }
    }
    
    public void selectUnselectCopyDia(DiaNode dia)
    {
        boolean selected = dia.isRadioSelected();
        if (selected)
        {
            unselecetAllExpect(dia);
        }
        this.copyDia = dia;
        System.err.println("0: EstruturaLogicaFsicaCarregar.selectUnselectCopyDia()");
    }
    
    public void selectUnselectTargetDias(DiaNode dia)
    {
        if (targetDias.contains(dia))
        {

            targetDias.remove(dia);
        }
        else
        {
            targetDias.add(dia);
        }
        System.err.println("0: EstruturaLogicaFsicaCarregar.selectTarCopyDia()");
    }
    
    
    
    public void selectUnselecDay(int dia)
    {
        DiaNode diaNode = allDias.get(dia);
        List<HoraNode> horaNodeList = diaNode.horas;

        for (HoraNode horaNode : horaNodeList)
        {
            if (!horaNode.isSelected())
            {
                horaNode.setSelected(true);
                selectedHoras.add(horaNode);
            }
            else
            {
                horaNode.setSelected(false);
                selectedHoras.remove(horaNode);
            }
        }

        if (diaNode.isSelected())
        {
            diaNode.setSelected(false);
            getSelectedDias().remove(diaNode);
        }
        else
        {
            diaNode.setSelected(true);
            getSelectedDias().add(diaNode);
        }

    }
    
    
     public void selectUnselectHora(int dia, int hora)
    {
        DiaNode diaNode = allDias.get(dia);
        HoraNode horaNode = diaNode.horas.get(hora);
        if (horaNode.isSelected())
        {
            horaNode.setSelected(false);
            horaNode.setCor("green");
            selectedHoras.remove(horaNode);
        }
        else
        {
            horaNode.setSelected(true);
            horaNode.setCor("blue");
            selectedHoras.add(horaNode);
        }
    }
     
    public void findTransporte()
    {
        List<PtTransporte> list = agendarTransporteCache.findByTransportePk(selectedPtTransporte.getPkPtTransporte());
        

        selectedTransporte = list;
    }
     
     
     
     /*                              METODO SALVAR                                   */
     
     
  
    /* 
    public void salvar()
    {
       

        if (!selectedMeses.isEmpty())
        {
            saveFromMonth();
            System.out.println("ENTROU selectedMeses SUCESSO !!!");

        }
        else if (!selectedDias.isEmpty())
        {
            saveFromDays();
            System.out.println("ENTROU selectedDias SUCESSO !!!");
        }
        else 
        {
            saveFromHours();
            System.out.println("ENTROU saveFromHours() SUCESSO !!!");
           
        }
        
        
       
      

    }*/
    
    // NOVO
    
    public void salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!selectedMeses.isEmpty())
        {
            saveFromMonth();
            System.out.println("ENTROU selectedMeses  SUCESSO !!! ");

        }
        else if (!selectedDias.isEmpty())
        {
            saveFromDays();
            System.out.println("ENTROU selectedDias  SUCESSO !!! ");
        }
        else
        {
            saveFromHours();
            System.out.println("ENTROU saveFromHours() SUCESSO !!! ");
        }
        
        
        System.err.println("0: TransporteAgendarCadastrarBean.salvar()");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transporte Agendado Com Sucesso !!!", ""));
    }

    public void saveFromMonth()
    {
       
        FrtTransporteAgendar agendarTransporte;
        String horaStr, dateStr;
        Calendar cld = Calendar.getInstance();
        Date data;
        SimpleDateFormat format;
        System.err.println("selectedTransporte"+selectedTransporte);
        System.err.println("selectedTransporte"+selectedTransporte);
        for (PtTransporte transporte : selectedTransporte)
        {

            for (MesNode mes : selectedMeses)
            {
                for (DiaNode dia : mes.dias)
                {
                    for (HoraNode horaNode : dia.horas)
                    {
                        agendarTransporte = new FrtTransporteAgendar();
                        agendarTransporte.setData(horaNode.data);
                        agendarTransporte.setIsAgendado(true);
                        format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        agendarTransporte.setFkTransporte(transporte);
                        agendarTransporte.setFkTipoAgendamento(tbTipoAgendamento);
                        
                        tbTipoAgendamento.setPkTipoAgendamento(codigotbTipoAgendamento);
                        //agendarTransporte.setFkTipoAgendamento(tbTipoAgendamento);
                           
                        
                        try
                        {

                            horaStr = horaNode.getHora() > 9 ? ("" + horaNode.getHora()) : ("0" + horaNode.getHora());

                            cld.setTime(horaNode.getData());
                            dateStr = cld.get(Calendar.YEAR) + "-" + cld.get(Calendar.MONTH) + "-" + cld.get(Calendar.DAY_OF_MONTH);
                            data = (java.util.Date) format.parse(dateStr + " " + horaStr + ":00:00");

                            Time ppstime = new java.sql.Time(data.getTime());
                            agendarTransporte.setHora(ppstime);
                            //hora.setFkEstruturaLogicaFisica(estruturaLogicaFisica);

                            agendarTransporteCache.create(agendarTransporte);
                        }
                        catch (ParseException ex)
                        {
                            Logger.getLogger(FrtTransporteAgendarCadastrarBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }

        }
    }

    public void saveFromDays()
    {
        FrtTransporteAgendar agendarTransporte;
        String horaStr, dateStr;
        Calendar cld = Calendar.getInstance();
        Date data;
        SimpleDateFormat format;
        System.err.println("selectedTransporte"+selectedTransporte);
        System.err.println("selectedTransporte"+selectedTransporte);
        for (PtTransporte transporte : selectedTransporte)
        {
            for (DiaNode diaNode : selectedDias)
            {
                for (HoraNode horaNode : selectedHoras)
                {
                    agendarTransporte = new FrtTransporteAgendar();
                    agendarTransporte.setData(horaNode.data);
                    agendarTransporte.setIsAgendado(true);
                    format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    agendarTransporte.setFkTransporte(transporte);
                    tbTipoAgendamento.setPkTipoAgendamento(codigotbTipoAgendamento);
                    agendarTransporte.setFkTipoAgendamento(tbTipoAgendamento);

                    try
                    {
                        horaStr = horaNode.getHora() > 9 ? ("" + horaNode.getHora()) : ("0" + horaNode.getHora());

                        cld.setTime(horaNode.getData());
                        dateStr = cld.get(Calendar.YEAR) + "-" + cld.get(Calendar.MONTH) + "-" + cld.get(Calendar.DAY_OF_MONTH);
                        data = (java.util.Date) format.parse(dateStr + " " + horaStr + ":00:00");

                        Time ppstime = new java.sql.Time(data.getTime());
                        agendarTransporte.setHora(ppstime);

                        agendarTransporteCache.create(agendarTransporte);
                    }
                    catch (ParseException ex)
                    {
                        Logger.getLogger(FrtTransporteAgendarCadastrarBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    }

    public void saveFromHours()
    {
        FrtTransporteAgendar agendarTransporte;
        String horaStr, dateStr;
        Calendar cld = Calendar.getInstance();
        Date data;
        SimpleDateFormat format;
        System.err.println("saveFromHours////"+selectedTransporte);
        System.err.println("selectedTransporte"+selectedTransporte);
        System.err.println("selectedTransporte"+selectedTransporte);
        for (PtTransporte transporte : selectedTransporte)
        {
            for (HoraNode horaNode : selectedHoras)
            {
                agendarTransporte = new FrtTransporteAgendar();
                agendarTransporte.setData(horaNode.data);
                agendarTransporte.setIsAgendado(true);
                format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                agendarTransporte.setFkTransporte(transporte);
                tbTipoAgendamento.setPkTipoAgendamento(codigotbTipoAgendamento);
                agendarTransporte.setFkTipoAgendamento(tbTipoAgendamento);

                try
                {
                    horaStr = horaNode.getHora() > 9 ? ("" + horaNode.getHora()) : ("0" + horaNode.getHora());

                    cld.setTime(horaNode.getData());
                    dateStr = cld.get(Calendar.YEAR) + "-" + cld.get(Calendar.MONTH) + "-" + cld.get(Calendar.DAY_OF_MONTH);
                    data = (java.util.Date) format.parse(dateStr + " " + horaStr + ":00:00");

                    Time ppstime = new java.sql.Time(data.getTime());
                    agendarTransporte.setHora(ppstime);

                    agendarTransporteCache.create(agendarTransporte);
                }
                catch (ParseException ex)
                {
                    Logger.getLogger(FrtTransporteAgendarCadastrarBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }


    
    /*                         Metodos Get e Set                                      */

    

   
    
    
    /* Metodos Get e Set da Data e Hora*/
    
    

    
    //--------------------------------------------------------------------------
//    public ELogicaNode getRootELogica()
//    {
//        return rootELogica;
//    }
//
//    public void setRootELogica(ELogicaNode rootELogica)
//    {
//        this.rootELogica = rootELogica;
//    }
//
//    public EFisicaNode getRootEFisica()
//    {
//        return rootEFisica;
//    }
//
//    public void setRootEFisica(EFisicaNode rootEFisica)
//    {
//        this.rootEFisica = rootEFisica;
//    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(Date dataFim)
    {
        this.dataFim = dataFim;
    }

    public boolean isDataFimDisabled()
    {
        return dataFimDisabled;
    }

    public void setDataFimDisabled(boolean dataFimDisabled)
    {
        this.dataFimDisabled = dataFimDisabled;
    }

    public boolean isSeleccaoSemanal()
    {
        return seleccaoSemanal;
    }

    public void setSeleccaoSemanal(boolean seleccaoSemanal)
    {
        this.seleccaoSemanal = seleccaoSemanal;
    }

    public MesNode getCurrentMonth()
    {
        return currentMonth;
    }

    public void setCurrentMonth(MesNode currentMonth)
    {
        this.currentMonth = currentMonth;
    }

    public List<MesNode> getAllMeses()
    {
        return allMeses;
    }

    public void setAllMeses(List<MesNode> allMeses)
    {
        this.allMeses = allMeses;
    }

    public List<MesNode> getSelectedMeses()
    {
        return selectedMeses;
    }

    public void setSelectedMeses(List<MesNode> selectedMeses)
    {
        this.selectedMeses = selectedMeses;
    }

    public List<DiaNode> getTargetDias()
    {
        return targetDias;
    }

    public void setTargetDias(List<DiaNode> targetDias)
    {
        this.targetDias = targetDias;
    }

    public DiaNode getCopyDia()
    {
        return copyDia;
    }

    public void setCopyDia(DiaNode copyDia)
    {
        this.copyDia = copyDia;
    }

    public List<DiaNode> getAllDias()
    {
        return allDias;
    }

    public void setAllDias(List<DiaNode> allDias)
    {
        this.allDias = allDias;
    }

    public List<DiaNode> getSelectedDias()
    {
        return selectedDias;
    }

    public void setSelectedDias(List<DiaNode> selectedDias)
    {
        this.selectedDias = selectedDias;
    }

//    public EstruturaLogica getSelectedEstruturaLogica()
//    {
//        return selectedEstruturaLogica;
//    }
//
//    public void setSelectedEstruturaLogica(EstruturaLogica selectedEstruturaLogica)
//    {
//        this.selectedEstruturaLogica = selectedEstruturaLogica;
//    }

    public String getSelectAllDiasLabel()
    {
        return selectAllDiasLabel;
    }

    public void setSelectAllDiasLabel(String selectAllDiasLabel)
    {
        this.selectAllDiasLabel = selectAllDiasLabel;
    }
    
    //----------------------------------------------------------------
//    public class EFisicaNode extends DefaultTreeNode
//    {
//
//        public EFisicaNode(EstruturaFisica eFisica, EFisicaNode node)
//        {
//            super(eFisica, node);
//        }
//
//
//        @Override
//        public EstruturaFisica getData()
//        {
//            return (EstruturaFisica) super.getData();
//        }
//
//        @Override
//        public String toString()
//        {
//            return this.getData().getDesignacao();
//        }
//
//    }

    public class ELogicaNode extends DefaultTreeNode
    {

        public ELogicaNode(PtTransporte eLogica, ELogicaNode node)
        {
            super(eLogica, node);
        }
//

        @Override
        public PtTransporte getData()
        {
            return (PtTransporte) super.getData();
        }

        @Override
        public String toString()
        {
            return this.getData().getMatricula();
        }

    }

    public class MesNode
    {

        String nome, cor;
        int numero, ano, index;
        boolean selected, isSelectedInDB;
        List<DiaNode> dias;
        Calendar data;

        public MesNode(String nome)
        {
            this.nome = nome;
            this.selected = false;
        }

        public MesNode(String nome, int numero, int ano, Calendar data, int index)
        {
            dias = new ArrayList<>();
            this.nome = nome;
            this.numero = numero;
            this.ano = ano;
            this.data = data;
            this.index = index;
        }

        public MesNode(String nome, int numero, int ano, Calendar data, boolean isSelectedInDB, int index)
        {
            dias = new ArrayList<>();
            this.nome = nome;
            this.numero = numero;
            this.ano = ano;
            this.data = data;
            this.index = index;
            this.isSelectedInDB = isSelectedInDB;
        }

        public String getNome()
        {
            return nome;
        }

        public void setNome(String nome)
        {
            this.nome = nome;
        }

        public List<DiaNode> getDias()
        {
            return dias;
        }

        public void setDias(List<DiaNode> dias)
        {
            this.dias = dias;
        }

        public int getNumero()
        {
            return numero;
        }

        public void setNumero(int numero)
        {
            this.numero = numero;
        }

        public int getAno()
        {
            return ano;
        }

        public void setAno(int ano)
        {
            this.ano = ano;
        }

        public boolean isSelected()
        {
            return selected;
        }

        public void setSelected(boolean selected)
        {
            this.selected = selected;
        }

        public Calendar getData()
        {
            return data;
        }

        public void setData(Calendar data)
        {
            this.data = data;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(int index)
        {
            this.index = index;
        }

        public boolean isIsSelectedInDB()
        {
            return isSelectedInDB;
        }

        public void setIsSelectedInDB(boolean isSelectedInDB)
        {
            this.isSelectedInDB = isSelectedInDB;
        }

        public String getCor()
        {
            return cor;
        }

        public void setCor(String cor)
        {
            this.cor = cor;
        }

    }

    public class DiaNode
    {

        String diaSemana, cor;
        int numero;
        boolean selected, isSelectedInDB, radioSelected, checkboxSelected;
        List<HoraNode> horas;
        Date data;

        public DiaNode()
        {
            this.selected = false;
        }

        public DiaNode(String diaSemana, int numero, Date data, boolean isSelectedInDB)
        {
            this.diaSemana = diaSemana;
            this.numero = numero;
            this.data = data;
            this.isSelectedInDB = isSelectedInDB;
            horas = new ArrayList<>();
            this.radioSelected = false;
            this.checkboxSelected = false;
        }

        public DiaNode(String diaSemana, int numero)
        {
            horas = new ArrayList<>();
            this.diaSemana = diaSemana;
            this.numero = numero;
            selected = false;
        }

        public DiaNode(String diaSemana, int numero, boolean selected)
        {
            this.diaSemana = diaSemana;
            this.numero = numero;
            this.selected = selected;
            horas = new ArrayList<>();
            this.radioSelected = false;
            this.checkboxSelected = false;
        }

        public DiaNode(String diaSemana, boolean selected)
        {
            this.diaSemana = diaSemana;
            this.selected = selected;
            horas = new ArrayList<>();
        }

        public String getDiaSemana()
        {
            return diaSemana;
        }

        public void setDiaSemana(String diaSemana)
        {
            this.diaSemana = diaSemana;
        }

        public int getNumero()
        {
            return numero;
        }

        public void setNumero(int numero)
        {
            this.numero = numero;
        }

        public boolean isSelected()
        {
            return selected;
        }

        public void setSelected(boolean selected)
        {
            this.selected = selected;
        }

        public List<HoraNode> getHoras()
        {
            return horas;
        }

        public void setHoras(List<HoraNode> horas)
        {
            this.horas = horas;
        }

        public boolean isIsSelectedInDB()
        {
            return isSelectedInDB;
        }

        public void setIsSelectedInDB(boolean isSelectedInDB)
        {
            this.isSelectedInDB = isSelectedInDB;
        }

        public Date getData()
        {
            return data;
        }

        public void setData(Date data)
        {
            this.data = data;
        }

        public String getCor()
        {
            return cor;
        }

        public void setCor(String cor)
        {
            this.cor = cor;
        }

        public boolean isRadioSelected()
        {
            return radioSelected;
        }

        public void setRadioSelected(boolean radioSelected)
        {
            this.radioSelected = radioSelected;
        }

        public boolean isCheckboxSelected()
        {
            return checkboxSelected;
        }

        public void setCheckboxSelected(boolean checkboxSelected)
        {
            this.checkboxSelected = checkboxSelected;
        }

    }

    public class HoraNode
    {

        int hora;
        boolean selected, selectedInDB;
        Date data;
        String cor;

        public HoraNode(int hora, Date data, boolean selectedInDB, String cor)
        {
            this.hora = hora;
            this.data = data;
            this.cor = cor;
            //this.selected = selected;
            selected = false;
            this.selectedInDB = selectedInDB;
        }

        public HoraNode(int hora, Date data, boolean selectedInDB)
        {
            this.hora = hora;
            this.data = data;
            //this.selected = selected;
            selected = false;
            this.selectedInDB = selectedInDB;
        }

        public HoraNode(int hora)
        {
            this.hora = hora;
            selected = false;
        }

        public HoraNode(int hora, Date data)
        {
            this.hora = hora;
            this.data = data;
            selected = false;
        }

        public int getHora()
        {
            return hora;
        }

        public void setHora(int hora)
        {
            this.hora = hora;
        }

        public boolean isSelected()
        {
            return selected;
        }

        public void setSelected(boolean selected)
        {
            this.selected = selected;
        }

        public Date getData()
        {
            return data;
        }

        public void setData(Date data)
        {
            this.data = data;
        }

        public boolean isSelectedInDB()
        {
            return selectedInDB;
        }

        public void setSelectedInDB(boolean selectedInDB)
        {
            this.selectedInDB = selectedInDB;
        }

        public String getCor()
        {
            return cor;
        }

        public void setCor(String cor)
        {
            this.cor = cor;
        }

    }
    ///////////////////

//    public PtTransporte getSelectedPtTransporteLogica() {
//        return selectedPtTransporteLogica;
//    }
//
//    public void setSelectedPtTransporteLogica(PtTransporte selectedPtTransporteLogica) {
//        this.selectedPtTransporteLogica = selectedPtTransporteLogica;
//    }

    public List<HoraNode> getSelectedHoras() {
        return selectedHoras;
    }

    public void setSelectedHoras(List<HoraNode> selectedHoras) {
        this.selectedHoras = selectedHoras;
    }

    public boolean isIsAllDaysSelected() {
        return isAllDaysSelected;
    }

    public void setIsAllDaysSelected(boolean isAllDaysSelected) {
        this.isAllDaysSelected = isAllDaysSelected;
    }

    public PtTransporte getSelectedPtTransporte() {
        return selectedPtTransporte;
    }

    public void setSelectedPtTransporte(PtTransporte selectedPtTransporte) {
        this.selectedPtTransporte = selectedPtTransporte;
    }

    public List<PtTransporte> getSelectedTransporte() {
        return selectedTransporte;
    }

    public void setSelectedTransporte(List<PtTransporte> selectedTransporte) {
        this.selectedTransporte = selectedTransporte;
    }

    public FrtTransporteAgendar getFrAgendarTransporte() {
        return frAgendarTransporte;
    }

    public void setFrAgendarTransporte(FrtTransporteAgendar frAgendarTransporte) {
        this.frAgendarTransporte = frAgendarTransporte;
    }

    public FrtTransporteTipoAgendamento getTbTipoAgendamento() {
        return tbTipoAgendamento;
    }

    public void setTbTipoAgendamento(FrtTransporteTipoAgendamento tbTipoAgendamento) {
        this.tbTipoAgendamento = tbTipoAgendamento;
    }

    public int getCodigotbTipoAgendamento()
    {
        return codigotbTipoAgendamento;
    }

    public void setCodigotbTipoAgendamento(int codigotbTipoAgendamento)
    {
        this.codigotbTipoAgendamento = codigotbTipoAgendamento;
    }

    public FrtTransporteConfiguracoes getFrtTransporteConfiguracoes()
    {
        return frtConfiguracoes;
    }

    public void setFrtTransporteConfiguracoes(FrtTransporteConfiguracoes frtConfiguracoes)
    {
        this.frtConfiguracoes = frtConfiguracoes;
    }
    
    
    
    
    
 
    
}
