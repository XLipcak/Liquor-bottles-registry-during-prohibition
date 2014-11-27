package muni.fi.pa165.liquorbottles.presentation;

import java.util.List;
import static muni.fi.pa165.liquorbottles.presentation.BaseActionBean.escapeHTML;
import muni.fi.pa165.liquorbottles.service.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.services.BottleTypeService;
import muni.fi.pa165.liquorbottles.service.services.ProducerService;
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
 * @author Michal Štora, Masaryk University
 */
@UrlBinding("/bottleType.action")
public class BottleTypeActionBean extends BaseActionBean implements ValidationErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreActionBean.class);

    @SpringBean
    protected BottleTypeService bottleTypeService;
    @SpringBean
    protected ProducerService producerService;

    private List<BottleTypeDTO> bottleTypeList;

    public List<ProducerDTO> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<ProducerDTO> producerList) {
        this.producerList = producerList;
    }
    private List<ProducerDTO> producerList;

    public List<BottleTypeDTO> getBottleTypeList() {
        return bottleTypeList;
    }

    public void setBottleTypeList(List<BottleTypeDTO> bottleTypeList) {
        this.bottleTypeList = bottleTypeList;
    }

    public BottleTypeActionBean() {
        //Set Logger
        BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);

    }

    @DefaultHandler
    public Resolution list() {
        LOGGER.debug("list()");
        bottleTypeList = bottleTypeService.findAll();
        producerList = producerService.findAll();
        return new ForwardResolution("/bottleType/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        bottleTypeList = bottleTypeService.findAll();
        return null;
    }

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "alcType", required = true),
        @Validate(on = {"add", "save"}, field = "volume", required = true),
        @Validate(on = {"add", "save"}, field = "power", required = true)
    })

    private BottleTypeDTO bottleType;

    public BottleTypeDTO getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleTypeDTO bottleType) {
        this.bottleType = bottleType;
    }
    private long producerID;

    public long getProducerID() {
        return producerID;
    }

    public void setProducerID(long producerID) {
        this.producerID = producerID;
    }



    private String id;

    public Resolution add() {
        LOGGER.debug("add() bottleType={}", bottleType);
        bottleType.setProducer(producerService.findById(producerID));
        bottleTypeService.insertBottleType(bottleType);
        getContext().getMessages().add(new LocalizableMessage("bottleType.add.message", escapeHTML(bottleType.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

}
