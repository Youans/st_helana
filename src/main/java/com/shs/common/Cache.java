/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.common;

import com.shs.common.enums.ServantStudyStatusEnum;
import com.shs.entities.Hobbie;
import com.shs.entities.Service;
import com.shs.entities.ServiceType;
import com.shs.entities.Study;
import com.shs.service.BasicService;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Youans
 */
@ManagedBean
@ApplicationScoped
public class Cache {

    @ManagedProperty("#{basicService}")
    @Getter
    @Setter
    private BasicService basicService;

    private List<Hobbie> hobbies;

    private List<Study> studies;

    private List<ServantStudyStatusEnum> servantServiceStatusEnumList;

    private List<ServiceType> serviceTypes;
    private List<Service> services;
    @Getter
    private Map<String, Integer> daysOfWeek;

    @PostConstruct
    public void init() {
        refreshCache();
    }

    public void refreshCache() {
        hobbies = basicService.loadAll(Hobbie.class);
        studies = basicService.loadAll(Study.class);
        serviceTypes = basicService.loadAll(ServiceType.class);
        servantServiceStatusEnumList = Arrays.asList(ServantStudyStatusEnum.values());
        services = basicService.loadAll(Service.class);
        daysOfWeek = new LinkedHashMap();
        daysOfWeek.put("SUNDAY", Calendar.SUNDAY);
        daysOfWeek.put("MONDAY", Calendar.MONDAY);
        daysOfWeek.put("TUESDAY", Calendar.TUESDAY);
        daysOfWeek.put("WEDNESDAY", Calendar.WEDNESDAY);
        daysOfWeek.put("THURSDAY", Calendar.THURSDAY);
        daysOfWeek.put("FRIDAY", Calendar.FRIDAY);
        daysOfWeek.put("SATURDAY", Calendar.SATURDAY);

    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public List<ServantStudyStatusEnum> getServantServiceStatusEnumList() {
        return servantServiceStatusEnumList;
    }

    public List<Hobbie> getHobbies() {
        return hobbies;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public List<Service> getServices() {
        return services;
    }

}
