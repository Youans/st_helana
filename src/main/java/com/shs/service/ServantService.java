/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.service;

import com.shs.entities.Servant;
import java.util.List;

/**
 *
 * @author Engy
 * @param <T>
 */
// This service will be defined in baseAction and will be used wherever generic database action is performed like loadAllX or saveX
public interface ServantService extends BasicService<Servant> {

    public Servant loadServantByEmail(String email);

    public Servant loadServantByEmailAndPassword(String email, String password);

    public List<Servant> loadServantsForAdmin();
}
