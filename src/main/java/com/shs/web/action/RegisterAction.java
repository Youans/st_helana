/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.web.action;

import com.shs.common.enums.ServantStudyStatusEnum;
import com.shs.common.enums.ServantServiceStatusEnum;
import com.shs.entities.Hobbie;
import com.shs.entities.Servant;
import com.shs.entities.ServantService;
import com.shs.entities.ServantServiceStatus;
import com.shs.entities.ServantStudy;
import com.shs.entities.ServantStudyStatus;
import com.shs.entities.ServantWeekend;
import com.shs.entities.Service;
import com.shs.entities.Study;
import com.shs.utils.FileUtils;
import com.shs.utils.JsfUtils;
import com.shs.utils.log.SystemLogger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectManyListbox;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youans
 */
@ManagedBean
@ViewScoped
public class RegisterAction extends BaseEntityAction<Servant> implements Serializable {

    @Getter
    @Setter
    private Part personalPhoto;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private int[] hobbiesIds;
    @Getter
    @Setter
    private int[] studiesIds;

    @Getter
    @Setter
    private List<SelectItem> studies;
    @Getter
    @Setter
    private String[] studySelections;
    @Getter
    @Setter
    private SelectItem[] studyStatusArray;

    @Getter
    @Setter
    private List<SelectItem> services;
    @Getter
    @Setter
    private String[] serviceSelections;
    @Getter
    @Setter
    private SelectItem[] serviceTypeArray;

    @Getter
    @Setter
    private int[] weekEnds;
    @Getter
    @Setter
    private HtmlSelectManyListbox studyListbox;

    public void init() {
        int servantId = JsfUtils.getIntParameter("sId");
        setT(getServantService().load(Servant.class, servantId));

        studies = new ArrayList(cache.getStudies().size());
        studyStatusArray = new SelectItem[cache.getServantServiceStatusEnumList().size()];

        for (Study study : cache.getStudies()) {
            studyStatusArray = studyStatusArray.clone();
            SelectItemGroup selectItemGroup = new SelectItemGroup(study.getName());
            for (int i = 0; i < studyStatusArray.length; i++) {
                studyStatusArray[i] = new SelectItem(study.getId() + "_" + ServantStudyStatusEnum.values()[i].getId(), ServantStudyStatusEnum.values()[i].getName());
            }
            selectItemGroup.setSelectItems(studyStatusArray);
            studies.add(selectItemGroup);
        }

        services = new ArrayList(cache.getServices().size());
        serviceTypeArray = new SelectItem[ServantServiceStatusEnum.values().length];

        for (Service service : cache.getServices()) {
            serviceTypeArray = serviceTypeArray.clone();
            SelectItemGroup selectItemGroup = new SelectItemGroup(service.getName());
//            selectItemGroup.setValue(("study_" + study.getId()));
            for (int i = 0; i < serviceTypeArray.length; i++) {
                serviceTypeArray[i] = new SelectItem(service.getId() + "_" + ServantServiceStatusEnum.values()[i].getId(), ServantServiceStatusEnum.values()[i].getName());

            }
            selectItemGroup.setSelectItems(serviceTypeArray);
            services.add(selectItemGroup);
        }
        ////////////////
        if (!getEntity().isNew()) {
            studySelections = new String[getEntity().getServantStudyList().size()];
            hobbiesIds = new int[getEntity().getHobbieList().size()];
            serviceSelections = new String[getEntity().getServantServiceList().size()];
            weekEnds = new int[getEntity().getServantWeekendList().size()];

            for (int i = 0; i < getEntity().getServantStudyList().size(); i++) {
                ServantStudy ss = getEntity().getServantStudyList().get(i);
                studySelections[i] = (ss.getStudy().getId() + "_" + ss.getServantStudyStatus().getId());
            }

            for (int i = 0; i < getEntity().getHobbieList().size(); i++) {
                Hobbie h = getEntity().getHobbieList().get(i);
                hobbiesIds[i] = h.getId();
            }
            for (int i = 0; i < getEntity().getServantServiceList().size(); i++) {
                ServantService ss = getEntity().getServantServiceList().get(i);
                serviceSelections[i] = (ss.getService().getId() + "_" + ss.getServantServiceStatus().getId());
            }
            for (int i = 0; i < getEntity().getServantServiceList().size(); i++) {
                ServantService ss = getEntity().getServantServiceList().get(i);
                serviceSelections[i] = (ss.getService().getId() + "_" + ss.getServantServiceStatus().getId());
            }
            for (int i = 0; i < getEntity().getServantWeekendList().size(); i++) {
                ServantWeekend sw = getEntity().getServantWeekendList().get(i);
                weekEnds[i] = sw.getWeekend();
            }

        }

    }

    public RegisterAction() {
        super(Servant.class);
    }

    @Override
    public void save() {
        try {
            List<ServantStudy> servantStudyList = new ArrayList();

            for (String s : (String[]) studyListbox.getSelectedValues()) {
                String[] values = s.split("_");
                int studyId = Integer.valueOf(values[0]);
                int statusId = Integer.valueOf(values[1]);
                servantStudyList.add(new ServantStudy(getEntity(), new Study(studyId), new ServantStudyStatus(statusId)));
            }
            List<ServantService> servantServiceList = new ArrayList();

            for (String s : serviceSelections) {
                System.out.println("service " + s);
                String[] values = s.split("_");
                int serviceId = Integer.valueOf(values[0]);
                int statusId = Integer.valueOf(values[1]);
                servantServiceList.add(new ServantService(getEntity(), new Service(serviceId), new ServantServiceStatus(statusId)));
            }

            List<Hobbie> hobbies = new ArrayList();
            for (int id : hobbiesIds) {
                hobbies.add(new Hobbie(id));
            }
            List<ServantWeekend> servantWeekends = new ArrayList();
            for (int weekEndId : weekEnds) {
                servantWeekends.add(new ServantWeekend(getEntity(), weekEndId));
            }

            getEntity().setHobbieList(hobbies);
            if (personalPhoto != null) {
                getEntity().setPersonalPhoto(FileUtils.uploadImage(personalPhoto, "/files/"));
            }
            getEntity().setServantStudyList(servantStudyList);

            getEntity().setServantServiceList(servantServiceList);

            getEntity().setServantWeekendList(servantWeekends);

            getEntity().setPassword("st-helana-" + System.nanoTime());

            super.save();
            JsfUtils.redirect("/listing-servants.html");
        } catch (Exception e) {
            e.printStackTrace();
            SystemLogger.getLogger(getClass().getName()).error(null, e);
        }
    }

}
