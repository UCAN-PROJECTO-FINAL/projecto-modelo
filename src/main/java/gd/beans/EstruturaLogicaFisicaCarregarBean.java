/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gd.beans;

import estrutura.ejbs.cache.EstruturaFisicaCache;
import estrutura.ejbs.cache.EstruturaLogicaCache;
import estrutura.ejbs.cache.EstruturaLogicaFisicaCache;
import ejbs.entities.EstruturaFisica;
import ejbs.entities.EstruturaLogica;
import ejbs.entities.EstruturaLogicaFisica;
import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import utils.Defs;

/**
 *
 * @author mdnex
 */
@Named(value = "estruturaLogicaFisicaCarregarrBean")
@SessionScoped
//@ViewScoped
public class EstruturaLogicaFisicaCarregarBean implements Serializable
{

    @Inject
    private EstruturaLogicaCache estruturaLogicaCache;

    @Inject
    private EstruturaFisicaCache estruturaFisicaCache;

    @Inject
    private EstruturaLogicaFisicaCache estruturaLogicaFisicaCache;

//    @Inject
//    private EstruturaLogicaFisicaHorarioCache estruturaLogicaFisicaHorarioCache;

    private ELogicaNode rootELogica;
    private EstruturaLogica selectedEstruturaLogica;

    private EFisicaNode rootEFisica;
    private List<EstruturaFisica> selectedEstruturaFisica;

    private Date dataInicio, dataFim;
    private boolean dataFimDisabled = true, seleccaoSemanal,isAllDaysSelected=false;
    private MesNode currentMonth;
    private List<MesNode> allMeses, selectedMeses;
    private DiaNode copyDia;
    private List<DiaNode> allDias, selectedDias, targetDias;
    private List<HoraNode> selectedHoras;
    private String selectAllDiasLabel;

    @PostConstruct
    public void init()
    {
        selectedEstruturaFisica = new ArrayList<>();
        rootELogica = new ELogicaNode(estruturaLogicaCache.findEstruturaLogicaRoot(), null);
        rootEFisica = new EFisicaNode(estruturaFisicaCache.findEstruturaFisicaRoot(), null);
        initELogicaNodes(rootELogica);
        initEFsicaNodes(rootEFisica);
        allMeses = new ArrayList<>();
        selectedMeses = new ArrayList<>();
        selectedDias = new ArrayList<>();
        selectedHoras = new ArrayList<>();
        targetDias = new ArrayList<>();
        selectAllDiasLabel = "selecionar todos";
    }

    public void initELogicaNodes(ELogicaNode paiNode)
    {

        EstruturaLogica estruturaLogicaPai = paiNode.getData();
        List<EstruturaLogica> filhos = estruturaLogicaCache.getListaSonsByPkEstruturaLogica(estruturaLogicaPai.getPkEstruturaLogica());
        if (filhos == null || filhos.size() == 0)
        {
            return;
        }
        for (EstruturaLogica filho : filhos)
        {
            ELogicaNode filhoNode = new ELogicaNode(filho, paiNode);

            initELogicaNodes(filhoNode);
        }

    }

    public void initEFsicaNodes(EFisicaNode paiNode)
    {

        EstruturaFisica estruturaFisicaPai = paiNode.getData();
        List<EstruturaFisica> filhos = estruturaFisicaCache.getListaSonsByPkEstruturaFisica(estruturaFisicaPai.getPkEstruturaFisica());
        if (filhos == null || filhos.isEmpty())
        {
            return;
        }
        for (EstruturaFisica filho : filhos)
        {
            EFisicaNode filhoNode = new EFisicaNode(filho, paiNode);

            initEFsicaNodes(filhoNode);
        }

    }

    public void removeFromSelectedEstruturaFisica(EstruturaFisica pai)
    {
        List<EstruturaFisica> filhos = estruturaFisicaCache.getListaSonsByPkEstruturaFisica(pai.getPkEstruturaFisica());
        if (filhos == null || filhos.isEmpty())
        {
            return;
        }
        for (EstruturaFisica filho : filhos)
        {
            this.selectedEstruturaFisica.remove(filho);

            removeFromSelectedEstruturaFisica(filho);
        }

    }

    public void appendToSelectedEstruturaFisica(EstruturaFisica pai)
    {
        List<EstruturaFisica> filhos = estruturaFisicaCache.getListaSonsByPkEstruturaFisica(pai.getPkEstruturaFisica());
        if (filhos == null || filhos.size() == 0)
        {
            return;
        }
        for (EstruturaFisica filho : filhos)
        {
            this.selectedEstruturaFisica.add(filho);

            appendToSelectedEstruturaFisica(filho);
        }

    }

    public void onELogicaSelect(NodeSelectEvent event)
    {
        clearAllNodes(rootEFisica);
        ELogicaNode selectedNodeTmp = (ELogicaNode) event.getTreeNode();
        selectedEstruturaLogica = (EstruturaLogica) event.getTreeNode().getData();
        selectedNodeTmp.setSelected(true);
        findEstruturaFisica();

        System.err.println("0: EstruturaLogicaFsicaCarregar.onELogicaSelect()");
    }

    public void onELogicaUnselect(NodeUnselectEvent event)
    {
        selectedEstruturaFisica.clear();
        ELogicaNode selectedNodeTmp = (ELogicaNode) event.getTreeNode();
        selectedNodeTmp.setSelected(false);
        System.err.println("0: EstruturaLogicaFsicaCarregar.onELogicaUnselect()");
    }

    public void findEstruturaFisica()
    {
        List<EstruturaFisica> list = estruturaLogicaFisicaCache.findByEstruturaLogicaPk(selectedEstruturaLogica.getPkEstruturaLogica());
        if (list.isEmpty())
        {
            list = estruturaFisicaCache.findAllIndefinido();
        }
        for (EstruturaFisica estruturaFisica : list)
        {
            setNodeList(rootEFisica, estruturaFisica, true);
        }

        selectedEstruturaFisica = list;
    }

    public void setNodeList(EFisicaNode node, EstruturaFisica eFisica, boolean state)
    {
        if (node.getData().getPkEstruturaFisica().equals(eFisica.getPkEstruturaFisica()))
        {
            node.setSelected(state);
        }

        List<TreeNode> list = node.getChildren();
        if (list.isEmpty() || list == null)
        {
            return;
        }

        for (TreeNode treeNode : list)
        {
            setNodeList((EFisicaNode) treeNode, eFisica, state);
        }
        return;
    }

    public void setNodeList(EFisicaNode node, boolean state)
    {
        node.setSelected(state);

        List<TreeNode> list = node.getChildren();
        if (list.isEmpty() || list == null)
        {
            return;
        }

        for (TreeNode treeNode : list)
        {
            setNodeList((EFisicaNode) treeNode, state);
        }
        return;
    }

    public void clearAllNodes(EFisicaNode node)
    {
        node.setSelected(false);

        List<TreeNode> list = node.getChildren();
        if (list.isEmpty() || list == null)
        {
            return;
        }

        for (TreeNode treeNode : list)
        {
            clearAllNodes((EFisicaNode) treeNode);
        }
        return;
    }

    public void onEFisicaSelect(NodeSelectEvent event)
    {
        if (!selectedEstruturaFisica.isEmpty())
        {
            if (selectedEstruturaFisica.get(0).getDesignacao().contains("Indefinido"))
            {
                selectedEstruturaFisica = new ArrayList<>();
            }
        }
        EFisicaNode eNode = (EFisicaNode) event.getTreeNode();
        EstruturaFisica eFisica = (EstruturaFisica) event.getTreeNode().getData();
        setNodeList(eNode, true);
        appendToSelectedEstruturaFisica((EstruturaFisica) eNode.getData());
        selectedEstruturaFisica.add(eFisica);
        System.err.println("0: EstruturaLogicaFsicaCarregar.onEFisicaSelect()");
    }

    public void onEFisicaUnselect(NodeUnselectEvent event)
    {
        EFisicaNode eNode = (EFisicaNode) event.getTreeNode();
        EstruturaFisica eFisica = (EstruturaFisica) event.getTreeNode().getData();
        setNodeList(eNode, false);
        removeFromSelectedEstruturaFisica(eFisica);
        selectedEstruturaFisica.remove(eFisica);
        System.err.println("0: EstruturaLogicaFsicaCarregar.onEFisicaUnselect()");
    }

    public void limparEFisicaNodesListener()
    {
        clearAllNodes(rootEFisica);
    }

    public void onEFisicaExpand(NodeUnselectEvent event)
    {
        System.err.println("0: EstruturaLogicaFsicaCarregar.onEFisicaExpand()");
    }

    public void onEFisicaColapse(NodeUnselectEvent event)
    {
        System.err.println("0: EstruturaLogicaFsicaCarregar.onEFisicaColapse()");
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
                isInDB = estruturaLogicaFisicaCache.isMesSelectedByOthers(cld.getTime(), selectedEstruturaLogica);
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
                mes.setCor(estruturaLogicaFisicaCache.getMesColor(cld, selectedEstruturaLogica));
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
            isInDB = estruturaLogicaFisicaCache.isDiaSelectedByOthers(cld, selectedEstruturaLogica);
            DiaNode day = new DiaNode(dateFormat + "/" + i_day, i_day, isInDB);
            day.setCor(estruturaLogicaFisicaCache.getDiaColor(cld, selectedEstruturaLogica));

            for (hour = 0; hour < 24; hour++)
            {
                cld.set(Calendar.HOUR_OF_DAY, hour);

                horaStr = hour > 9 ? ("" + hour) : ("0" + hour);
                try
                {
                    date_full = cld.get(Calendar.YEAR) + "-" + (cld.get(Calendar.MONTH) + 1) + "-" + cld.get(Calendar.DAY_OF_MONTH);

                    d1 = (java.util.Date) format.parse(date_full + " " + horaStr + ":00:00");
                    isInDB = estruturaLogicaFisicaCache.isHoraSelectedByOthers(cld, selectedEstruturaLogica);
                    day.horas.add(new HoraNode(hour, cld.getTime(), isInDB, estruturaLogicaFisicaCache.getHoraColor(cld, selectedEstruturaLogica)));
                }
                catch (ParseException ex)
                {
                    Logger.getLogger(EstruturaLogicaFisicaCarregarBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            mes.dias.add(day);
        }

    }

    public void changeDays(int month)
    {
        currentMonth = allMeses.get(month);
        allDias = currentMonth.dias;
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

    public int calculaColumns()
    {
        return allMeses.size();
    }

    public void selectUnselectAllDays()
    {
        if(isAllDaysSelected)
            unSelectAllDays();
        else
            selectAllDays();
        
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
    
    private void unselectAllDays()
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
        
        targetDias.clear();
    }

    public void unSelectAllDays()
    {
        targetDias.clear();
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

    public void clearDias()
    {
        int hour;
        for (DiaNode dia : targetDias)
        {
            for (hour = 0; hour < 24; hour++)
            {
                if (!dia.horas.get(hour).isSelected())
                {
                    dia.horas.get(hour).setSelected(false);
                }
            }
            dia.setSelected(true);
        }
        selectedDias.clear();
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

    public void clearDaysSelections()
    {
        for (DiaNode dia : selectedDias)
        {
            dia.setSelected(false);
        }
        selectedDias.clear();
        clearHoursSelections();
    }

    public void clearHoursSelections()
    {
        for (HoraNode hora : selectedHoras)
        {
            hora.setSelected(false);
        }
        selectedHoras.clear();
    }

    public void process()
    {
        System.err.println("0: EstruturaLogicaFsicaCarregar.process()");
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

    public void salvar()
    {
        saveFromHours();
        System.err.println("0: EstruturaLogicaFsicaCarregar.salvar()");
    }
//    public void salvar()
//    {
//
//        if (!selectedMeses.isEmpty())
//        {
//            saveFromMonth();
//
//        }
//        else if (!selectedDias.isEmpty())
//        {
//            saveFromDays();
//        }
//        else
//        {
//            saveFromHours();
//        }
//        System.err.println("0: EstruturaLogicaFsicaCarregar.salvar()");
//    }

    public void saveFromMonth()
    {
        EstruturaLogicaFisica estrutura;
        String horaStr, dateStr;
        Calendar cld = Calendar.getInstance();
        Date data;
        SimpleDateFormat format;
        for (EstruturaFisica estruturaFisica : selectedEstruturaFisica)
        {

            for (MesNode mes : selectedMeses)
            {
                for (DiaNode dia : mes.dias)
                {
                    for (HoraNode horaNode : dia.horas)
                    {
                        estrutura = new EstruturaLogicaFisica();
                        estrutura.setFkEstruturaFisica(estruturaFisica);
                        estrutura.setFkEstruturaLogica(selectedEstruturaLogica);
                        estrutura.setData(horaNode.data);
                        format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

                        try
                        {

                            horaStr = horaNode.getHora() > 9 ? ("" + horaNode.getHora()) : ("0" + horaNode.getHora());

                            cld.setTime(horaNode.getData());
                            dateStr = cld.get(Calendar.YEAR) + "-" + cld.get(Calendar.MONTH) + "-" + cld.get(Calendar.DAY_OF_MONTH);
                            data = (java.util.Date) format.parse(dateStr + " " + horaStr + ":00:00");

                            Time ppstime = new java.sql.Time(data.getTime());
                            estrutura.setHora(ppstime);
                            //hora.setFkEstruturaLogicaFisica(estruturaLogicaFisica);

                            estruturaLogicaFisicaCache.create(estrutura);
                        }
                        catch (ParseException ex)
                        {
                            Logger.getLogger(EstruturaLogicaFisicaCarregarBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }

        }
    }

    public void saveFromDays()
    {
        EstruturaLogicaFisica estrutura;
        String horaStr, dateStr;
        Calendar cld = Calendar.getInstance();
        Date data;
        SimpleDateFormat format;
        for (EstruturaFisica estruturaFisica : selectedEstruturaFisica)
        {
            for (DiaNode diaNode : selectedDias)
            {
                for (HoraNode horaNode : selectedHoras)
                {
                    estrutura = new EstruturaLogicaFisica();
                    estrutura.setFkEstruturaFisica(estruturaFisica);
                    estrutura.setFkEstruturaLogica(selectedEstruturaLogica);
                    estrutura.setData(horaNode.data);
                    format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

                    try
                    {
                        horaStr = horaNode.getHora() > 9 ? ("" + horaNode.getHora()) : ("0" + horaNode.getHora());

                        cld.setTime(horaNode.getData());
                        dateStr = cld.get(Calendar.YEAR) + "-" + cld.get(Calendar.MONTH) + "-" + cld.get(Calendar.DAY_OF_MONTH);
                        data = (java.util.Date) format.parse(dateStr + " " + horaStr + ":00:00");

                        Time ppstime = new java.sql.Time(data.getTime());
                        estrutura.setHora(ppstime);

                        estruturaLogicaFisicaCache.create(estrutura);
                    }
                    catch (ParseException ex)
                    {
                        Logger.getLogger(EstruturaLogicaFisicaCarregarBean.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    }

    public void saveFromHours()
    {
        EstruturaLogicaFisica estrutura;
        String horaStr, dateStr;
        Calendar cld = Calendar.getInstance();
        Date data;
        SimpleDateFormat format;
        for (EstruturaFisica estruturaFisica : selectedEstruturaFisica)
        {
          
            for (HoraNode horaNode : selectedHoras)
            {
                estrutura = new EstruturaLogicaFisica();
                estrutura.setFkEstruturaFisica(estruturaFisica);
                estrutura.setFkEstruturaLogica(selectedEstruturaLogica);
                estrutura.setData(horaNode.data);
                format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

                try
                {
                    horaStr = horaNode.getHora() > 9 ? ("" + horaNode.getHora()) : ("0" + horaNode.getHora());

                    cld.setTime(horaNode.getData());
                    dateStr = cld.get(Calendar.YEAR) + "-" + cld.get(Calendar.MONTH) + "-" + cld.get(Calendar.DAY_OF_MONTH);
                    data = (java.util.Date) format.parse(dateStr + " " + horaStr + ":00:00");

                    Time ppstime = new java.sql.Time(data.getTime());
                    estrutura.setHora(ppstime);

                    estruturaLogicaFisicaCache.create(estrutura);
                }
                catch (ParseException ex)
                {
                    Logger.getLogger(EstruturaLogicaFisicaCarregarBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
    
    public EstruturaLogicaFisica saveFroHora()
    {
        EstruturaLogicaFisica estrutura = new EstruturaLogicaFisica();
        String horaStr, dateStr;
        Calendar cld = Calendar.getInstance();
        Date data;
        SimpleDateFormat formata;
        SimpleDateFormat formatHora;
       
            
//            estrutura = new EstruturaLogicaFisica();
            estrutura.setFkEstruturaFisica(new EstruturaFisica("0.0"));
            estrutura.setFkEstruturaLogica(selectedEstruturaLogica);
//            formata = new SimpleDateFormat("yyyy-mm-dd");
            estrutura.setData(new Date());
            formatHora = new SimpleDateFormat("hh:mm:ss");
            estrutura.setHora(java.sql.Time.valueOf(formatHora.format(new Date())));
            
            estruturaLogicaFisicaCache.create(estrutura);
          
        return estrutura;
    }
//    public EstruturaLogicaFisica saveFroHora()
//    {
//        EstruturaLogicaFisica estrutura = new EstruturaLogicaFisica();
//        String horaStr, dateStr;
//        Calendar cld = Calendar.getInstance();
//        Date data;
//        SimpleDateFormat formata;
//        SimpleDateFormat formatHora;
//        for (EstruturaFisica estruturaFisica : selectedEstruturaFisica)
//        {
//            
////            estrutura = new EstruturaLogicaFisica();
//            estrutura.setFkEstruturaFisica(estruturaFisica);
//            estrutura.setFkEstruturaLogica(selectedEstruturaLogica);
////            formata = new SimpleDateFormat("yyyy-mm-dd");
//            estrutura.setData(new Date());
//            formatHora = new SimpleDateFormat("hh:mm:ss");
//            estrutura.setHora(java.sql.Time.valueOf(formatHora.format(new Date())));
//            
//            estruturaLogicaFisicaCache.create(estrutura);
//            
//            break;
//           
//        }
//        return estrutura;
//    }

    //--------------------------------------------------------------------------
    public ELogicaNode getRootELogica()
    {
        return rootELogica;
    }

    public void setRootELogica(ELogicaNode rootELogica)
    {
        this.rootELogica = rootELogica;
    }

    public EFisicaNode getRootEFisica()
    {
        return rootEFisica;
    }

    public void setRootEFisica(EFisicaNode rootEFisica)
    {
        this.rootEFisica = rootEFisica;
    }

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

    public EstruturaLogica getSelectedEstruturaLogica()
    {
        return selectedEstruturaLogica;
    }

    public void setSelectedEstruturaLogica(EstruturaLogica selectedEstruturaLogica)
    {
        this.selectedEstruturaLogica = selectedEstruturaLogica;
    }

    public String getSelectAllDiasLabel()
    {
        return selectAllDiasLabel;
    }

    public void setSelectAllDiasLabel(String selectAllDiasLabel)
    {
        this.selectAllDiasLabel = selectAllDiasLabel;
    }
    
    //----------------------------------------------------------------
    public class EFisicaNode extends DefaultTreeNode
    {

        public EFisicaNode(EstruturaFisica eFisica, EFisicaNode node)
        {
            super(eFisica, node);
        }
//

        @Override
        public EstruturaFisica getData()
        {
            return (EstruturaFisica) super.getData();
        }

        @Override
        public String toString()
        {
            return this.getData().getDesignacao();
        }

    }

    public class ELogicaNode extends DefaultTreeNode
    {

        public ELogicaNode(EstruturaLogica eLogica, ELogicaNode node)
        {
            super(eLogica, node);
        }
//

        @Override
        public EstruturaLogica getData()
        {
            return (EstruturaLogica) super.getData();
        }

        @Override
        public String toString()
        {
            return this.getData().getDesignacao();
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

}
