/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.presentation;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Store;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.services.StoreService;
import muni.fi.pa165.liquorbottles.service.services.impl.StoreServiceImpl;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matus Novak fi muni
 */
@UrlBinding("/store.action")
public class StoreActionBean extends BaseActionBean implements ValidationErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreActionBean.class);

    public StoreActionBean() {
        //Set Logger
        BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);
    }


    /*public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }*/
    
    // Inject!!
    @SpringBean
    protected StoreService storeService;

    private List<StoreDTO> storeList;

    // View All part
    @DefaultHandler
    public Resolution list() {
        LOGGER.debug("list()");
           storeList = storeService.findAll();
        return new ForwardResolution("/store/list.jsp");
    }

    public List<StoreDTO> getStoreList() {
        return storeList;
    }

    // ADD part
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "address", required = true),
        @Validate(on = {"add", "save"}, field = "username", required = true),
        @Validate(on = {"add", "save"}, field = "password", required = true)
    })

    private StoreDTO store;

    public Resolution add() {
        LOGGER.debug("add() store={}", store);
        storeService.insertStore(store);
        getContext().getMessages().add(new LocalizableMessage("store.add.message", escapeHTML(store.getName()), escapeHTML(store.getAddress())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        storeList = storeService.findAll();
        //return null to let the event handling continue
        return null;
    }

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    // EDIT part    
    // DELETE part
}
