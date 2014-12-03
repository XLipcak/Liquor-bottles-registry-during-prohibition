package muni.fi.pa165.liquorbottles.presentation;

import java.util.List;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.services.StoreService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
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

    //inject
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
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadStoreFromDatabase() {
        String ids = getContext().getRequest().getParameter("store.id");
        if (ids == null) {
            return;
        }
        store = storeService.findById(Long.parseLong(ids));
    }

    public Resolution edit() {
        LOGGER.debug("edit() store={}", store);
        return new ForwardResolution("/store/edit.jsp");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String name;
    private String address;

    public Resolution filter() {
        LOGGER.debug("filter() store={}", store);
        if (name == null)
            name = "";
        if (address == null)
            address = "";
        storeList = storeService.findByFilter(name, address);
        return new ForwardResolution("/store/list.jsp");
    }

    public Resolution save() {
        LOGGER.debug("save() store={}", store);
        storeService.updateStore(store);
        return new RedirectResolution(this.getClass(), "list");
    }
    // DELETE part

    public Resolution delete() {
        LOGGER.debug("delete({})", store.getId());
        store = storeService.findById(store.getId());
        
        try {
            storeService.deleteStore(store);
            getContext().getMessages().add(new LocalizableMessage("store.delete.message", escapeHTML(store.getName()), escapeHTML(store.getAddress())));
        } catch (Exception ex) {
            getContext().getMessages().add(new LocalizableMessage("store.delete.error.message", escapeHTML(store.getName())));
        }
        
        return new RedirectResolution(this.getClass(), "list");
    }
}
