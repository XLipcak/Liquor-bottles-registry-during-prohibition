package muni.fi.pa165.liquorbottles.presentation;

import java.util.List;
import static muni.fi.pa165.liquorbottles.presentation.BaseActionBean.escapeHTML;
import muni.fi.pa165.liquorbottles.service.dto.PoliceDTO;
import muni.fi.pa165.liquorbottles.service.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.service.services.PoliceService;
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

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
@UrlBinding("/police.action")
public class PoliceActionBean extends BaseActionBean implements ValidationErrorHandler{
    
    @SpringBean
    private PoliceService policeService;

    private List<PoliceDTO> policeList;
    
    private PoliceDTO police;

    // View All part
    @DefaultHandler
    public Resolution list() {
        policeList = policeService.findAll();
        return new ForwardResolution("/police/list.jsp");
    }


    // ADD part
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "address", required = true),
        @Validate(on = {"add", "save"}, field = "username", required = true),
        @Validate(on = {"add", "save"}, field = "password", required = true)
    })

    

    public Resolution add() {
        policeService.insertPolice(police);
        getContext().getMessages().add(new LocalizableMessage("police.add.message", escapeHTML(police.getName()), escapeHTML(police.getAddress())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        policeList = policeService.findAll();
        //return null to let the event handling continue
        return null;
    }


    // EDIT part   
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadStoreFromDatabase() {
        String ids = getContext().getRequest().getParameter("police.id");
        if (ids == null) {
            return;
        }
        police = policeService.findById(Long.parseLong(ids));
    }
    

    public Resolution edit() {
        return new ForwardResolution("/police/edit.jsp");
    }

    
    public Resolution save() {
        policeService.updatePolice(police);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        police = policeService.findById(police.getId());
        policeService.deletePolice(police);
        getContext().getMessages().add(new LocalizableMessage("police.delete.message", escapeHTML(police.getName()), escapeHTML(police.getAddress())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public PoliceService getPoliceService() {
        return policeService;
    }

    public void setPoliceService(PoliceService policeService) {
        this.policeService = policeService;
    }

    public List<PoliceDTO> getPoliceList() {
        return policeList;
    }

    public void setPoliceList(List<PoliceDTO> policeList) {
        this.policeList = policeList;
    }

    public PoliceDTO getPolice() {
        return police;
    }

    public void setPolice(PoliceDTO police) {
        this.police = police;
    }
    
    
}
