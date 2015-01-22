package muni.fi.pa165.liquorbottles.presentation;

import java.util.List;
import static muni.fi.pa165.liquorbottles.presentation.BaseActionBean.escapeHTML;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleTypeService;
import muni.fi.pa165.liquorbottles.api.services.ProducerService;
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
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@UrlBinding("/producer.action")
public class ProducerActionBean extends BaseActionBean implements ValidationErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerActionBean.class);

    @SpringBean
    protected ProducerService producerService;

    @SpringBean
    protected BottleTypeService bottleTypeService;

    private List<ProducerDTO> producerList;
    private String name;
    private String address;

    public List<ProducerDTO> getProducerList() {
        return producerList;
    }

    public ProducerActionBean() {
        BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);
    }

    @DefaultHandler
    public Resolution list() {
        LOGGER.debug("list()");
        producerList = producerService.findAll();
        return new ForwardResolution("/producer/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        producerList = producerService.findAll();
        return null;
    }

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "address", required = true),
        @Validate(on = {"add", "save"}, field = "username", required = true),
        @Validate(on = {"add", "save"}, field = "password", required = true)
    })

    private ProducerDTO producer;

    public ProducerDTO getProducer() {
        return producer;
    }

    public void setProducer(ProducerDTO producer) {
        this.producer = producer;
    }

    public Resolution add() {
        LOGGER.debug("add() producer={}", producer);
        try {
            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            producer.setPassword(encoder.encodePassword(producer.getPassword(), null));
            producerService.insertProducer(producer);
            getContext().getMessages().add(new LocalizableMessage("producer.add.message", escapeHTML(producer.getName()), escapeHTML(producer.getAddress())));
        } catch (Exception ex) {
            getContext().getMessages().add(new LocalizableMessage("common.userExists.error.message", escapeHTML(producer.getUsername())));
        }

        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadProducerFromDatabase() {
        String ids = getContext().getRequest().getParameter("producer.id");
        if (ids == null) {
            return;
        }
        producer = producerService.findById(Long.parseLong(ids));
    }

    public Resolution edit() {
        LOGGER.debug("edit() producer={}", producer);
        return new ForwardResolution("/producer/edit.jsp");
    }

    public Resolution save() {
        LOGGER.debug("save() producer={}", producer);

        try {
            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            producer.setPassword(encoder.encodePassword(producer.getPassword(), null));
            producerService.updateProducer(producer);
        } catch (Exception ex) {
            getContext().getMessages().add(new LocalizableMessage("common.userExists.error.message", escapeHTML(producer.getUsername())));
        }

        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        LOGGER.debug("delete({})", producer.getId());

        producer = producerService.findById(producer.getId());

        String producerAddress = producer.getAddress();
        String producerName = producer.getName();

        try {
            producerService.deleteProducer(producer);
            getContext().getMessages().add(new LocalizableMessage("producer.delete.message", escapeHTML(producerName), escapeHTML(producerAddress)));
        } catch (Exception ex) {
            getContext().getMessages().add(new LocalizableMessage("producer.delete.error.message", escapeHTML(producerName)));
        }

        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution filter() {
        LOGGER.debug("filter() producer={}", producer);
        if (name == null) {
            name = "";
        }
        if (address == null) {
            address = "";
        }
        producerList = producerService.findByFilter(name, address);
        return new ForwardResolution("/producer/list.jsp");
    }

    public ProducerService getProducerService() {
        return producerService;
    }

    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    public BottleTypeService getBottleTypeService() {
        return bottleTypeService;
    }

    public void setBottleTypeService(BottleTypeService bottleTypeService) {
        this.bottleTypeService = bottleTypeService;
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

}
