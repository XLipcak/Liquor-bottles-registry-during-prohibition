package muni.fi.pa165.liquorbottles.presentation;

import java.util.List;
import static muni.fi.pa165.liquorbottles.presentation.BaseActionBean.escapeHTML;
import muni.fi.pa165.liquorbottles.service.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.services.BottleTypeService;
import muni.fi.pa165.liquorbottles.service.services.ProducerService;
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

    private List<ProducerDTO> producerList;

    private String name;
    private String alcType;
    private int volume;
    private int power;

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "alcType", required = true),
        @Validate(on = {"add", "save"}, field = "volume", required = true),
        @Validate(on = {"add", "save"}, field = "power", required = true)
    })
    private BottleTypeDTO bottleType;

    public List<ProducerDTO> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<ProducerDTO> producerList) {
        this.producerList = producerList;
    }

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
        producerList = producerService.findAll();
        bottleTypeList = bottleTypeService.findAll();
        return null;
    }

    public BottleTypeDTO getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleTypeDTO bottleType) {
        this.bottleType = bottleType;
    }
    private long producerID;

    public long getProducerID() {
        //
        return producerID;
    }

    public void setProducerID(long producerID) {
        this.producerID = producerID;
    }

    public Resolution add() {
        LOGGER.debug("add() bottleType={}", bottleType);
        if (getProducerID() <= 0) {
            getContext().getMessages().add(new LocalizableMessage("bottleType.add.error.message"));
        } else {
            bottleType.setProducer(producerService.findById(producerID));
            bottleTypeService.insertBottleType(bottleType);
            getContext().getMessages().add(new LocalizableMessage("bottleType.add.message", escapeHTML(bottleType.getName())));
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadBottleTypeFromDatabase() {
        String ids = getContext().getRequest().getParameter("bottleType.id");
        if (ids == null) {
            return;
        }
        bottleType = bottleTypeService.findById(Long.parseLong(ids));
    }

    public Resolution edit() {
        LOGGER.debug("edit() bottleType={}", bottleType);
        producerList = producerService.findAll();
        return new ForwardResolution("/bottleType/edit.jsp");
    }

    public Resolution filter() {
        if (producerID <= 0) {
            producerID = -1;
        }
        if (name == null) {
            name = "";
        }
        if (alcType == null) {
            alcType = "";
        }
        if (power <= 0) {
            power = -1;
        }
        if (volume <= 0) {
            volume = -1;
        }

        bottleTypeList = bottleTypeService.findByFilter(producerID, name, alcType, power, volume);
        producerList = producerService.findAll();
        return new ForwardResolution("/bottleType/list.jsp");
    }

    public Resolution save() {
        LOGGER.debug("save() bottleType={}", bottleType);
        bottleType.setProducer(producerService.findById(producerID));
        bottleTypeService.updateBottleType(bottleType);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        LOGGER.debug("delete({})", bottleType.getId());
        //only id is filled by the form
        bottleType = bottleTypeService.findById(bottleType.getId());
        try {
            bottleTypeService.deleteBottleType(bottleType);
            getContext().getMessages().add(new LocalizableMessage("bottleType.delete.message", escapeHTML(bottleType.getName())));
        } catch (Exception ex) {
            getContext().getMessages().add(new LocalizableMessage("bottleType.delete.error.message", escapeHTML(bottleType.getName())));
        }

        return new RedirectResolution(this.getClass(), "list");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlcType() {
        return alcType;
    }

    public void setAlcType(String alcType) {
        this.alcType = alcType;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
